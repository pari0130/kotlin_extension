package com.dong.kotlin_extension.utils

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException

object JsonUtils {

    val objectMapper: ObjectMapper = ObjectMapper()

    fun <T> toObject(json: String, clazz: Class<T>): T {
        try {
            return objectMapper.readValue(json, clazz)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun <T> toJsonString(entity: T): String {
        try {
            return objectMapper.writeValueAsString(entity)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
    }

    fun <T> toCollection(json: String, typeReference: TypeReference<T>): T {
        try {
            return objectMapper.readValue<T>(json, typeReference)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    fun <T> classToMap(bean: Any): Map<*, *> {
        try {
            return objectMapper.convertValue(bean, Map::class.java)
        } catch (ex: Exception) {
            throw RuntimeException(ex)
        }
    }

    fun <T> mapToClass(map: Map<*, *>, clazz: Class<T>): T {
        return objectMapper.convertValue(map, clazz)
    }
}