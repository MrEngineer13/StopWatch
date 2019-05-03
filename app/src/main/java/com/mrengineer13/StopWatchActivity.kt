package com.mrengineer13


import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mrengineer13.interview.R
import com.mrengineer13.timer.TimerViewModel
import kotlinx.android.synthetic.main.activity_stop_watch.*



class StopWatchActivity : AppCompatActivity() {
    var timeHandler = Handler()
    lateinit var timerModel : TimerViewModel

    /**
     * Runnable that updates time variable
     */
    private val timeRunnable = object : Runnable {
        override fun run() {
            timerModel.incrementSeconds()
            timeHandler.postDelayed(this, MILLIS_IN_SECONDS)
        }
    }

    companion object{
        val MILLIS_IN_SECONDS = 1000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop_watch)
        timerModel = ViewModelProviders.of(this)
            .get(TimerViewModel::class.java)

        btn_time_control.setOnClickListener {
            if (timerModel.isFirstStart()) {
                btn_time_control.text = getString(R.string.pause)
                timerModel.setRunning()
                timeHandler.postDelayed(timeRunnable, MILLIS_IN_SECONDS)
            } else if (timerModel.isPaused()){
                btn_time_control.text = getString(R.string.pause)
                timerModel.setRunning()
                timeHandler.postDelayed(timeRunnable, MILLIS_IN_SECONDS)
            } else {
                btn_time_control.text = getString(R.string.resume)
                timerModel.setStopped()
                timeHandler.removeCallbacks(timeRunnable)
            }
        }

        timerModel.model.seconds.observe(this, Observer {
            val displaySeconds = it % 60
            val minutes = it / 60
            val hours = minutes / 60
            time_display.text = String.format("%d:%d:%d", hours, minutes, displaySeconds)
        })
    }
}
