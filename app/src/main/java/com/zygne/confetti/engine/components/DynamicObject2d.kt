package com.zygne.confetti.engine.components

import android.graphics.Canvas
import com.zygne.confetti.engine.math.Rectangle
import com.zygne.confetti.engine.math.Vector2

abstract class DynamicObject2d(x : Float, y : Float) : RenderableObject, UpdatableObject {
    var position: Vector2? = null
    var bounds: Rectangle? = null
    var velocity: Vector2? = null

    init {
        position = Vector2(x, y)
        bounds = Rectangle(x, y, 0f, 0f)
        velocity = Vector2()
    }

    override fun render(canvas: Canvas) {}
    override fun update(dt: Float) {}

}