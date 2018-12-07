package com.dj.api

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.hamcrest.CoreMatchers.`is`

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ApiApplicationTests {

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun contextLoads() {
    }

    @Test
    fun whenGetPreorders_thenResponse200AndJson() {
        mvc.perform(
            get("/api/v2/preorders"))
            .andExpect(status().isOk)
            .andExpect(content().string("{\"_embedded\":{\"preorders\":[{\"name\":\"1222\"}]}}"))
//            .andExpect(jsonPath("$[0].id", `is`("1222")))
//            .andExpect(jsonPath("$._embedded.preorder[0].id", `is`("1222")))
    }


}
