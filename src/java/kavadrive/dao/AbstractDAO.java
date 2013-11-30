/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import kavadrive.classes.ServiceException;

/**
 *
 * @author Aleksey Dziuniak
 */
public abstract class AbstractDAO<T> {
    final static private EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("KAVADrivePU");
    
    public AbstractDAO() {
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
 
    protected static <T> void add(T entity) throws ServiceException {
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = getEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(entity);
            et.commit();
        } catch (Exception e) {
            if ((et != null) && et.isActive()) {
                et.rollback();
            }
            throw new ServiceException(e.getMessage());
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }

    protected static <T> void update(T entity) throws ServiceException {
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = getEntityManager();
            et = em.getTransaction();
            et.begin();
            em.merge(entity);
            et.commit();
        } catch (Exception e) {
            if ((et != null) && et.isActive()) {
                et.rollback();
            }
            throw new ServiceException(e.getMessage());
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }

    protected static <T> void delete(T entity) throws ServiceException {
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = getEntityManager();
            et = em.getTransaction();
            et.begin();
            em.remove(em.merge(entity));
            et.commit();
        } catch (Exception e) {
            if ((et != null) && et.isActive()) {
                et.rollback();
            }
            throw new ServiceException(e.getMessage());
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }

    protected static <T> T getById(Class entityClass, Object id) throws ServiceException {
        EntityManager em = null;
        try {            
            em = getEntityManager();
            T found = (T)em.find(entityClass, id);
            return found;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }
    
    protected static <T> List<T> getAll(Class entityClass) throws ServiceException {
        EntityManager em = null;
        try { 
            em = getEntityManager();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            List<T> found = em.createQuery(cq).getResultList();
            return found;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }

    protected static <T> List<T> getRange(Class entityClass, int[] range) throws ServiceException {
        EntityManager em = null;
        try { 
            em = getEntityManager();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            Query q = em.createQuery(cq);
            q.setMaxResults(range[1] - range[0]);
            q.setFirstResult(range[0]);
            List<T> found = q.getResultList();
            return found;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }

    protected static <T> int getCount(Class entityClass) throws ServiceException {
        EntityManager em = null;
        try { 
            em = getEntityManager();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<T> rt = cq.from(entityClass);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            int count = ((Long) q.getSingleResult()).intValue();
            return count;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }
    
    protected static <T,E> List<T> getByParameter(Class entityClass, 
            String parameterName, E parameterValue) throws ServiceException {
        
        EntityManager em = null;
        try { 
            em = getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root e = cq.from(entityClass);
            cq.where(cb.equal(e.get(parameterName),parameterValue));
            Query query = em.createQuery(cq);
            List<T> list = query.getResultList();
            return list;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }
}
