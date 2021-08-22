package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx

class Music : EntitySystem() {

    private val music by lazy { Gdx.audio.newMusic(Gdx.files.internal("music.ogg")) }

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        music.play()
    }

    override fun removedFromEngine(engine: Engine) {
        super.removedFromEngine(engine)
        music.stop()
    }
}