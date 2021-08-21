package com.github.samelvhatargh.druid

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Application.LOG_DEBUG
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.viewport.FitViewport
import com.github.samelvhatargh.druid.screens.LoseScreen
import com.github.samelvhatargh.druid.screens.PlayScreen
import com.github.samelvhatargh.druid.screens.StartScreen
import com.github.samelvhatargh.druid.screens.WinScreen
import com.github.samelvhatargh.druid.systems.*
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.scene2d.Scene2DSkin
import ktx.scene2d.label
import ktx.style.label
import ktx.style.skin

class DruidGame : KtxGame<KtxScreen>(clearScreen = false) {
    private val batch by lazy { SpriteBatch() }
    private val camera by lazy { OrthographicCamera(Config.cameraWidth, Config.cameraHeight) }
    private val viewport by lazy { FitViewport(Config.cameraWidth, Config.cameraHeight, camera) }
    private lateinit var spriteAtlas: TextureAtlas

    private val engine = PooledEngine()

    override fun create() {
        spriteAtlas = TextureAtlas(Gdx.files.internal("animals.atlas"))

        Scene2DSkin.defaultSkin = skin {
            label {
                font = BitmapFont(Gdx.files.internal("future.fnt"))
                fontColor = Color.WHITE
                font.data.scale(1f)
            }
            label("small") {
                font = BitmapFont(Gdx.files.internal("future.fnt"))
                fontColor = Color.WHITE
                font.data.scale(.1f)
            }
        }

        Gdx.app.logLevel = LOG_DEBUG

        val world = World(engine)
        world.create()

        val inputMultiplexer = InputMultiplexer()
        Gdx.input.inputProcessor = inputMultiplexer


        val spriteCache = SpriteCache(spriteAtlas)
        val game = this

        engine.apply {
            addSystem(PlayerInput(inputMultiplexer, camera, engine).apply { setProcessing(false) })
            addSystem(Aging().apply { setProcessing(false) })
            addSystem(Spawner(inputMultiplexer, camera, Config.spawnDebug).apply { setProcessing(false) })
            addSystem(Move().apply { setProcessing(false) })
            addSystem(Collect().apply { setProcessing(false) })
            addSystem(LevelUp().apply { setProcessing(false) })
            addSystem(EndGame(game).apply { setProcessing(false) })
            addSystem(CollectedAnimalsPositionCalculator().apply { setProcessing(false) })
            addSystem(MiscRender(batch, camera, spriteCache))
            addSystem(LevelRender(batch, camera).apply { setProcessing(false) })
            addSystem(AnimalRender(batch, camera, spriteCache).apply { setProcessing(false) })
        }

        addScreen(PlayScreen(engine))
        addScreen(LoseScreen(engine, spriteAtlas, this, inputMultiplexer))
        addScreen(WinScreen(engine, spriteAtlas, this, inputMultiplexer))
        addScreen(StartScreen(engine, spriteAtlas, this, inputMultiplexer))
        setScreen<StartScreen>()
    }

    override fun render() {
        clearScreen(39f/255, 174f/255, 96f/255, 1f)
        super.render()
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        viewport.update(width, height)
    }

    override fun dispose() {
        super.dispose()
        batch.dispose()
        spriteAtlas.dispose()
    }
}