package com.adamarla.ids.data

/**
 * Created by adamarla on 10/27/17.
 */

data class RunningTotal(val count: Int, val amount: Int) {

    operator fun plus(runningTotal: RunningTotal) =
            RunningTotal(count+1, amount+runningTotal.amount)

}