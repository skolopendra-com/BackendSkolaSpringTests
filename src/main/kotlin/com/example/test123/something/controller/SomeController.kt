package com.example.test123.something.controller

import com.example.test123.something.entity.Some
import com.example.test123.something.service.SomeService
import com.example.test123.something.vo.SomeRq
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/some")
class SomeController(val someService: SomeService) {

    @PostMapping
    fun create(@RequestBody someRq: SomeRq): ResponseEntity<Some> = ResponseEntity.ok(someService.create(someRq))

    @GetMapping
    fun findAll(): ResponseEntity<List<Some>> = ResponseEntity.ok(someService.findAll())

}
