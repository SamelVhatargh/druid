package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.components.Druid
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.allOf
import ktx.ashley.exclude
import ktx.ashley.get

class Collect : IteratingSystem(allOf(Animal::class, Position::class).exclude(CollectedAnimal::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val position = entity[Position.mapper]!!
        val druid = engine.getEntitiesFor(allOf(Druid::class).get()).first()[Druid.mapper]!!

        if (position.vec.len() <= druid.radius) {
            entity.add(CollectedAnimal())
        }
    }
}