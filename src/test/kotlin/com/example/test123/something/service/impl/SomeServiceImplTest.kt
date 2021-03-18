package com.example.test123.something.service.impl

import com.example.test123.something.entity.Some
import com.example.test123.something.repository.SomeRepository
import com.example.test123.something.vo.SomeRq
import com.nhaarman.mockitokotlin2.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SomeServiceImplTest {

    private lateinit var someRepository: SomeRepository
    private lateinit var someServiceImpl: SomeServiceImpl

    @BeforeEach
    fun setUp() {
        someRepository = mock {
            on { save(any<Some>()) } doAnswer { inv -> inv.getArgument(0) }
        }
        someServiceImpl = SomeServiceImpl(someRepository)
    }

    @Test
    fun save() {
        val some = Some(1L, "d1")
        assertEquals(
            some,
            someServiceImpl.save(some)
        )
        verify(someRepository).save(eq(some))
    }

    @Test
    fun create() {
        with(someServiceImpl.create(SomeRq("d2"))) {
            assertNotNull(id)
            assertEquals("d2", data)
        }
        verify(someRepository).save(argThat { id != null && data == "d2" })
    }

}
