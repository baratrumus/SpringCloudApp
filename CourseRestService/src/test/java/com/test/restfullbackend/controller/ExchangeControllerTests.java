package com.test.restfullbackend.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Сначала надо запустить Spring Boot Eureka
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void whenSendEurUsdResponceOneDotTwo() throws Exception {

        this.mockMvc.perform(get("/")
                .param("fromCur", "EUR")
                .param("toCur", "USD"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(content().string(" 1.2"));
    }

    @Test
    public void whenSendEurCADResponceNoContent() throws Exception {
        this.mockMvc.perform(get("/")
                .param("fromCur", "EUR")
                .param("toCur", "CAD"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
