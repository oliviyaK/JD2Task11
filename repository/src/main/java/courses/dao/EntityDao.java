package courses.dao;

import java.lang.reflect.InvocationTargetException;

public interface EntityDao {

    <T> void insert(T object);

    <T> void delete(T object);

    <T> void deleteById(T id);

    <T> void update(T object);

     <T> void updateById(int id,T object);

    void select();
}
