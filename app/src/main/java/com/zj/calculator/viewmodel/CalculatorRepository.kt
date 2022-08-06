package com.zj.calculator.viewmodel

import android.icu.text.DecimalFormat
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import kotlin.String

@ViewModelScoped
class CalculatorRepository @Inject constructor() {

    /**
     * 计算公式结果
     *
     * @param r 当前公式
     * @return 计算结果
     */
    fun calculatorResult(r: String): String {
        var result = r
        if (result.contains("x")) {
            result = result.replace("x", "*")
        }
        if (result.contains("÷")) {
            result = result.replace("÷", "/")
        }
        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")
        val eval = engine.eval(result).toString()

        val v = getTwoDecimal(eval.toDouble())
        // 这块由于计算出来是Double，所以进行字符串截取下；当然如果计算公式中包含小数点的话就不进行截取
        val value = if (v.contains(".00")) {
            v.toInt()
        } else {
            v
        }

        return "$value"
    }

    private fun getTwoDecimal(num: Double): String {
        val dFormat = DecimalFormat("#.##")
        return dFormat.format(num)
    }


}