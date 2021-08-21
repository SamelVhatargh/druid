package com.github.samelvhatargh.druid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor


enum class Species(val sprite: String) {
    COW("cow"),
    DUCK("duck"),
    PIG("pig"),
    CHICK("chick"),
}

class Animal : Component, Pool.Poolable {

    var age = 0f
    var species = Species.COW
    var level = 1

    override fun reset() {
        age = 0f
        species = Species.COW
        level = 1
    }

    companion object {
        val mapper = mapperFor<Animal>()
    }
}