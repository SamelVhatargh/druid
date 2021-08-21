package com.github.samelvhatargh.druid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import com.github.samelvhatargh.druid.Config
import ktx.ashley.mapperFor

class Druid : Component, Pool.Poolable {

    var angle = 0f
    var radius = 1f
    var animalsCount = 0
    var maxMana = Config.maxMana
    var mana = maxMana

    override fun reset() {
        angle = 0f
        radius = 0f
        animalsCount = 0
    }

    companion object {
        val mapper = mapperFor<Druid>()
    }
}