package com.eagle.mongodb;

import com.eagle.mongodb.entity.Gav;
import com.eagle.mongodb.tacos.Taco;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ServiceMongodbApplication.class)
@AutoConfigureMockMvc
public class TestMVC {
    private Gav gav;

    {
        gav = new Gav();
        gav.setGroupId("com.eagle");
        gav.setArtifactId("mongodb");
        gav.setVersion("1.0");
        Gav gav2 = new Gav();
        gav.setDependencies(new ArrayList<>());
        gav2.setGroupId("112");
        gav2.setArtifactId("23");
        String id = "122124";
        gav.getDependencies().add(gav2);
        gav.setUid(id);
    }
    @BeforeAll
    public static void aa() {

    }

    private MvcResult postJson(String s, String relURL) throws Exception {
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(relURL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(s)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        return resultActions.andReturn();
    }

    @Resource
    private MockMvc mockMvc;

    @Test
    public void person() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/taco/add")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String ret = mvcResult.getResponse().getContentAsString();
        System.out.println(ret);
    }

    @Test
    public void saveGav() throws Exception {
        Gav gav1 = new Gav();
        gav1.setGroupId("1");
        gav1.setArtifactId("2");
        Gav gav2 = new Gav();
        gav2.setDependencies(new ArrayList<>());
        gav2.getDependencies().add(gav1);
        gav2.setGroupId("112");
        gav2.setArtifactId("23");
        String id = "122124";
        gav2.setUid(id);
        String jsonContent = new ObjectMapper().writeValueAsString(gav2);
        MvcResult mvcResult = postJson(jsonContent, "/gav/saveOne");
        String ret = mvcResult.getResponse().getContentAsString();
        String exp = "{\"code\":0,\"message\":null,\"data\":\"" + id + "\"}";
        assertEquals(ret, exp);
    }

    @Test
    public void saveOneByGav() throws Exception {

        String jsonContent = new ObjectMapper().writeValueAsString(gav);
        MvcResult mvcResult = postJson(jsonContent, "/gav/saveOneByGav");
        String ret = mvcResult.getResponse().getContentAsString();
        System.out.println(ret);

    }

    @Test
    public void saveOneByGav2() throws Exception {

        String jsonContent = new ObjectMapper().writeValueAsString(gav);
        MvcResult mvcResult = postJson(jsonContent, "/gav/saveOneByGav2");
        String ret = mvcResult.getResponse().getContentAsString();
        System.out.println(ret);

    }
}
