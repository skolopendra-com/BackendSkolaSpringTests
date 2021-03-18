package com.example.test123.something.repository

import com.example.test123.something.entity.Some
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SomeRepository : JpaRepository<Some, Long> {

    fun findAllByData(data: String): List<Some>

    fun findByDataIsNot(data: String): List<Some>

}
