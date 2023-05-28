package com.eagle.mysql;

import com.eagle.ServiceMysqlApplication;
import com.eagle.mysql.controller.HealthController;
import com.eagle.mysql.pojo.entity.Health;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ServiceMysqlApplication.class)
@AutoConfigureMockMvc
class HealthControllerTest {

    @Resource
    private MockMvc mockMvc;

//    @Test
//    void testGreeting() throws Exception {
//        mockMvc.perform(
//                        MockMvcRequestBuilders.get("/health")
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }

    @Test
    void testPostHealth() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Health health = new Health();
        health.setName("Tom");
        health.setAge(20);
        health.setStatus(1);
        health.setDescription("Good health");
        health.setDeleted(false);
        byte[] bytes = objectMapper.writeValueAsBytes(health);
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/health/healthJson")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bytes)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
