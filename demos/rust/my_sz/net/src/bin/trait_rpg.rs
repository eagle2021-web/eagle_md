use rand;
use rand::seq::SliceRandom;
use rand::Rng;
#[derive(Debug)]
struct Dwarf{}
#[derive(Debug)]
struct Elf{}
#[derive(Debug)]
struct Human{}
#[derive(Debug)]
enum Thing{
    Sword,
    Trinket,
}
trait Enchanter: std::fmt::Debug{
    fn competency(&self) -> f64;
    fn enchant(&self, thing: &mut Thing){
        let probability_of_success = self.competency();
        let spell_is_successful = rand::thread_rng()
            .gen_bool(probability_of_success);    //-  gen_bool() 产生一个布尔值，其中true的出现比例与其参数值成正比。比如参数值是0.5，则表示有50% 的可能会返回true。
        print!("{:?} mutters incoherently. ", self);
        if spell_is_successful{
            println!("The{:?} glows brightly.", thing);
        } else{
            println!("The{:?} fizzes, \
then turns into a worthless trinket.", thing);
            *thing = Thing::Trinket{};
        }
    }
}
impl Enchanter for Dwarf{
    fn competency(&self) -> f64{
        0.5    //-  矮人族是差劲的施法者，他们的咒语常常会失败。
    }
}
impl Enchanter for Elf{
    fn competency(&self) -> f64{
        0.95    //-  精灵族吟唱的咒语很少会失败。
    }
}
impl Enchanter for Human{
    fn competency(&self) -> f64{
        0.8    //-  人族擅长给装备施放魔法，很少出现失误。
    }
}
fn main(){
    let mut it = Thing::Sword;
    let d = Dwarf{};
    let e = Elf{};
    let h = Human{};
    let party: Vec<&dyn Enchanter> = vec![&d, &h, &e];    //-  我们可以把不同类型的成员放到同一个Vec中，因为这些成员都实现了这个Enchanter trait。
    let spellcaster = party.choose(&mut rand::thread_rng()).unwrap();
    spellcaster.enchant(&mut it);
}