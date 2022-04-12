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
object BooleanUtils {
    private val logger = LoggerFactory.getLogger(this::class.java)

//    fun <T> T.tureAll(block: T.() -> Unit): T {
//        block()
//        return this
//    }

    fun <T> throwCheckAll(block: () -> Unit) {
        logger.info("check : $this")
        return block()
    }

    fun <T, R> T.tureAll(block: (T) -> R): R {
        logger.info("check : $this")
        return block(this)
    }
}