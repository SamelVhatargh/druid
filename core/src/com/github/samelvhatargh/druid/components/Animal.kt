package com.github.samelvhatargh.druid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor


enum class Species(val sprite: String) {
    COW("cow")
}

class Animal : Component, Pool.Poolable {

    var age = 0f
    var species = Species.COW

    override fun reset() {
        age = 0f
        species = Species.COW
    }

    companion object {
        val mapper = mapperFor<Animal>()
    }
}