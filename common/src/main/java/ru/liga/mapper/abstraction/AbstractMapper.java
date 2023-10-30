package ru.liga.mapper.abstraction;

public interface AbstractMapper <E,D> extends ToEntityMapper<E,D>, ToDtoMapper<E,D>,ToDtosMapper<E,D>{
}