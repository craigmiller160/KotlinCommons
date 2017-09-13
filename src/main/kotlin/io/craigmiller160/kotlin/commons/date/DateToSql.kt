package io.craigmiller160.kotlin.commons.date

import java.sql.Date
import java.sql.Time
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun LocalDate.toSqlDate(): Date = Date.valueOf(this)

fun LocalTime.toSqlTime(): Time = Time.valueOf(this)

fun LocalDateTime.toSqlTimestamp(): Timestamp = Timestamp.valueOf(this)