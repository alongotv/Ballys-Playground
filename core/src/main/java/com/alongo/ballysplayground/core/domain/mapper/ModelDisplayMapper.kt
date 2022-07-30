package com.alongo.ballysplayground.core.domain.mapper

abstract class ModelDisplayMapper<T, R> {
    fun fromDisplayToModel(display: R): T = throw NotImplementedError("fromDisplayToModel in ${this.javaClass.simpleName} has not been implemented")
    abstract fun fromModelToDisplay(model: T): R
}