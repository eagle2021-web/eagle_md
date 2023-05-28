package com.eagle.spring;

import com.eagle.ServiceApplication;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = ServiceApplication.class)
@AutoConfigureMockMvc
public class TestMVC {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void abc() throws Exception {
       mockMvc.perform(
                        MockMvcRequestBuilders.get("/health")
                )
                .andExpect(MockMvcResultMatchers.status().isOk());


    }
}
