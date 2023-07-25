package com.eagle.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.spring.mongodb.example.ServiceTestMVCApplication;
import org.spring.mongodb.example.service.SService;
import org.spring.mongodb.example.spring_test.contoller.HealthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ServiceTestMVCApplication.class)
@AutoConfigureMockMvc
public class TestMvc {

    @Mock
    private SService sService;

    @InjectMocks
    private HealthController controller;

    @Resource
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void test() throws Exception {
        // 设置模拟行为
        when(sService.abc(anyString())).thenReturn("hello");

        // 执行测试逻辑并断言结果
        mockMvc.perform(MockMvcRequestBuilders.get("/health/one"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello"));
        // 验证 queryJson 方法被正确调用
        verify(sService).abc(anyString());
    }
}