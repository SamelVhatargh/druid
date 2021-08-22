package com.github.samelvhatargh.druid.screens

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.github.samelvhatargh.druid.Config
import com.github.samelvhatargh.druid.DruidGame
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.components.Position
import com.github.samelvhatargh.druid.getCollectedAnimals
import com.github.samelvhatargh.druid.systems.AnimalRender
import com.github.samelvhatargh.druid.systems.LevelRender
import com.github.samelvhatargh.druid.systems.Sound
import com.github.samelvhatargh.druid.systems.SoundEffects
import ktx.actors.onClick
import ktx.ashley.allOf
import ktx.ashley.exclude
import ktx.ashley.get
import ktx.ashley.getSystem
import ktx.scene2d.*
import kotlin.math.floor

class WinScreen(
    engine: Engine,
    private val atlas: TextureAtlas,
    private val game: DruidGame,
    private val inputMultiplexer: InputMultiplexer
) : Screen(engine) {

    private val stage: Stage by lazy {
        Stage(FitViewport(Config.windowWidth.toFloat(), Config.windowHeight.toFloat()))
    }

    var animalPopSpeed = .05f
    var timePast = 0f
    var animalsRendered = 0

    override fun show() {
        engine.getSystem<AnimalRender>().setProcessing(true)
        engine.getSystem<LevelRender>().setProcessing(true)

        engine.removeAllEntities(allOf(Animal::class).exclude(CollectedAnimal::class).get())

        val tableWidth = 1000f
        val padding = 50f
        inputMultiplexer.addProcessor(stage)

        stage.actors {
            table {
                setFillParent(true)
                onClick {
                    Gdx.app.exit()
                }
                stack {
                    image(atlas.findRegion("white")) {
                        setColor(0f, 0f, 0f, .4f)
                        width = tableWidth
                    }

                    table {
                        width = tableWidth
                        it.width = tableWidth
                        label("Congratulations!") { cell ->
                            cell.padTop(padding).padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            width = tableWidth
                            setAlignment(Align.center)
                            wrap = true
                        }
                        row()
                        label("Your companions are powerful indeed.") { cell ->
                            cell.padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            setAlignment(Align.center)
                            width = tableWidth
                            wrap = true
                        }
                        row()
                        label("Now you can call yourself a druid.") { cell ->
                            cell.padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            setAlignment(Align.center)
                            width = tableWidth
                            wrap = true
                        }
                        row()
                        label("Click anywhere to exit.", style = "small") { cell ->
                            cell.padBottom(padding)
                        }

                        pack()
                    }
                }
            }
        }
    }

    override fun hide() {
        super.hide()
        inputMultiplexer.removeProcessor(stage)
    }

    override fun render(delta: Float) {
        super.render(delta)
        renderAnimals(delta)
        stage.act()
        stage.draw()
    }

    private fun renderAnimals(delta: Float) {
        val rowSize = 17f
        timePast += delta
        if (timePast >= animalPopSpeed) {
            val animals = engine.getCollectedAnimals().sortedBy {
                -it[Animal.mapper]!!.level
            }
            if (animals.isNotEmpty() && animalsRendered < animals.size) {
                val animal = animals.get(animalsRendered)
                val dx = animalsRendered % rowSize
                animal[Position.mapper]!!.vec.set(-12f + dx * 1.5f, -5.5f - (floor(animalsRendered/ rowSize) * 1.5f))
                engine.getSystem<SoundEffects>().play(Sound.POP)


                animalsRendered++
            }

            timePast = 0f
        }
    }
}