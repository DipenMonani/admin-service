/*
package com.admin.entity

import java.time.*
import jakarta.persistence.*

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    open var id: Long = 0

    open lateinit var createdAt: ZonedDateTime

    open lateinit var updatedAt: ZonedDateTime

    @PrePersist
    fun prePersist() {
        createdAt = ZonedDateTime.now(ZoneId.of("UTC"))
        updatedAt = ZonedDateTime.now(ZoneId.of("UTC"))
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = ZonedDateTime.now(ZoneId.of("UTC"))
    }
}
*/
