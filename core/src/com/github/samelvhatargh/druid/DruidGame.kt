package com.github.samelvhatargh.druid

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.samelvhatargh.druid.components.Graphics
import com.github.samelvhatargh.druid.screens.PlayScreen
import com.github.samelvhatargh.druid.systems.CollectedAnimalsPositionCalculator
import com.github.samelvhatargh.druid.systems.Render
import com.github.samelvhatargh.druid.systems.Spawner
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.ashley.entity

class DruidGame : KtxGame<KtxScreen>() {
    private val batch by lazy { SpriteBatch() }
    private val img by lazy { Texture("badlogic.jpg") }
    private val camera by lazy { OrthographicCamera(16f, 9f) }

    private val engine = PooledEngine()

    override fun create() {
        val world = World(engine, img)
        world.create()

        Gdx.input.inputProcessor = PlayerInput(camera, engine)

        engine.apply {
            addSystem(Spawner(img))
            addSystem(CollectedAnimalsPositionCalculator())
            addSystem(Render(batch, camera))
        }

        addScreen(PlayScreen(engine))
        setScreen<PlayScreen>()
    }

    override fun dispose() {
        super.dispose()
        batch.dispose()
        img.dispose()
    }
}