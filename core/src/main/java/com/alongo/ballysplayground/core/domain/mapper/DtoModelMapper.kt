package com.alongo.ballysplayground.core.domain.mapper

abstract class DtoModelMapper<T, R> {
    fun fromModelToDto(model: R): T = throw NotImplementedError("fromModelToDto in ${this.javaClass.simpleName} has not been implemented")
    abstract fun fromDtoToModel(dto: T): R
}