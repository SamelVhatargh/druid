package com.github.samelvhatargh.druid.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class CollectedAnimal : Component, Pool.Poolable, Comparable<CollectedAnimal> {

    var id = 0
    var order = 0

    override fun reset() {
        id = 0
        order = 0
    }

    companion object {
        val mapper = mapperFor<CollectedAnimal>()
    }

    override fun compareTo(other: CollectedAnimal): Int {
        return order.compareTo(other.order)
    }
}