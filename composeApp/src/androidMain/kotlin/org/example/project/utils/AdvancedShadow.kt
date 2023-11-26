package org.example.project.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun Modifier.advancedShadow(
    color: Color = Color.Black,
    alpha: Float = 1f,
    elevation: Dp = 0.dp,
    cornersRadius: Dp = 0.dp,
    shadowBlurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = drawBehind {

    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()

    drawIntoCanvas { canvas ->
        val paint = Paint().also {
            it.asFrameworkPaint().apply {
                this.color = transparentColor
                setShadowLayer(
                    shadowBlurRadius.toPx(),
                    offsetX.toPx(),
                    offsetY.toPx(),
                    shadowColor
                )
            }
        }
        canvas.drawRoundRect(
            0f,
            0f,
            this.size.width + elevation.toPx(),
            this.size.height + elevation.toPx(),
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}
