package com.water.springboot.study.userapicrud.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(UserController.class).build();
    }


    @Test
    public void testGetUserList() throws Exception {
        RequestBuilder request = get("/users/");
        mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

        request = post("/users/").param("id", "1")
                .param("name", "zhangsan")
                .param("age", "18");
        mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("success")));

        request = put("/users/1").param("name", "lisi").param("age", "19");
        mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string("success"));

        request = get("/users/1");
        mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string("{\"id\":1,\"name\":\"lisi\",\"age\":19}"));

        request = delete("/users/1");
        mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string("success"));

        request = get("/users/");
        mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

    }

}