package com.hfad.introtocompose.util

import androidx.compose.runtime.MutableState

fun calculateTotalPerPerson(totalBillState: MutableState<String>, numberOfSplits: MutableState<Int>, tip: Double): Double {
    return if(isValidState(totalBillState))
        (totalBillState.value.toDouble() + tip) / numberOfSplits.value
    else 0.0
}

fun calculateTip(totalBillState: MutableState<String>, sliderPositionState: MutableState<Float>): Double {
    return if(isValidState(totalBillState))
        totalBillState.value.toDouble() * sliderPositionState.value
    else 0.0
}

fun isValidState(state: MutableState<String>): Boolean = state.value.trim().isNotEmpty()

