package tiiehenry.script.engine.android

import android.app.Activity
import android.content.Context
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tiiehenry.script.wrapper.engine.IScriptEngine
import tiiehenry.script.wrapper.engine.IScriptEngineFactory
import java.util.*


interface ScriptContextActivity<E : IScriptEngine<*, *>, A : AppCompatActivity> : ScriptContext<E> {
    val activity: A
    override val context: Context
        get() = activity

    override val mainHandler: ScriptContextActivityHandler

    val printTextView: TextView


    override fun onEngineInited() {
        super.onEngineInited()
        engine.apply {
            varBridge.set("activity", activity)
        }

        printTextView.apply {
            setPadding(10, 10, 10, 10)
            setTextIsSelectable(true)
        }

        val scroll = ScrollView(context).apply {
            isFillViewport = true
            addView(printTextView, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
        }

        activity.setContentView(scroll)

        activity.intent?.let {
            processIntent(it)
        }
    }


    fun overridePendingTransitionOpen() {
        activity.overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit)
    }

    fun overridePendingTransitionExit() {
        activity.overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit)
    }
}