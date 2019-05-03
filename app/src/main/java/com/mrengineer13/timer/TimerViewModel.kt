package com.mrengineer13.timer

import androidx.lifecycle.ViewModel


class TimerViewModel() : ViewModel() {

    var model = StopWatchModel()

    fun setRunning() {
        model.running = true
    }

    fun setStopped() {
        model.running = false
    }

    fun incrementSeconds() {
        model.incrementSeconds()
    }

    fun isFirstStart(): Boolean {
       return model.seconds.value!! == 0L && !model.running
    }

    fun isPaused(): Boolean {
        return !model.running
    }
}