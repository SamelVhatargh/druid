package com.github.samelvhatargh.druid.screens

import com.badlogic.ashley.core.Engine
import com.github.samelvhatargh.druid.Config
import com.github.samelvhatargh.druid.systems.*
import ktx.app.KtxScreen
import ktx.ashley.getSystem

class PlayScreen(engine: Engine) : Screen(engine) {

    override fun show() {
        super.show()
        engine.getSystem<PlayerInput>().setProcessing(true)
        engine.getSystem<Aging>().setProcessing(true)
        engine.getSystem<Spawner>().setProcessing(true)
        engine.getSystem<Move>().setProcessing(true)
        engine.getSystem<Collect>().setProcessing(true)
        engine.getSystem<LevelUp>().setProcessing(true)
        engine.getSystem<CollectedAnimalsPositionCalculator>().setProcessing(true)
        engine.getSystem<LevelRender>().setProcessing(true)
        engine.getSystem<AnimalRender>().setProcessing(true)
    }

    override fun hide() {
        super.hide()
        engine.getSystem<PlayerInput>().setProcessing(false)
        engine.getSystem<Aging>().setProcessing(false)
        engine.getSystem<Spawner>().setProcessing(false)
        engine.getSystem<Move>().setProcessing(false)
        engine.getSystem<Collect>().setProcessing(false)
        engine.getSystem<LevelUp>().setProcessing(false)
        engine.getSystem<CollectedAnimalsPositionCalculator>().setProcessing(false)
        engine.getSystem<LevelRender>().setProcessing(false)
        engine.getSystem<AnimalRender>().setProcessing(false)
    }

}