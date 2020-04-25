package com.zygne.confetti.engine.components

import android.graphics.Canvas

/**
 * Created by Bardur Thomsen on 9/13/18.
 */
interface RendableObject {
    fun render(canvas: Canvas)
}