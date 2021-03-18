package com.example.test123.something.controller

import com.example.test123.Test123Application
import com.example.test123.json
import com.example.test123.something.entity.Some
import com.example.test123.something.service.SomeService
import com.example.test123.something.vo.SomeRq
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.hasItem
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@AutoConfigureMockMvc
@SpringBootTest(classes = [Test123Application::class])
internal class SomeControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var someService: SomeService

    @BeforeEach
    fun setUp() {
        whenever(someService.create(any())).thenReturn(Some(1, "d1"))
        whenever(someService.findAll()).thenReturn(
            listOf(
                Some(1, "d1"),
                Some(2, "d2")
            )
        )
    }

    @Test
    fun create() {
        mockMvc.perform(post("/some").json(SomeRq("d1")))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.data").value("d1"))
    }

    @Test
    internal fun findAll() {
        mockMvc.perform(get("/some"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.*.id").value(hasItem(1)))
            .andExpect(jsonPath("$.*.data").value(hasItem("d1")))
            .andExpect(jsonPath("$.*.id").value(hasItem(2)))
            .andExpect(jsonPath("$.*.data").value(hasItem("d2")))
    }
}
