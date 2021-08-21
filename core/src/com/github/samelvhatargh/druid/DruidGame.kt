package com.github.samelvhatargh.druid

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.samelvhatargh.druid.screens.PlayScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen

class DruidGame : KtxGame<KtxScreen>() {
    private val batch by lazy { SpriteBatch() }
    private val img by lazy { Texture("badlogic.jpg") }
    private val camera by lazy { OrthographicCamera(16f, 9f) }

    override fun create() {
        addScreen(PlayScreen(batch, img, camera))
        setScreen<PlayScreen>()
    }

    override fun dispose() {
        super.dispose()
        batch.dispose()
        img.dispose()
    }
}