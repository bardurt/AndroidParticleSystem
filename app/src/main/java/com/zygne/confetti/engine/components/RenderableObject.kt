package com.zygne.confetti.engine.components

import android.graphics.Canvas

interface RenderableObject {
    fun render(canvas: Canvas)
}