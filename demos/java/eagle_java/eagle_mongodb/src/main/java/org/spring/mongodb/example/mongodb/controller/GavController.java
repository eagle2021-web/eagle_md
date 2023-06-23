package org.spring.mongodb.example.mongodb.controller;

import org.spring.mongodb.example.common.result.ResultBean;
import org.spring.mongodb.example.mongodb.entity.Gav;
import org.spring.mongodb.example.mongodb.service.GavService;
import com.google.gson.Gson;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonValue;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Slf4j
@RestController
@RequestMapping("/gav")
public class GavController {
    @Resource
    private GavService gavService;
    @Resource
    private MongoTemplate template;

    @PostMapping("/saveGav")
    public ResultBean<String> saveGav(@RequestBody Gav gav) {
        log.info(gav.toString());
        gavService.saveGav(gav);
        return ResultBean.getInstance(gav.getUid());
    }

    @PostMapping("/saveOne")
    public ResultBean<String> saveOne(@RequestBody Gav gav) {
        log.info(gav.toString());
        gavService.saveOne(gav);
        return ResultBean.getInstance(gav.getUid());
    }
    /**
     * 更新or插入
     * @param gav json对象
     * @return doc id
     */
    @PostMapping("/saveOneByGav")
    public ResultBean<String> saveByGav(@RequestBody Gav gav) {
        System.out.println("121212121212121212121212");
        Query query = new Query();
        query.addCriteria(where("artifactId").is(gav.getArtifactId())
                .and("groupId").is(gav.getGroupId())
                .and("version").is(gav.getVersion()));
        Gson gson = new Gson();
        String s = gson.toJson(gav);
        BasicUpdate basicUpdate = new BasicUpdate(s);
        UpdateResult upsert = template.update(Gav.class)
                .matching(query)
                .apply(basicUpdate)
                .upsert();
        BsonValue upsertedId = upsert.getUpsertedId();
        System.out.println(upsertedId);

        return ResultBean.getInstance(gav.getUid());
    }

    /**
     * 更新or插入
     * @param gav json对象
     * @return doc id
     */
    @PostMapping("/saveOneByGav2")
    public ResultBean<String> saveOneByGav2(@RequestBody Gav gav) {
        System.out.println(gav.toString());
        log.info(gav.toString());
        Query query = new Query();
        query.addCriteria(where("artifactId").is(gav.getArtifactId())
                .and("groupId").is(gav.getGroupId())
                .and("version").is(gav.getVersion()));
        template.update(Gav.class)
                .matching(query)
                .replaceWith(gav)
                .withOptions(FindAndReplaceOptions.options().upsert())
                .as(Gav.class)
                .findAndReplace();

        return ResultBean.getInstance(gav.getUid());
    }
}
