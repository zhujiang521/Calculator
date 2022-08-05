package com.zj.calculator.widget

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zj.calculator.ui.theme.Purple200

/**
 * 圆形按钮Base
 *
 * @param text 按钮上的文字
 * @param backgroundColor 按钮的背景颜色
 * @param textColor 按钮上文字的颜色
 * @param fontSize 文字大小
 * @param onClick 按钮点击事件
 */
@Composable
fun BaseButton(
    text: String = "0",
    backgroundColor: Color = MaterialTheme.colors.primary,
    textColor: Color = Color.White,
    fontSize: TextUnit = TextUnit.Unspecified,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(75.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
    ) {
        Text(text = text, color = textColor, fontSize = fontSize)
    }
}

/**
 * 符号按钮的Base，如：+、-、x、/
 *
 * @param text 按钮上的文字
 * @param onClick 按钮点击事件
 */
@Composable
fun BaseSymbolButton(text: String = "+", onClick: () -> Unit) {
    BaseButton(
        text = text,
        backgroundColor = Purple200,
        textColor = Color.White,
        fontSize = 25.sp,
        onClick = onClick
    )
}

/**
 * 符号按钮的Base，如：+、-、x、/
 *
 * @param text 按钮上的文字
 * @param onClick 按钮点击事件
 */
@Composable
fun BaseBigSymbolButton(text: String = "=", onClick: () -> Unit) {
    BaseBigButton(
        text = text,
        backgroundColor = Purple200,
        textColor = Color.White,
        onClick = onClick
    )
}

/**
 * 功能按钮的Base，如：AC、+/-、% 等
 *
 * @param text 按钮上的文字
 * @param onClick 按钮点击事件
 */
@Composable
fun BaseFunctionButton(text: String = "AC", onClick: () -> Unit) {
    BaseButton(
        text = text,
        backgroundColor = Color.LightGray,
        textColor = Color.Black,
        fontSize = 20.sp,
        onClick = onClick
    )
}

/**
 * 数字按钮的Base，如：1、2、3、4 等
 *
 * @param text 按钮上的文字
 * @param onClick 按钮点击事件
 */
@Composable
fun BaseNumberButton(text: String = "0", onClick: () -> Unit) {
    BaseButton(
        text = text,
        backgroundColor = Color.DarkGray,
        textColor = Color.White,
        fontSize = 20.sp,
        onClick = onClick
    )
}

/**
 * 大按钮Base
 *
 * @param text 按钮上的文字
 * @param backgroundColor 按钮的背景颜色
 * @param textColor 按钮上文字的颜色
 * @param onClick 按钮点击事件
 */
@Composable
fun BaseBigButton(
    text: String = "0",
    backgroundColor: Color = MaterialTheme.colors.primary,
    textColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(75.dp)
            .width(160.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
    ) {
        Text(text = text, color = textColor, fontSize = 25.sp)
    }
}