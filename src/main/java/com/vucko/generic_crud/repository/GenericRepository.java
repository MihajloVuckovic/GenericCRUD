package com.vucko.generic_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T,DTO,ID> extends JpaRepository<T,ID> {
}
