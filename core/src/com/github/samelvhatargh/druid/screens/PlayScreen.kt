package com.github.samelvhatargh.druid.screens

import com.badlogic.ashley.core.Engine
import ktx.app.KtxScreen

class PlayScreen(private val engine: Engine) : KtxScreen {

    override fun render(delta: Float) {
        engine.update(delta)
    }
}