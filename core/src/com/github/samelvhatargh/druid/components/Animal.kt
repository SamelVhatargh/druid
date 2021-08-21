package com.github.samelvhatargh.druid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor



class Animal : Component, Pool.Poolable {

    var age = 0f

    override fun reset() {
        age = 0f
    }

    companion object {
        val mapper = mapperFor<Animal>()
    }
}