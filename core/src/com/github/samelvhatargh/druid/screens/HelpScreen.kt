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

class HelpScreen(
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

                onClick {
                    game.setScreen<PlayScreen>()
                }
                setFillParent(true)
                stack {
                    image(atlas.findRegion("white")) {
                        setColor(0f, 0f, 0f, .4f)
                        width = tableWidth
                    }

                    table {
                        width = tableWidth
                        it.width = tableWidth
                        label("Animals will run towards you. When they reach you, they will stand in a circle around you.") { cell ->
                            cell.padTop(padding).padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            width = tableWidth
                            setAlignment(Align.center)
                            wrap = true
                        }
                        row()
                        label("Move your mouse right or left to rotate the circle of animals to the right or left.") { cell ->
                            cell.padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            width = tableWidth
                            setAlignment(Align.center)
                            wrap = true
                        }
                        row()
                        label("If you put 3 gray animals of the same type next to each other, they will merge into 1 blue one. 3 blue next to each other will merge into 1 gold.") { cell ->
                            cell.padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            setAlignment(Align.center)
                            width = tableWidth
                            wrap = true
                        }
                        row()
                        label("Collect at least one golden animal of each type to win.") { cell ->
                            cell.padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            setAlignment(Align.center)
                            width = tableWidth
                            wrap = true
                        }
                        row()
                        label("Each gathered animal spends one mana. If you run out of mana, you lose.") { cell ->
                            cell.padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            setAlignment(Align.center)
                            width = tableWidth
                            wrap = true
                        }
                        row()
                        label("Beware of the bears! They will eat your animal instead of joining you.") { cell ->
                            cell.padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            setAlignment(Align.center)
                            width = tableWidth
                            wrap = true
                        }
                        row()
                        label("Click anywhere to start.", style = "small") { cell ->
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
        stage.act()
        stage.draw()
    }
}