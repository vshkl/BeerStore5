package com.vshkl.beerstore5.ui.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * Add overlay with linear gradient applied to both horizontal edges.
 *
 * @param width Width of the gradient.
 * @param colors List of colors to be used in the gradient.
 *
 * @see [Modifier.drawWithContent]
 */
fun Modifier.edgeGradientOverlay(
    width: Dp,
    colors: List<Color>,
) = drawWithContent {
    drawContent()
    drawRect(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset.Zero,
            end = Offset(x = width.toPx(), y = 0F),
        ),
    )
    drawRect(
        brush = Brush.linearGradient(
            colors = colors,
            start = Offset(x = size.width, y = 0F),
            end = Offset(x = size.width - width.toPx(), y = 0F),
        ),
        topLeft = Offset(x = size.width - width.toPx(), y = 0F),
    )
}
