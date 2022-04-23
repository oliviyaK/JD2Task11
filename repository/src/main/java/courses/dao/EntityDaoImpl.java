package courses.dao;

import courses.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EntityDaoImpl<T> implements EntityDao {

    private EntityManager em;
    private Class<T> clazz;

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
    public <T> void delete(T object) {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("This element is absent in table");
        }
        em.close();
    }

    @Override
    public <T> void deleteById(T id) {
        em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            em.remove(em.find(clazz, id));
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("This element is absent in table");
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
    public <T> void updateById(int id, T object) {
        em = HibernateUtil.getEntityManager();
        try {
            T obj = (T) clazz.getConstructor().newInstance();

            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("This element is absent in table");
        }
        em.close();
    }

    @Override
    public void select() {
        em = HibernateUtil.getEntityManager();
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e";
        Query query = em.createQuery(queryString);
        List list = query.getResultList();
        list.forEach(System.out::println);
        em.close();
    }
}
