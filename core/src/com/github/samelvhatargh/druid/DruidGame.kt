package com.github.samelvhatargh.druid

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Application.LOG_DEBUG
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.github.samelvhatargh.druid.screens.PlayScreen
import com.github.samelvhatargh.druid.systems.*
import ktx.app.KtxGame
import ktx.app.KtxScreen

class DruidGame : KtxGame<KtxScreen>() {
    private val batch by lazy { SpriteBatch() }
    private val camera by lazy { OrthographicCamera(16f, 9f) }
    private lateinit var spriteAtlas: TextureAtlas

    private val engine = PooledEngine()

    override fun create() {
        spriteAtlas = TextureAtlas(Gdx.files.internal("animals.atlas"))

        Gdx.app.logLevel = LOG_DEBUG

        val world = World(engine)
        world.create()

        val inputMultiplexer = InputMultiplexer()
        Gdx.input.inputProcessor = inputMultiplexer


        engine.apply {
            addSystem(PlayerInput(inputMultiplexer, camera, engine))
            addSystem(Aging())
            addSystem(Spawner(inputMultiplexer, camera))
            addSystem(Move())
            addSystem(Collect())
            addSystem(CollectedAnimalsPositionCalculator())
            addSystem(LevelRender(batch, camera))
            addSystem(AnimalRender(batch, camera, SpriteCache(spriteAtlas)))
        }

        addScreen(PlayScreen(engine))
        setScreen<PlayScreen>()
    }

    override fun dispose() {
        super.dispose()
        batch.dispose()
        spriteAtlas.dispose()
    }
}