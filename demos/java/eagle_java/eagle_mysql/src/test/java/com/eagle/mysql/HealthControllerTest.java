package com.eagle.mysql;

import com.eagle.ServiceMysqlApplication;
import com.eagle.mysql.controller.HealthController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ServiceMysqlApplication.class)
@AutoConfigureMockMvc
class HealthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGreeting() throws Exception {
        // Create the expected response string
        String expectedResponse = "Hello, World!";

        // Perform the MockHTTP GET request to the /greeting endpoint
//        mockMvc.perform(get("/greeting"))
//
//                // Expect a successful response with status 200
//                .andExpect(status().isOk())
//
//                // Expect a response string equal to our expected value
//                .andExpect(content().string(expectedResponse));
    }
}
