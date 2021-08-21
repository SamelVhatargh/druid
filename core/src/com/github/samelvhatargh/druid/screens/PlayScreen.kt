package com.github.samelvhatargh.druid.screens

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen
import ktx.graphics.use

class PlayScreen(private val batch: SpriteBatch, private val img: Texture) : KtxScreen {

    override fun render(delta: Float) {
        batch.use {
            batch.draw(img, 0f, 0f)
        }
    }
}