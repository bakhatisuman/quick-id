package com.example.mycameraapp.session

import java.util.*

interface Repository<T> {
    fun persist(entity: T)


    fun update(entity: T)

    fun findById(id: Objects): T?

    fun findOne(): T?


    fun delete(entity: T)


    fun findAll(): List<T>?


    fun deleteAll()
}