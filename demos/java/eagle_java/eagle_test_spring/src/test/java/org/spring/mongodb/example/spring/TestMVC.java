package org.spring.mongodb.example.spring;

import org.spring.mongodb.example.ServiceTestMVCApplication;
import org.spring.mongodb.example.entity.Person;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ServiceTestMVCApplication.class)
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
        Person person = new Person("ADF", 12);
        String jsonContent = new ObjectMapper().writeValueAsString(person);
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/entity/person")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonContent)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        MvcResult mvcResult = resultActions.andReturn();
        String ret = mvcResult.getResponse().getContentAsString();
        assertEquals(ret, "ok");
    }
}
