package com.example.test123.something.service

import com.example.test123.something.entity.Some
import com.example.test123.something.vo.SomeRq

interface SomeService {

    fun save(some: Some): Some

    fun create(someRq: SomeRq): Some

    fun findAll(): List<Some>

}
