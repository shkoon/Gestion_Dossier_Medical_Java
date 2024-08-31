package com.dm.sdia.dossiermedicale.dao;
import java.util.List;

public interface Dao <T,U> {

    void save(T o);
    void removeById(U o);
    T getById(U o);
    List<T> getAll();
    void update(T o);
}
