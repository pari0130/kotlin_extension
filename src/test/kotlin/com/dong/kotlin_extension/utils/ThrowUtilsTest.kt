package com.dong.kotlin_extension.utils

import com.dong.kotlin_extension.utils.BooleanUtils.tureAll
import com.dong.kotlin_extension.utils.DateUtils.calcDateBetweenEndAndStart
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ThrowUtilsTest {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Test
    fun test1(){
        // "sss".toStr()

        logger.info(" ${"sss".calcDateBetweenEndAndStart()}")
        logger.info(" ${mapOf(1 to 1).calcDateBetweenEndAndStart()}")
    }

    @Test
    fun test2(){
        listOf(1,2,3).tureAll {

        }
    }
}