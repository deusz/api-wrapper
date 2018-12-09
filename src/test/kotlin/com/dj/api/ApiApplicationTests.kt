package com.dj.api

import com.dj.api.Repository.PreorderRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.junit.Assert.*
import org.springframework.cloud.contract.wiremock.WireMockRestServiceServer
import org.springframework.web.client.RestTemplate

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ApiApplicationTests {

    @Autowired
    private val restTemplate: RestTemplate? = null

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun contextLoads() {
    }

    @Test
    fun callKinguin() {

        WireMockRestServiceServer.with(restTemplate)
                .baseUrl("https://api.kinguin.net")
                .stubs("classpath:/stubs/get-preorders.stub.json")
                .build()

        val client = PreorderRepository(restTemplate!!)
        assertEquals(client.findPreorders(null)?.get(0)?.id, "117570")
    }

    @Test
    fun callKinguin2() {

        WireMockRestServiceServer.with(restTemplate)
                .baseUrl("https://api.kinguin.net")
                .stubs("classpath:/stubs/get-preorder.stub.json")
                .build()

        val client = PreorderRepository(restTemplate!!)
        assertEquals(client.findPreorder(7570)?.id, "7570")
    }

    @Test
    fun whenGetPreorders_thenResponse200AndJson() {
        mvc.perform(
            get("/api/v2/preorders?pagev=1&sort.by=name"))
            .andExpect(status().isOk)
            .andExpect(content().string("{\n" +
                    "  \"_embedded\" : {\n" +
                    "    \"preorders\" : [ {\n" +
                    "      \"name\" : \"1222\",\n" +
                    "      \"_links\" : {\n" +
                    "        \"self\" : {\n" +
                    "          \"href\" : \"http://localhost/api/v2/preorders/1222\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    } ]\n" +
                    "  },\n" +
                    "  \"_links\" : {\n" +
                    "    \"self\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page={page}&sort.by=name&sort.dir={sort.dir}\",\n" +
                    "      \"templated\" : true\n" +
                    "    },\n" +
                    "    \"next\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page={page}&sort.by=name&sort.dir={sort.dir}\",\n" +
                    "      \"templated\" : true\n" +
                    "    }\n" +
                    "  }\n" +
                    "}"))
//            .andExpect(jsonPath("$[0].id", `is`("1222")))
//            .andExpect(jsonPath("$._embedded.preorder[0].id", `is`("1222")))
    }


}
