package com.example.test123

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder

fun Any?.toJson(): String = ObjectMapper().apply {
    configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false)
    registerModule(JavaTimeModule())
}.writeValueAsString(this)

fun MockHttpServletRequestBuilder.json(body: Any): MockHttpServletRequestBuilder =
    this.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(body.toJson())

