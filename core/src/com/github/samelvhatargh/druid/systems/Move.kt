package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.allOf
import ktx.ashley.exclude
import ktx.ashley.get
import ktx.math.vec2

const val speed = 1f

class Move : IteratingSystem(allOf(Animal::class, Position::class).exclude(CollectedAnimal::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val position = entity[Position.mapper]!!
        val animal = entity[Animal.mapper]!!

        val alpha = animal.age / speed
        if (alpha <= 1f) {
            position.vec.setLength(spawnDistance).lerp(vec2(0f, 0f), alpha)
        }
    }
}