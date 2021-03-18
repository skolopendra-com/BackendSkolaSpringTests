package com.example.test123.something.service.impl

import com.example.test123.something.entity.Some
import com.example.test123.something.repository.SomeRepository
import com.example.test123.something.service.SomeService
import com.example.test123.something.vo.SomeRq
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class SomeServiceImpl(val someRepository: SomeRepository) : SomeService {

    override fun save(some: Some): Some = someRepository.save(some)

    override fun create(someRq: SomeRq): Some = save(Some(Random.nextLong(), someRq.data))

    override fun findAll(): List<Some> = someRepository.findAll()

}
