package courses.dao;

import courses.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

public class EntityDaoImpl<T,K> implements EntityDao {

    private EntityManager em;
    private final Class<T> clazz;

    public EntityDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public <T> void insert(T object) {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public <T> void delete(T object) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException, SQLException {
        deleteById(getIdOfObject(object));
    }

    @Override
    public <T> void deleteById(T id) {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        try {
        em.remove(em.find(clazz, id));
        em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("This element is absent in table or could not be deleted");
        }
        em.close();
    }

    @Override
    public <T> void update(T object) {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void select() {
        em = HibernateUtil.getEntityManager();
        System.out.println("@@@@@@@@@@@" + clazz.getSimpleName());
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e";
        Query query = em.createQuery(queryString);
        List list = query.getResultList();
        list.forEach(System.out::println);
        em.close();
    }

    private <T>  K getIdOfObject(T object)
            throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        Method method = object.getClass().getMethod("getId");
        K id = (K) method.invoke(object);
        System.out.println("id = " + id);
        return id;
    }
}
