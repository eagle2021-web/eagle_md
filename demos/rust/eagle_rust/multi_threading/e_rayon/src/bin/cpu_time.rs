use std::mem::MaybeUninit;
use winapi::um::processthreadsapi::{GetCurrentProcess, GetProcessTimes};

fn main() {
    let t = get_cpu_time();
    if let Some(t) = t {
        println!("time = {}", t);
    } else {
        panic!("acquire time err");
    }
}

pub fn get_cpu_time() -> Option<u64> {
    let (kernel, user) = unsafe {
        let process = GetCurrentProcess();
        let mut _creation = MaybeUninit::uninit();
        let mut _exit = MaybeUninit::uninit();
        let mut kernel = MaybeUninit::uninit();
        let mut user = MaybeUninit::uninit();
        if GetProcessTimes(
            process,
            _creation.as_mut_ptr(),
            _exit.as_mut_ptr(),
            kernel.as_mut_ptr(),
            user.as_mut_ptr(),
        ) == 0
        {
            return None;
        }
        (kernel.assume_init(), user.assume_init())
    };
    let kernel = (kernel.dwHighDateTime as u64) << 32 | kernel.dwLowDateTime as u64;
    let user = (user.dwHighDateTime as u64) << 32 | user.dwLowDateTime as u64;
    Some(100 * (kernel + user))
}


#[cfg(test)]
mod test {
    #[test]
    fn test_rayon() {
        fn partition<T: PartialOrd + Send>(v: &mut [T]) -> usize {
            let (mut i, pivot) = (0, v.len() - 1);
            for j in 0..pivot {
                if v[j] < v[pivot] {
                    v.swap(i, j);
                    i += 1;
                }
            }
            v.swap(i, pivot);
            i
        }
        fn quick_sort<T: PartialOrd + Send>(v: &mut [T]) {
            if v.len() <= 1 {
                return;
            }
            let mid = partition(v);
            let (lo, hi) = v.split_at_mut(mid);
            rayon::join(|| quick_sort(lo), || quick_sort(hi));
        }

        let mut v = vec![10, 9, 8, 7, 6, 5, 4, 3, 2, 1];
        let mut v2 = v.clone();
        quick_sort(&mut v);
        v2.sort();
        assert_eq!(&v, &v2);
    }
}