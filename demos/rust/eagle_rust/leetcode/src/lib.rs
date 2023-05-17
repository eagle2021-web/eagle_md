pub fn add(left: usize, right: usize) -> usize {
    left + right
}

#[derive(Debug)]
pub struct Solution {
    pub mask: usize,
    pub res: Vec<Vec<String>>,
    pub record: Vec<usize>,
    pub n: usize,
}


pub fn dfs2(row: usize, col: usize, pie: usize, na: usize, n: usize, mask: usize, record: &mut Vec<usize>, res: &mut Vec<Vec<String>>) {
    if row == n {
        let mut s_arr = vec![];
        for v in record.iter().enumerate() {
            let mut cs = vec!['.' as u8; n];
            cs[*v.1] = 'Q' as u8;
            s_arr.push(String::from_utf8(cs).unwrap());
        }
        res.push(s_arr);
    } else {
        let used = col | pie | na;
        let mut can_used = mask & !used;
        while can_used != 0 {
            let tmp_can_used = can_used & (can_used - 1);
            let used_col = tmp_can_used ^ can_used;
            can_used = tmp_can_used;

            record[row] = (used_col - 1).count_ones() as usize;
            dfs2( row + 1, used_col | col, (pie | used_col) << 1, (na | used_col) >> 1, n, mask, record, res);
        }
    }
}

impl Solution {
    pub fn solve_n_queens(n: i32) -> Vec<Vec<String>> {
        let (n, mask, mut record, mut res) = (n as usize, (1 << n) - 1, vec![0; n as usize], vec![]);
        dfs2(0, 0, 0, 0, n, mask, &mut record, &mut res);
        res
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = add(2, 2);
        assert_eq!(result, 4);
        let a = Solution::solve_n_queens(4);
        println!("a = {:?}", a);
    }

    #[test]
    fn it_works3() {
        let a = 1;
        let b = a & (a - 1);
        let c = a & !a;
        println!("b = {:?}", b);
        println!("c = {:?}", c);

        assert_eq!(7_usize.count_ones(), 3);
        assert_eq!(6_i32.count_ones(), 2);
        assert_eq!(1_usize.count_ones(), 1);
    }
}
