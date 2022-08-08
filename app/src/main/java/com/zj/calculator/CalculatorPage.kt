package com.zj.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zj.calculator.ui.theme.Purple200
import com.zj.calculator.viewmodel.CalculatorViewModel
import com.zj.calculator.widget.BaseFunctionButton
import com.zj.calculator.widget.BaseNumberButton
import com.zj.calculator.widget.BaseSymbolButton


@Composable
fun CalculatorPage(calculatorViewModel: CalculatorViewModel) {

    val result by calculatorViewModel.result.observeAsState("0")
    val symbolBg by calculatorViewModel.symbolBg.observeAsState(Pair("", Purple200))
    Column(
        Modifier
            .background(color = Color.Black)
            .statusBarsPadding()
    ) {

        // 竖屏状态下上方留白，横屏去除
        val topPadding: Float = integerResource(id = R.integer.result_top_padding).toFloat() / 100
        val resultSize: TextUnit = integerResource(id = R.integer.result_size).sp

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(topPadding)
        )

        // 内容区
        SelectionContainer {
            // 使用SelectionContainer使Text可复制
            Text(
                text = result, modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.result_horizontal_padding),
                        vertical = dimensionResource(id = R.dimen.result_vertical_padding)
                    ),
                textAlign = TextAlign.End,
                maxLines = 1,
                color = Color.White,
                fontSize = resultSize
            )
        }

        // 按钮区
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.65f)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BaseFunctionButton("AC") {
                    calculatorViewModel.buildAC()
                }

                BaseFunctionButton("C") {
                    calculatorViewModel.buildDeleteBit(result)
                }

                BaseFunctionButton("%") {
                    calculatorViewModel.buildPercent(result)
                }

                BaseSymbolButton("÷", backgroundColor = symbolBg) {
                    calculatorViewModel.buildSymbol(result, '÷')
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BaseNumberButton("7") {
                    calculatorViewModel.buildNumber(result, "7")
                }

                BaseNumberButton("8") {
                    calculatorViewModel.buildNumber(result, "8")
                }

                BaseNumberButton("9") {
                    calculatorViewModel.buildNumber(result, "9")
                }

                BaseSymbolButton("x", backgroundColor = symbolBg) {
                    calculatorViewModel.buildSymbol(result, 'x')
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BaseNumberButton("4") {
                    calculatorViewModel.buildNumber(result, "4")
                }

                BaseNumberButton("5") {
                    calculatorViewModel.buildNumber(result, "5")
                }

                BaseNumberButton("6") {
                    calculatorViewModel.buildNumber(result, "6")
                }

                BaseSymbolButton("-", backgroundColor = symbolBg) {
                    calculatorViewModel.buildSymbol(result, '-')
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BaseNumberButton("1") {
                    calculatorViewModel.buildNumber(result, "1")
                }

                BaseNumberButton("2") {
                    calculatorViewModel.buildNumber(result, "2")
                }

                BaseNumberButton("3") {
                    calculatorViewModel.buildNumber(result, "3")
                }

                BaseSymbolButton("+", backgroundColor = symbolBg) {
                    calculatorViewModel.buildSymbol(result, '+')
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BaseNumberButton("0") {
                    calculatorViewModel.buildNumber(result, "0")
                }

                BaseNumberButton(".") {
                    calculatorViewModel.buildDecimalPoint(result)
                }

                BaseNumberButton("+/-") {
                    calculatorViewModel.buildPlusOrMinus(result)
                }

                BaseSymbolButton("=", backgroundColor = symbolBg) {
                    calculatorViewModel.calculatorResult()
                }
            }

        }
    }

}