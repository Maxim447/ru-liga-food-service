package ru.liga.mapper.abstraction;

import java.util.List;
public interface ToDtosMapper <E,D>{
    /**
     * Преобразование списка entity в список dto
     * @param entity
     * @return List<dto>
     */
    List<D> toDto (List<E> entity);
}
