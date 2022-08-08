package com.zj.calculator.widget

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.zj.calculator.R
import com.zj.calculator.ui.theme.Purple200

/**
 * 圆形按钮Base
 *
 * @param modifier 修饰符
 * @param text 按钮上的文字
 * @param backgroundColor 按钮的背景颜色
 * @param textColor 按钮上文字的颜色
 * @param fontSize 文字大小
 * @param onClick 按钮点击事件
 */
@Composable
fun BaseButton(
    modifier: Modifier = Modifier.size(dimensionResource(id = R.dimen.btn_size)),
    text: String = "0",
    backgroundColor: Color = MaterialTheme.colors.primary,
    textColor: Color = Color.White,
    fontSize: TextUnit = TextUnit.Unspecified,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
    ) {
        Text(text = text, color = textColor, fontSize = fontSize)
    }
}

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
fun BaseNormalButton(
    text: String = "0",
    backgroundColor: Color = MaterialTheme.colors.primary,
    textColor: Color = Color.White,
    fontSize: TextUnit = TextUnit.Unspecified,
    onClick: () -> Unit
) {
    BaseButton(
        text = text,
        backgroundColor = backgroundColor,
        textColor = textColor,
        fontSize = fontSize,
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
fun BaseSymbolButton(
    text: String = "+",
    backgroundColor: Pair<String, Color>,
    onClick: () -> Unit
) {
    val color: Color = if (text == backgroundColor.first) {
        backgroundColor.second
    } else {
        Purple200
    }

    BaseNormalButton(
        text = text,
        backgroundColor = color,
        textColor = Color.White,
        fontSize = integerResource(id = R.integer.btn_symbol_text_size).sp,
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
    BaseNormalButton(
        text = text,
        backgroundColor = Color.LightGray,
        textColor = Color.Black,
        fontSize = integerResource(id = R.integer.btn_text_size).sp,
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
    BaseNormalButton(
        text = text,
        backgroundColor = Color.DarkGray,
        textColor = Color.White,
        fontSize = integerResource(id = R.integer.btn_text_size).sp,
        onClick = onClick
    )
}