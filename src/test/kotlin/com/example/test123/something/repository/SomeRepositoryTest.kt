package com.example.test123.something.repository

import com.example.test123.Test123Application
import com.example.test123.something.entity.Some
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [Test123Application::class])
class SomeRepositoryTest {

    @Autowired
    private lateinit var someRepository: SomeRepository

    @BeforeEach
    internal fun setUp() {
        someRepository.save(Some(1, "d1"))
        someRepository.save(Some(2, "d1"))
        someRepository.save(Some(3, "d2"))
    }

    @AfterEach
    internal fun tearDown() {
        someRepository.deleteAll()
    }

    @Test
    fun findAllByData() {
        assertEquals(
            listOf(1L, 2L),
            someRepository.findAllByData("d1").mapNotNull(Some::id)
        )
    }

    @Test
    internal fun findByDataIsNot() {
        assertEquals(
            listOf(Some(3, "d2")),
            someRepository.findByDataIsNot("d1")
        )
    }

}
