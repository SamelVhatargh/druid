package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx

enum class Sound {
    POP, MERGE
}

class SoundEffects : EntitySystem() {

    private val pop by lazy { Gdx.audio.newSound(Gdx.files.internal("pop.ogg")) }
    private val merge by lazy { Gdx.audio.newSound(Gdx.files.internal("merge.ogg")) }

    fun play(sound: Sound) {
        when (sound) {
            Sound.POP -> pop.play()
            Sound.MERGE -> {
                pop.stop()
                merge.play()
            }
        }
    }
}