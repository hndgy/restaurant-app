package fr.hndgy.restaurantapp.adapter.out.persistance.common;

public interface EntityMapper<E,D> {
    E toEntity(D domainObject);
    D toDomainObject(E entity);
}
