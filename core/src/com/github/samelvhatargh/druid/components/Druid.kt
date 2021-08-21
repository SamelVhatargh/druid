package com.github.samelvhatargh.druid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class Druid : Component, Pool.Poolable {

    var angle = 0f
    var radius = .6f
    var animalsCount = 0

    override fun reset() {
        angle = 0f
        radius = 0f
        animalsCount = 0
    }

    companion object {
        val mapper = mapperFor<Druid>()
    }
}