package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Camera
import com.github.samelvhatargh.druid.Config
import com.github.samelvhatargh.druid.getDruid
import ktx.ashley.getSystem
import ktx.math.vec3

class PlayerInput(inputMultiplexer: InputMultiplexer, private val camera: Camera, engine: Engine) :
    InputSystem(inputMultiplexer) {

    private val druid = engine.getDruid()

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        val mouse = camera.unproject(vec3(screenX.toFloat(), screenY.toFloat(), 0f))

        druid.angle = 360 * (mouse.x / Config.fullRotateDistance)
        return true
    }
}