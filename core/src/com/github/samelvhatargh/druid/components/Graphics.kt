package com.github.samelvhatargh.druid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class Graphics(var texture: Texture?) : Component, Pool.Poolable {

    override fun reset() {
        texture = null
    }

    companion object {
        val mapper = mapperFor<Graphics>()
    }
}