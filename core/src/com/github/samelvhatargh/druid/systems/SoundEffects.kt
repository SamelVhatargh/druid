package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx

enum class Sound {
    POP, TURN, MERGE
}

class SoundEffects : EntitySystem() {

    private val pop by lazy { Gdx.audio.newSound(Gdx.files.internal("pop.ogg")) }
    private val turn by lazy { Gdx.audio.newSound(Gdx.files.internal("turn.ogg")) }
    private val merge by lazy { Gdx.audio.newSound(Gdx.files.internal("merge.ogg")) }


    fun play(sound: Sound) {
        when (sound) {
            Sound.POP -> pop.play()
            Sound.TURN -> turn.play()
            Sound.MERGE -> merge.play()
        }
    }
}