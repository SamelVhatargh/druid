package com.github.samelvhatargh.druid.screens

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxScreen
import ktx.graphics.use

class PlayScreen(private val batch: SpriteBatch, private val img: Texture, private val camera: Camera) : KtxScreen {

    override fun render(delta: Float) {
        batch.use(camera.combined) {
            batch.draw(img, -.5f, -.5f, 1f, 1f)
        }
    }
}