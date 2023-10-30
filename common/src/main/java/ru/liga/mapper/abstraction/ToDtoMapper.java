package ru.liga.mapper.abstraction;

public interface ToDtoMapper<E,D> {
    /**
     * Преобразование entity в dto
     * @param entity
     * @return dto
     */
    D toDto (E entity);
}
