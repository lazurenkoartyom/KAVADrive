/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import kavadrive.classes.Response;

/**
 *
 * @author Artyom
 */
public abstract class AbstractFacade {
    final static private EntityManagerFactory emf = Persistence.createEntityManagerFactory("KAVADrivePU");
    private Class entityClass;

    public <T> AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public <T> Response create(T entity) {
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = getEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(entity);
            et.commit();
            return Response.OK;
        } catch (Exception e) {
            if ((et != null) && et.isActive()) {
                et.rollback();
            }
            return new Response(null, e.getMessage(), -1);
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }

    public <T>  Response edit(T entity) {
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = getEntityManager();
            et = em.getTransaction();
            et.begin();
            em.merge(entity);
            et.commit();
            return Response.OK;
        } catch (Exception e) {
            if ((et != null) && et.isActive()) {
                et.rollback();
            }
            return new Response(null, e.getMessage(), -1);
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }

    public <T>  Response remove(T entity) {
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = getEntityManager();
            et = em.getTransaction();
            et.begin();
            em.remove(em.merge(entity));
            et.commit();
            return Response.OK;
        } catch (Exception e) {
            if ((et != null) && et.isActive()) {
                et.rollback();
            }
            return new Response(null, e.getMessage(), -1);
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }

    public  <T> Response find(Object id) {
        EntityManager em = null;
        try {            
            em = getEntityManager();
            T found;
            found = (T)em.find(entityClass, id);
            Response resp = 
                    found == null 
                    ? new Response<T>("Object not found in DB", -1) 
                    : new Response<T>(found, "OK", 0);
            return resp;
        } catch (Exception e) {
            return new Response(null, e.getMessage(), -1);
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }

    public <T>  Response findAll() {
        EntityManager em = null;
        try { 
            em = getEntityManager();
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            List<T> found;
            found = em.createQuery(cq).getResultList();
            Response resp = 
                    found.isEmpty()
                    ? new Response<T>("No object found in DB", -1) 
                    : new Response<T>(found, "OK", 0);
            return resp;
        } catch (Exception e) {
            return new Response(null, e.getMessage(), -1);
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }

    public <T>  Response findRange(int[] range) {
//       if ((range[0] < 0) || (range[0] > count()) || (range[0] > range[1]) || (range[1] < 1) || (range[1] > count())) {
//            return new Response(null, "Wrong range.", -1);
//        }
        EntityManager em = null;
        try { 
            em = getEntityManager();
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
            javax.persistence.Query q = em.createQuery(cq);
            q.setMaxResults(range[1] - range[0]);
            q.setFirstResult(range[0]);
            List<T> found;
            found = q.getResultList();
            Response resp = 
                    found.isEmpty()
                    ? new Response("No object found in DB", -1) 
                    : new Response(found, "OK", 0);
            return resp;
        } catch (Exception e) {
            return new Response(null, e.getMessage(), -1);
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }

    public  <T> Response count() {
        EntityManager em = null;
        try { 
            em = getEntityManager();
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
            cq.select(em.getCriteriaBuilder().count(rt));
            javax.persistence.Query q = em.createQuery(cq);
            int count;
            count = ((Long) q.getSingleResult()).intValue();
            return new Response(count, "OK", 0);
//        } catch (Exception e) {
//            return new Response(null, e.getMessage(), -1);
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }
    
    protected Response createMessage(){
        return Response.OK;
    }
    
    protected Response createMessage(String errorMessage){
        return new Response(errorMessage, -1);
    }
    
    protected <T> Response createMessage(List<T> entityList){
         return entityList.isEmpty()
                    ? new Response("No object found in DB", -1) 
                    : new Response(entityList, "OK", 0);
    }
    
    protected  <T,E> Response createMessage(T entity, List<E> entityList){
        return entityList.isEmpty()
                    ? new Response(entity,null,"OK",0) 
                    : new Response(entity,entityList, "OK", 0);
     }
}
