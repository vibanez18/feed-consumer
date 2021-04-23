package com.sparta.infrastructure.rest.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public abstract class AbstractMapper<E, D> implements Mapper<E, D> {

    @Override
    public List<D> fromEntities(List<E> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().map(this::fromEntity).collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    @Override
    public Set<D> fromEntities(Set<E> set) {
        if (set == null) {
            return Collections.emptySet();
        }
        return set.stream().map(this::fromEntity).collect(collectingAndThen(toSet(), Collections::unmodifiableSet));
    }

    @Override
    public List<E> fromDtos(List<D> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return list.stream().map(this::fromDto).collect(Collectors.toList());
    }

    @Override
    public Set<E> fromDtos(Set<D> set) {
        if (set == null) {
            return Collections.emptySet();
        }
        return set.stream().map(this::fromDto).collect(collectingAndThen(toSet(), Collections::unmodifiableSet));
    }

}

