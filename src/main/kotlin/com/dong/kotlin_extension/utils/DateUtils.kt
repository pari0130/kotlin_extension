package com.dong.kotlin_extension.utils

import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

object DateUtils {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun <T> T.calcDateBetweenEndAndStart() : Long {

        logger.info("check -> $this")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        val endDate = dateFormat.parse("2022-02-01").time
        val startDate = dateFormat.parse("2022-01-01").time

        return (endDate - startDate) / (24 * 60 * 60 * 1000)
    }
}