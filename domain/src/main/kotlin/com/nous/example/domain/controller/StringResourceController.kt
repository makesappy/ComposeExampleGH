package com.nous.example.domain.controller

interface StringResourceController {
    fun getString(id: Int, vararg formatArgs: Any): String
    fun getPluralString(id: Int, quantity: Int, vararg formatArgs: Any): String
}