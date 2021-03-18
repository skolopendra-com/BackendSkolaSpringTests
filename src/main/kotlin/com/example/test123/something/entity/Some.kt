package com.example.test123.something.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Some(
    @Id
    var id: Long?,
    var data: String
)
