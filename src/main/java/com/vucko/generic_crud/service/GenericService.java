package com.vucko.generic_crud.service;


public interface GenericService<T,DTO,ID> {
    Iterable<DTO> findAll();
    DTO findById(ID id);
    boolean existsById(ID id);
    T save(T t);
    void update(DTO dto, ID id);
    void remove(ID id);
    }

