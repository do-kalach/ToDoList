package com.example.data.mapper

interface Mapper<Input, Output> {
    fun map(data: Input): Output
}