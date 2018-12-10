package com.dj.api

import com.dj.api.Repository.PreorderRepository
import org.hamcrest.Matchers.`is`
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
class PreorderControllerIntTests {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun testKinguinApi() {

        WireMockRestServiceServer.with(restTemplate)
                .baseUrl("https://api.kinguin.net")
                .stubs("classpath:/stubs/get-preorders.stub.json")
                .build()

        val client = PreorderRepository(restTemplate)
        assertEquals(client.findPreorders()[0].id, 117570)
    }

    @Test
    fun whenGetPreorders_thenResponse200AndJson() {
        mvc.perform(
            get("/api/v2/preorders?page=2&size=3&sort=regularPrice"))
            .andExpect(status().isOk)
            .andExpect(content().string("{\n" +
                    "  \"_embedded\" : {\n" +
                    "    \"preorders\" : [ {\n" +
                    "      \"_links\" : {\n" +
                    "        \"self\" : {\n" +
                    "          \"href\" : \"http://localhost/api/v2/preorders/51503\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }, {\n" +
                    "      \"_links\" : {\n" +
                    "        \"self\" : {\n" +
                    "          \"href\" : \"http://localhost/api/v2/preorders/51515\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }, {\n" +
                    "      \"_links\" : {\n" +
                    "        \"self\" : {\n" +
                    "          \"href\" : \"http://localhost/api/v2/preorders/51518\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    } ]\n" +
                    "  },\n" +
                    "  \"_links\" : {\n" +
                    "    \"first\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page=0&size=3&sort=regularPrice,asc\"\n" +
                    "    },\n" +
                    "    \"prev\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page=1&size=3&sort=regularPrice,asc\"\n" +
                    "    },\n" +
                    "    \"self\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page=2&size=3&sort=regularPrice,asc\"\n" +
                    "    },\n" +
                    "    \"next\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page=3&size=3&sort=regularPrice,asc\"\n" +
                    "    },\n" +
                    "    \"lastc\" : {\n" +
                    "      \"href\" : \"http://localhost/api/v2/preorders?page=9&size=3&sort=regularPrice,asc\"\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"page\" : {\n" +
                    "    \"size\" : 3,\n" +
                    "    \"totalElements\" : 30,\n" +
                    "    \"totalPages\" : 10,\n" +
                    "    \"number\" : 2\n" +
                    "  }\n" +
                    "}"))
    }

    @Test
    fun whenGetPreorder_thenResponse200AndJson() {
        mvc.perform(
                get("/api/v2/preorders/51503"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("name", `is`("Doom Eternal EU PRE-ORDER Steam CD Key")))
    }
}
