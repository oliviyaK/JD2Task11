package courses.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface EntityDao {

    <T> void insert(T object);

    <T> void delete(T object) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException, SQLException;

    <T> void deleteById(T id) throws SQLException;

    <T> void update(T object);

    void select();
}
