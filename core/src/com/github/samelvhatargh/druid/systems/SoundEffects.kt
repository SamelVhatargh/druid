package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx

enum class Sound {
    POP, MERGE, BEAR, EAT
}

class SoundEffects : EntitySystem() {

    private val pop by lazy { Gdx.audio.newSound(Gdx.files.internal("pop.ogg")) }
    private val merge by lazy { Gdx.audio.newSound(Gdx.files.internal("merge.ogg")) }
    private val bear by lazy { Gdx.audio.newSound(Gdx.files.internal("bear.ogg")) }
    private val eat by lazy { Gdx.audio.newSound(Gdx.files.internal("eat.ogg")) }

    fun play(sound: Sound) {
        when (sound) {
            Sound.POP -> pop.play()
            Sound.BEAR -> {
                val id = bear.play()
                bear.setVolume(id, .1f)
                bear.setPitch(id, 2f)
            }
            Sound.EAT -> {
                val id = eat.play()
                bear.setVolume(id, .25f)
                bear.setPitch(id, .5f)
            }
            Sound.MERGE -> {
                pop.stop()
                merge.play()
            }
        }
    }
}