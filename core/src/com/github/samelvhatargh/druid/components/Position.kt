package com.github.samelvhatargh.druid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor
import ktx.math.vec2

class Position : Component, Pool.Poolable {

    var vec: Vector2 = vec2()

    override fun reset() {
        vec = vec2()
    }

    companion object {
        val mapper = mapperFor<Position>()
    }
}