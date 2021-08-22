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
import ktx.actors.onClick
import ktx.scene2d.*

class StartScreen(
    engine: Engine,
    private val atlas: TextureAtlas,
    private val game: DruidGame,
    private val inputMultiplexer: InputMultiplexer
) : Screen(engine) {

    private val stage: Stage by lazy {
        Stage(FitViewport(Config.windowWidth.toFloat(), Config.windowHeight.toFloat()))
    }

    override fun show() {
        val tableWidth = 1000f
        val padding = 50f
        inputMultiplexer.addProcessor(stage)

        stage.actors {
            table {
                setFillParent(true)
                stack {
                    image(atlas.findRegion("white")) {
                        setColor(0f, 0f, 0f, .4f)
                        width = tableWidth
                    }

                    table {
                        width = tableWidth
                        it.width = tableWidth
                        label("Hello apprentice!") { cell ->
                            cell.padTop(padding).padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            width = tableWidth
                            setAlignment(Align.center)
                            wrap = true
                        }
                        row()
                        label("You have to prove yourself, if you want to become a full member of our druid circle.") { cell ->
                            cell.padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            setAlignment(Align.center)
                            width = tableWidth
                            wrap = true
                        }
                        row()
                        label("Charm animals, train them and show us that you have enough mana to keep them around.") { cell ->
                            cell.padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            setAlignment(Align.center)
                            width = tableWidth
                            wrap = true
                        }
                        row()
                        table {
                            left()
//                            setFillParent(true)
                            bottom()
                            padBottom(padding)
                            stack {
                                it.left().padRight(200f)
                                image(atlas.findRegion("button"))
                                table {
                                    label("How to play") {
                                        it.pad(50f)
                                    }
                                }
                                onClick {
                                    game.setScreen<HelpScreen>()
                                }
                            }
                            stack {
                                it.expandX()
                                image(atlas.findRegion("button"))
                                table {
                                    label("Start") {
                                        it.pad(50f)
                                    }
                                }
                                onClick {
                                    game.setScreen<PlayScreen>()
                                }
                            }
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
        stage.act()
        stage.draw()
    }
}