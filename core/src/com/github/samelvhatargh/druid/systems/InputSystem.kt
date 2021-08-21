package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.InputMultiplexer
import ktx.app.KtxInputAdapter

open class InputSystem(private val inputMultiplexer: InputMultiplexer) :
    EntitySystem(), KtxInputAdapter {

    override fun addedToEngine(engine: Engine) {
        inputMultiplexer.addProcessor(this)
    }

    override fun removedFromEngine(engine: Engine) {
        inputMultiplexer.removeProcessor(this)
    }
}