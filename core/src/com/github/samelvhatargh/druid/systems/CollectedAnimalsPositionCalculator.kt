package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.components.Druid
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.allOf
import ktx.ashley.get

class CollectedAnimalsPositionCalculator : EntitySystem() {



    override fun update(deltaTime: Float) {
        super.update(deltaTime)

        val druid = engine.getEntitiesFor(allOf(Druid::class).get()).first()[Druid.mapper]!!
        val entities = engine.getEntitiesFor(allOf(Animal::class, CollectedAnimal::class, Position::class).get())
        val totalCount = entities.size()

        for (i in 0 until totalCount) {
            val position = entities[i][Position.mapper]!!

            position.vec.set(0f, 1f)
            position.vec.rotateDeg(i * (360 / totalCount).toFloat() + druid.angle)
        }
    }
}