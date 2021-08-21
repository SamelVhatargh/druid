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

class LoseScreen(
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
                        label("Sorry!") { cell ->
                            cell.padTop(padding).padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            width = tableWidth
                            setAlignment(Align.center)
                            wrap = true
                        }
                        row()
                        label("You seem to be out of mana.") { cell ->
                            cell.padBottom(padding).fillX().expandX().center().minWidth(tableWidth)
                                .prefWidth(tableWidth)
                            setAlignment(Align.center)
                            width = tableWidth
                            wrap = true
                        }
                        row()
                        label("You still have a lot to learn\n before becoming a full-fledged druid.") { cell ->
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
        stage.act()
        stage.draw()
    }
}