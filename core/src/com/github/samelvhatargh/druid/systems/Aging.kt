package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.github.samelvhatargh.druid.components.Animal
import ktx.ashley.allOf
import ktx.ashley.get

class Aging : IteratingSystem(allOf(Animal::class).get()) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        entity[Animal.mapper]!!.age += deltaTime
    }
}