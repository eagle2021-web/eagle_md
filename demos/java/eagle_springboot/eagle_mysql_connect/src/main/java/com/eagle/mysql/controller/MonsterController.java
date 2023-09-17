package com.eagle.mysql.controller;

import com.eagle.mysql.mapper.MonsterMapper;
import com.eagle.mysql.pojo.entity.Monster;
import com.eagle.mysql.service.IMonsterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2023-07-02
 */
@RestController
@RequestMapping("/monster")
@Slf4j
public class MonsterController {
    @Resource(name = "monsterServiceImpl")
    private IMonsterService iMonsterService;
    @Resource
    private MonsterMapper monsterMapper;
    @PostMapping("/deleteById")
    public String deleteById(@RequestParam("id") Integer id){
        log.info("dafsdf");
        iMonsterService.removeById(id);
        return "ok";
    }

    @PostMapping("/addMonster")
    public String addMonster(){
        log.info("dafsdf");
        Monster monster = new Monster();
        monster.setName("hsp");
        monsterMapper.addMonster2(monster);
        return monster.toString();
    }

    @PostMapping("/addMonster2")
    public String addMonster2(@RequestParam String name){
        System.out.println(name);
        log.info("dafsdf");
        Monster monster = new Monster();
        monster.setName("hsp");
        monsterMapper.addMonster2(monster);
        return monster.toString();
    }
}
