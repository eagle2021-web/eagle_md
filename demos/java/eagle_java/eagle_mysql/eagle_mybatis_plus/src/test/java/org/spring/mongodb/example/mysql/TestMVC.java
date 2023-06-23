package org.spring.mongodb.example.mysql;

import org.spring.mongodb.example.ServiceMysqlPlusApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ServiceMysqlPlusApplication.class)
@AutoConfigureMockMvc
public class TestMVC {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void abc() throws Exception {
       mockMvc.perform(
                        MockMvcRequestBuilders.get("/health/one")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());


    }
    @Test
    public void two() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/health/two")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void person() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/health/getOneById")
                                .param("id", "1")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String ret = mvcResult.getResponse().getContentAsString();
        System.out.println(ret);
    }
    @Test
    public void saveGav() throws Exception {

        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/gav/saveGav")
                                .param("id", "1")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String ret = mvcResult.getResponse().getContentAsString();
        System.out.println(ret);
    }
}

