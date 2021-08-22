package com.github.samelvhatargh.druid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class Animation : Component, Pool.Poolable {

    var progress = 0f
    var size = 1f
    var speed = .25f

    override fun reset() {
        progress = 0f
        size = 1f
        speed = 1f
    }

    companion object {
        val mapper = mapperFor<Animation>()
    }
}