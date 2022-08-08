package com.zj.calculator.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zj.calculator.R
import com.zj.calculator.ui.theme.Purple200
import com.zj.calculator.ui.theme.Teal200
import com.zj.calculator.utils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * 版权：Zhujiang 个人版权
 * @author zhujiang
 * 版本：1.0
 * 创建日期：2022/08/06
 * 描述：Calculator
 *
 */
@HiltViewModel
class CalculatorViewModel @Inject constructor(
    application: Application,
    private val calculatorRepository: CalculatorRepository
) : AndroidViewModel(application) {

    companion object {
        private const val TAG = "CalculatorViewModel"
        private const val DEFAULT_RESULT = "0"
    }

    private val app: Application = application
    private val maxNumber = app.resources.getInteger(R.integer.max_number)
    private var firstString = ""
    private var isNew = false

    private val _result = MutableLiveData(DEFAULT_RESULT)
    val result: LiveData<String> = _result

    private fun onResultChanged(result: String) {
        if (result == _result.value) {
            return
        }
        _result.postValue(result)
    }

    private val _symbolBg = MutableLiveData(Pair("", Purple200))
    val symbolBg: LiveData<Pair<String, Color>> = _symbolBg

    private fun onSymbolBgChanged(symbolBg: Pair<String, Color>) {
        if (symbolBg == _symbolBg.value) {
            return
        }
        _symbolBg.postValue(symbolBg)
    }


    fun calculatorResult() {
        val value = result.value
        if (value.isNullOrEmpty() || value == DEFAULT_RESULT) {
            Log.w(TAG, "calculatorResult: return")
            return
        }
        val symBol = symbolBg.value
        if (firstString.isEmpty() || symBol == null) {
            onResultChanged(value)
            return
        }
        val endString = "$firstString${symBol.first}${value}"
        val calculatorResult = calculatorRepository.calculatorResult(endString)
        onSymbolBgChanged(Pair("", Teal200))
        onResultChanged(calculatorResult)
        firstString = ""
    }

    /**
     * 构建数字
     *
     * @param r 目前字符串
     * @param number 需要构建的数字
     */
    fun buildNumber(
        r: String,
        number: String
    ) {
        val result: String
        if (isNew) {
            onResultChanged("")
            isNew = false
            result = ""
        } else {
            result = r
        }
        if (result.length > maxNumber) {
            app.showToast(R.string.warn_max_length)
            Log.w(TAG, "buildNumber: max length")
            return
        }
        val symbol = symbolBg.value
        val v = if (symbol == null || symbol.first.isEmpty() || firstString.isEmpty()) {
            if (result != DEFAULT_RESULT) {
                "${result}$number"
            } else {
                number
            }
        } else {
            if (result != DEFAULT_RESULT) {
                "${result}$number"
            } else {
                number
            }
        }
        onResultChanged(v)
    }

    /**
     * 构建符号
     *
     * @param result 目前字符串
     * @param symbol 需要构建的符号
     */
    fun buildSymbol(
        result: String,
        symbol: Char
    ) {
        if (result == DEFAULT_RESULT && symbol == '÷') {
            onSymbolBgChanged(Pair("", Teal200))
            app.showToast(R.string.warn_zero)
            Log.w(TAG, "buildSymbol: The dividend can't be 0")
            return
        }
        firstString = _result.value ?: DEFAULT_RESULT
        isNew = true
        onSymbolBgChanged(Pair(symbol.toString(), Teal200))
    }

    /**
     * 构建百分比
     *
     * @param result 目前字符串
     */
    fun buildPercent(
        result: String,
    ) {
        if (result == DEFAULT_RESULT) {
            app.showToast(R.string.warn_zero)
            Log.w(TAG, "buildSymbol: The dividend can't be 0")
            return
        }
        onResultChanged("${result.toDouble() / 100}")
    }

    /**
     * 计算器中的C，删除一位
     *
     * @param result 目前字符串
     */
    fun buildDeleteBit(
        result: String,
    ) {
        if (result != DEFAULT_RESULT && result.length > 1) {
            onResultChanged(result.substring(0, result.length - 1))
        } else {
            onResultChanged(DEFAULT_RESULT)
            onSymbolBgChanged(Pair("", Teal200))
        }
    }

    fun buildAC() {
        onResultChanged(DEFAULT_RESULT)
        onSymbolBgChanged(Pair("", Teal200))
    }

    /**
     * 构建小数点
     *
     * @param result 目前字符串
     */
    fun buildDecimalPoint(
        result: String,
    ) {
        if (result != DEFAULT_RESULT) {
            if (!result.contains(".")) {
                onResultChanged("${result}.")
            } else {
                Log.w(TAG, "buildDecimalPoint")
            }
        } else {
            onResultChanged("${result}.")
        }
    }

    /**
     * 构建正负数
     *
     * @param result 目前字符串
     */
    fun buildPlusOrMinus(result: String) {
        if (result.length > maxNumber) {
            app.showToast(R.string.warn_max_length)
            Log.w(TAG, "buildNumber: max length")
            return
        }
        if (result == DEFAULT_RESULT) {
            return
        }
        if (result.contains("-")) {
            onResultChanged(result.substring(1, result.length))
        } else {
            onResultChanged("-${result}")
        }
    }

}