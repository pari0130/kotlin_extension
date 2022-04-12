package com.dong.kotlin_extension.utils

import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class bak_DateUtils {
    companion object {
        private const val dateTimeFormatKO = "yyyy-MM-dd HH:mm:ss"
        private const val dateTimeFormatEN = "dd-MM-yyyy HH:mm:ss"
        private const val dateFormatKO = "yyyy-MM-dd"
        private const val dateFormatEN = "dd-MM-yyyy"

        /**
         * 날짜 사이 일수
         * ###
         * @param end yyyy-mm-dd 형태의 String date
         * @param start yyyy-mm-dd 형태의 String date
         * @return 두 날짜 사이의 일수 (Long), not null
         */
        fun calcDateBetweenEndAndStart(end: String, start: String) : Long {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")

            val endDate = dateFormat.parse(end).time
            val startDate = dateFormat.parse(start).time

            return (endDate - startDate) / (24 * 60 * 60 * 1000)
        }

        /**
         * 날짜 타입별 추출
         * ###
         * @param dt yyyy-mm-dd 형태의 String date
         * @param type type(y:년도, m:월, d:일)
         * @return 구분자에 따른 년도, 월, 일 (String), not null
         */
        fun whatType(dt: String, type: String) : String {
            var resultVal = ""
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date: LocalDate = LocalDate.parse(dt, formatter)

            if(type.equals("y")) {
                resultVal = date.year.toString()
            } else if(type.equals("m")) {
                resultVal = date.month.toString()
            } else if(type.equals("d")) {
                resultVal = date.dayOfMonth.toString()
            }
            return resultVal
        }

        /**
         * 달의 마지막 날, 달의 시작 날 구하기
         * ###
         * @param dt yyyy-mm-dd 형태의 String date
         * @return ArrayList 에 달의 마지막[1], 달의 시작[2] 일자 (ArrayList<String>), not null
         */
        fun lastDayOfMonth(dt: String) : ArrayList<String> {
            var result = ArrayList<String>()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val dtString = dt.split("-")

            val cal = Calendar.getInstance()
            cal.set(dtString[0].toInt(), dtString[1].toInt()-1, dtString[2].toInt())
            cal.set(dtString[0].toInt(), dtString[1].toInt()-1, cal.getActualMaximum(Calendar.DAY_OF_MONTH))

            result.add(cal.getActualMaximum(Calendar.DAY_OF_MONTH).toString())
            result.add(dateFormat.format(cal.time))

            cal.set(dtString[0].toInt(), dtString[1].toInt()-1, 1)

            result.add(dateFormat.format(cal.time))

            return result
        }

        /**
         * type 구분에 따른 date format 변환
         * ###
         * @param dt String 으로 변환된 Long type 의 date
         * @param type (1 => yyyy-MM-dd to Long, 2 => Long to yyyy-MM-dd)
         * @return type 구분에 따라 변환된 date return (String), not null
         */
        fun convertDate(dt: String, type: String) : String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")

            if(type.equals("1")) {
                var dt = dateFormat.parse(dt)
                return dt.toInstant().epochSecond.toString()
            } else {
                return Instant.ofEpochSecond(dt.toLong()).atOffset(ZoneOffset.ofHours(9)).toLocalDate().toString()
            }
        }

        /**
         * Date 형태의 날짜로 변환 (1622517367 to 2021-06-01)
         * ###
         * @param dt Long dateTime
         * @param offset zone offset, default 9
         * @param lang 국가 언어, default ko
         * @return zone offset time 변환된 date time (String), not null
         */
        fun convertDate(dt: Long, offset: ZoneOffset? = ZoneOffset.ofHours(9), lang: String? = "ko") : String {

            val format = if (lang?.toLowerCase().equals("ko")) {
                dateFormatKO
            } else {
                dateFormatEN
            }

            val zoneOffset = offset?.let {
                offset
            } ?: run {
                ZoneOffset.ofHours(9)
            }

            return DateTimeFormatter.ofPattern(format).format(
                ZonedDateTime.ofInstant(
                    Instant.ofEpochSecond(dt),
                    zoneOffset
                )
            )
        }

        /**
         * DateTime 형태의 날짜로 변환 (1622517367 to 2021-06-01 12:16:07)
         * ###
         * @param dt Long dateTime
         * @param offset zone offset, default 9
         * @param lang 국가 언어, default ko
         * @return zone offset time 변환된 date time (String), not null
         */
        fun convertDateTime(dt: Long, offset: ZoneOffset? = ZoneOffset.ofHours(9), lang: String? = "ko") : String {

            val format = if (lang?.toLowerCase().equals("en")) {
                dateTimeFormatEN
            } else {
                dateTimeFormatKO
            }

            val zoneOffset = offset?.let {
                offset
            } ?: run {
                ZoneOffset.ofHours(9)
            }

            return DateTimeFormatter.ofPattern(format).format(
                ZonedDateTime.ofInstant(
                    Instant.ofEpochSecond(dt),
                    zoneOffset
                )
            )
        }

        /**
         * date가 startDate와 EndDate 사이에 있는지 반환
         * ###
         * @param date target date (yyyy-MM-dd)
         * @param startDate 시작 date (yyyy-MM-dd)
         * @param endDate 종료 date (yyyy-MM-dd)
         * @return 확인결과 (Boolean)
         */
        fun isWithinRange(date: String, startDate: String, endDate: String) : Boolean {
            val localDate = LocalDate.parse(date)
            val startLocalDate = LocalDate.parse(startDate)
            val endLocalDate = LocalDate.parse(endDate).plusDays(1)

            return ( ! localDate.isBefore( startLocalDate ) ) && ( localDate.isBefore( endLocalDate ) )
        }

        /**
         * yyyy-mm-dd 날짜 형태에 대한 정규식 체크
         *
         * 년도 정규식의 경우 19xx년 ~ 29xx년 까지 체크 하도록 설정함
         *
         * - 예시
         *      - datePatternRegex("1999-01-01") then return true
         *      - datePatternRegex("0000-01-01") then return false
         * @param date 정규식 체크를 위한 date
         * @return 정규식 확인결과 (Boolean)
         * @sample
         */
        fun datePatternRegex(date: String) : Boolean{
            val pattern: Pattern =
                Pattern.compile("^((19|2[0-9])\\d\\d)?([- /.])?(0[1-9]|1[012])([- /.])?(0[1-9]|[12][0-9]|3[01])$")
            val matcher: Matcher = pattern.matcher(date)
            return matcher.find()
        }
    }
}