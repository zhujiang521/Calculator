package com.zj.calculator

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zj.calculator.widget.*

private const val TAG = "CalculatorPage"

@Composable
fun CalculatorPage() {

    Column(Modifier.background(color = Color.Black)) {
        // 内容区
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.30f)
        )
        Text(
            text = "111", modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            textAlign = TextAlign.End,
            color = Color.White,
            fontSize = 65.sp
        )

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
                    Log.w(TAG, "CalculatorPage: AC")
                }

                BaseFunctionButton("+/-") {
                    Log.w(TAG, "CalculatorPage: +/-")
                }

                BaseFunctionButton("%") {
                    Log.w(TAG, "CalculatorPage: %")
                }

                BaseSymbolButton("÷") {
                    Log.w(TAG, "CalculatorPage: ÷")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BaseNumberButton("7") {
                    Log.w(TAG, "CalculatorPage: 7")
                }

                BaseNumberButton("8") {
                    Log.w(TAG, "CalculatorPage: 8")
                }

                BaseNumberButton("9") {
                    Log.w(TAG, "CalculatorPage: 9")
                }

                BaseSymbolButton("x") {
                    Log.w(TAG, "CalculatorPage: x")
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BaseNumberButton("4") {
                    Log.w(TAG, "CalculatorPage: 4")
                }

                BaseNumberButton("5") {
                    Log.w(TAG, "CalculatorPage: 5")
                }

                BaseNumberButton("6") {
                    Log.w(TAG, "CalculatorPage: 6")
                }

                BaseSymbolButton("-") {
                    Log.w(TAG, "CalculatorPage: -")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BaseNumberButton("1") {
                    Log.w(TAG, "CalculatorPage: 1")
                }

                BaseNumberButton("2") {
                    Log.w(TAG, "CalculatorPage: 2")
                }

                BaseNumberButton("3") {
                    Log.w(TAG, "CalculatorPage: 3")
                }

                BaseSymbolButton("+") {
                    Log.w(TAG, "CalculatorPage: +")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                BaseNumberButton("0") {
                    Log.w(TAG, "CalculatorPage: 0")
                }

                BaseNumberButton(".") {
                    Log.w(TAG, "CalculatorPage: .")
                }
                BaseBigSymbolButton("=") {
                    Log.w(TAG, "CalculatorPage: =")
                }
            }

        }
    }

}