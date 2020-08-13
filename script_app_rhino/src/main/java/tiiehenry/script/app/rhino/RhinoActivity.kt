package tiiehenry.script.app.rhino

import android.content.Context
import tiiehenry.script.engine.android.ScriptActivity
import tiiehenry.script.rhino.RhinoEngine
import tiiehenry.script.rhino.RhinoEngineFactory
import tiiehenry.script.wrapper.engine.IScriptEngineFactory
import tiiehenry.script.wrapper.engine.ScriptEngineManager

class RhinoActivity : ScriptActivity<RhinoEngine, RhinoActivity>() {
    override val activity: RhinoActivity = this

    private val factory: IScriptEngineFactory<*, *, *> = RhinoEngineFactory()
    override val engineName: String = factory.engineName

    init {
        ScriptEngineManager.addEngineFactory(factory)
    }


    override fun onCreateEngine() {
//        System.`in`
        engine.apply {
            create()
        }
    }

    override val context: Context = this

}