package com.github.samelvhatargh.druid

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.Vector2
import com.github.samelvhatargh.druid.components.Druid
import ktx.app.KtxInputAdapter
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.math.vec2
import ktx.math.vec3

class PlayerInput(private val camera: Camera, engine: Engine) : KtxInputAdapter {

    private val druid = engine.getEntitiesFor(allOf(Druid::class).get()).first()[Druid.mapper]!!

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        val mouse = camera.unproject(vec3(screenX.toFloat(), screenY.toFloat(), 0f))
        druid.angle = vec2(mouse.x, mouse.y).sub(vec2(0f, 0f)).angleDeg()
        return true
    }
}