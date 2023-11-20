package ra.academy.service;

import java.util.List;

public interface IGenericService <T,U,E> {
    List<T> findAll();
    T findById(E id);
    void save(U u);
    void delete(E id);
}
