package com.eagle.mongodb;

import com.eagle.mongodb.entity.Gav;
import com.eagle.mongodb.tacos.Taco;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        gav2.setUid("1221");
        String jsonContent = new ObjectMapper().writeValueAsString(gav2);
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/gav/saveOne")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonContent)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String ret = mvcResult.getResponse().getContentAsString();
        String exp = "{\"code\":0,\"message\":null,\"data\":\"1221\"}";
        assertEquals(ret, exp);
    }
}
