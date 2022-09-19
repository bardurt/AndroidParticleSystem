package com.zygne.confetti.engine.components

import android.graphics.Canvas

interface RendableObject {
    fun render(canvas: Canvas)
}