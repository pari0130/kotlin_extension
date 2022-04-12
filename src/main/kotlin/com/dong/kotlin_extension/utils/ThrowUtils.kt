package com.dong.kotlin_extension.utils

import org.slf4j.LoggerFactory
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/*
throwIfIsNil: 들어오는 값이 null, undefined이면 에러를 던집니다.
throwIfIs: 들어오는 값이 일치하면 에러를 던집니다.
throwIf: 들어오는 값이 predicate 함수의 true 결과이면 에러를 던집니다.
execIfIsNil: 들어오는 값이 null, undefined이면 함수를 실행합니다.
execIfIs: 들어오는 값이 일치하면 함수를 실행합니다.
execIf: 들어오는 값이 predicate 함수의 true 결과이면 함수를 실행합니다.
* */

/*
* 구현 방안에 대해 메모.
* 하나의 값 여러개의 값일때에 대해 대응 가능
* type 을 받아서 확인 가능할 수 있는지
*
* */
object ThrowUtils {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun <T> T.toStr() : String {
        logger.info("test code")
        return this.toString()
    }

    fun <T> T.validate(error: Exception, invoke:() -> Unit) {
        val value = this // T parameter


        throwIfIsNil().throwIfIs()

    }

    fun <T> T.throwApply(block: T.() -> Unit): T {
        block()
        return this
    }

    private fun throwIfIsNil(): ThrowUtils {
        return this
    }

    private fun throwIfIs(): ThrowUtils {
        return this
    }

    private fun throwIf(): ThrowUtils {
        return this
    }

    private fun execIfIsNil(): ThrowUtils {
        return this
    }

    private fun execIfIs(): ThrowUtils {
        return this
    }

    private fun execIf(): ThrowUtils {
        return this
    }
}