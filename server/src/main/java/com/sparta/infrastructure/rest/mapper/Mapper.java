package com.sparta.infrastructure.rest.mapper;

import java.util.List;
import java.util.Set;

public interface Mapper<E, D> {

    D fromEntity(E entity);

    List<D> fromEntities(List<E> collection);

    Set<D> fromEntities(Set<E> set);

    E fromDto(D dto);

    List<E> fromDtos(List<D> collection);

    Set<E> fromDtos(Set<D> set);

}
