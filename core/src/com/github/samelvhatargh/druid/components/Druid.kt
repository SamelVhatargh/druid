package com.github.samelvhatargh.druid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class Druid : Component, Pool.Poolable {

    var angle = 0f

    override fun reset() {
        angle = 0f
    }

    companion object {
        val mapper = mapperFor<Druid>()
    }
}