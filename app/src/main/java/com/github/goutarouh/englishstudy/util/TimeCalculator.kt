package com.github.goutarouh.englishstudy.util

import java.util.*
import kotlin.math.floor

/**
 * 日付関連の計算のためのutilクラス
 */
object TimeCalculator {

    private const val ONE_DAY_TIME_MILLIS = 1000 * 60 * 60 * 24

    /**
     * 登録された日付を受けとり、いつ登録されたかを返す
     *  ・今日     -> today
     *  ・昨日     -> yesterday
     *  ・それ以上 -> n days ago
     *  ・未来     -> unknown date
     *
     *  @param registeredDate Date
     *  @return today or yesterday or n days ago
     */
    fun dateToWhenPosted(registeredDate: Date): String {
        val today = Date()
        val diff = today.time - registeredDate.time
        if (diff < 0) {
            return "unknown date"
        }

        val diffOfDay = diff / ONE_DAY_TIME_MILLIS.toDouble()
        return when {
            (diffOfDay < 1) -> "today"
            (diffOfDay < 2) -> "yesterday"
            else -> "${diffOfDay.toInt()} days ago"
        }
    }

    /**
     * Date型をyyyy/mm/ddの文字列型に変換する
     * @param registeredDate Date
     * @return yyyy/mm/dd of String
     */
    fun dateToString(registeredDate: Date): String {
        val calendar = Calendar.getInstance().apply {
            time = registeredDate
        }

        return "${calendar.get(Calendar.YEAR)}/" +
                "${calendar.get(Calendar.MONTH) + 1}/" +
                "${calendar.get(Calendar.DAY_OF_MONTH)}"

    }

}