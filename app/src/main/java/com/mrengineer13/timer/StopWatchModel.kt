package com.mrengineer13.timer

import androidx.lifecycle.MutableLiveData

data class StopWatchModel( var seconds : MutableLiveData<Long> = MutableLiveData<Long>(0), var running : Boolean = false){

    fun incrementSeconds() {
        seconds.postValue(seconds.value!! + 1)
    }
}