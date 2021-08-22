package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.ashley.remove
import com.github.samelvhatargh.druid.components.Animation as AnimationComponent

class Animation : IteratingSystem(allOf(AnimationComponent::class).get()) {


    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animation = entity[AnimationComponent.mapper]!!

        animation.progress += deltaTime / animation.speed

        if (animation.progress <= .5f) {
            animation.size = 1f + (.5f * animation.progress /.5f)
        } else {
            animation.size = 1.5f - (.5f * (animation.progress - .5f) /.5f)
        }

        if (animation.progress >= 1) {

            entity.remove<AnimationComponent>()
        }
    }

}