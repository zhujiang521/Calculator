package com.zj.calculator.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zj.calculator.R
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
    }

    private val app: Application = application

    private val _result = MutableLiveData("0")
    val result: LiveData<String> = _result

    fun onResultChanged(result: String) {
        if (result == _result.value) {
            return
        }
        _result.postValue(result)
    }

    fun calculatorResult() {
        val value = result.value
        if (value.isNullOrEmpty() || value == "0") {
            Log.w(TAG, "calculatorResult: return")
            return
        }
        val calculatorResult = calculatorRepository.calculatorResult(value)
        onResultChanged(calculatorResult)
    }

    /**
     * 构建数字
     *
     * @param result 目前字符串
     * @param number 需要构建的数字
     */
    fun buildNumber(
        result: String,
        number: String
    ) {
        if (result.length > 7) {
            app.showToast(R.string.warn_max_length)
            Log.w(TAG, "buildNumber: max length")
            return
        }
        val v = if (result != "0") {
            "${result}$number"
        } else {
            number
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
        if (result.length > 7) {
            app.showToast(R.string.warn_max_length)
            Log.w(TAG, "buildNumber: max length")
            return
        }
        if (result == "0" && symbol == '÷') {
            app.showToast(R.string.warn_zero)
            Log.w(TAG, "buildSymbol: The dividend can't be 0")
            return
        }
        if (result.toCharArray()[result.length - 1] != symbol) {
            onResultChanged("${result}$symbol")
        }
    }

    /**
     * 构建百分比
     *
     * @param result 目前字符串
     */
    fun buildPercent(
        result: String,
    ) {
        if (result == "0") {
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
        if (result != "0" && result.length > 1) {
            onResultChanged(result.substring(0, result.length - 1))
        } else {
            onResultChanged("0")
        }
    }

    /**
     * 构建小数点
     *
     * @param result 目前字符串
     */
    fun buildDecimalPoint(
        result: String,
    ) {
        if (result.length > 7) {
            app.showToast(R.string.warn_max_length)
            Log.w(TAG, "buildNumber: max length")
            return
        }
        if (result != "0") {
            if (!result.contains(".")) {
                onResultChanged("${result}.")
            } else {
                Log.w(TAG, "buildDecimalPoint")
            }
        } else {
            onResultChanged("${result}.")
        }
    }

}