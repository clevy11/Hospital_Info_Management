package com.example.hospital_info_system.dao;

import java.util.List;

public interface DAO<T> {
    void create(T entity);
    T read(int id);
    void update(T entity);
    void delete(int id);
    List<T> findAll();
}
