package com.github.goutarouh.englishstudy.util

import com.github.goutarouh.englishstudy.util.TimeCalculator.dateToWhenPosted
import org.junit.Test
import org.assertj.core.api.Assertions.*
import java.util.*

class TimeCalculatorTest {

    @Test
    fun dateToWhenPostedTest() {

        val tomorrow = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 1)
        }
        val today = Calendar.getInstance()
        val yesterday = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -1)
        }
        val threeDaysAgo = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -3)
        }

        val tenDaysAgo = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, - 10)
        }

        val actualTomorrow = dateToWhenPosted(tomorrow.time)
        assertThat(actualTomorrow)
            .`as`("expect tomorrow")
            .isEqualTo("unknown date")


        val actualToday = dateToWhenPosted(today.time)
        assertThat(actualToday)
            .`as`("expect today")
            .isEqualTo("today")

        val actualYesterday = dateToWhenPosted(yesterday.time)
        assertThat(actualYesterday)
            .`as`("expect yesterday")
            .isEqualTo("yesterday")

        val actualThreeDaysAgo = dateToWhenPosted(threeDaysAgo.time)
        assertThat(actualThreeDaysAgo)
            .`as`("expect 3 days ago")
            .isEqualTo("3 days ago")

        val actualTenDaysAgo = dateToWhenPosted(tenDaysAgo.time)
        assertThat(actualTenDaysAgo)
            .`as`("expect 10 days ago")
            .isEqualTo("10 days ago")
    }


}