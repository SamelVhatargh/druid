package com.github.samelvhatargh.druid

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
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

        val world = World(engine)
        world.create()

        Gdx.input.inputProcessor = PlayerInput(camera, engine)

        engine.apply {
            addSystem(Aging())
            addSystem(Spawner())
            addSystem(Move())
            addSystem(Collect())
            addSystem(CollectedAnimalsPositionCalculator())
            addSystem(Render(batch, camera, SpriteCache(spriteAtlas)))
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