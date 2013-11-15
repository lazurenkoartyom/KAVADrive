/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.logic;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import kavadrive.entity.Users;
import kavadrive.service.AbstractFacade;

/**
 *
 * @author Artyom
 */
public class UserDAO extends AbstractFacade<Users>{
    private UserDAO() {
        super(Users.class);
    }
    
    public static Users findByPhone(String phone) throws ServiceException {
        List found;
        EntityManager em = AbstractFacade.getEntityManager();
        //Searching by phone number
        found = em.createNamedQuery("Users.findByUserPhone", Users.class)
                .setParameter("userPhone", phone)
                .getResultList();
        em.close();
        switch (((Integer)found.size()).compareTo(1)) {
            case -1: 
                return null;
            case 0:
                return (Users)found.get(0);
            default:
                throw new ServiceException("DataBase inconsistancy. Multiple PhoneNumbers existing.");
        }
    }

    public static Users findByEMail(String email) throws ServiceException {
        List found;
        EntityManager em = AbstractFacade.getEntityManager();
        //Searching by e-mail
        found = em.createNamedQuery("Users.findByEmail", Users.class)
                .setParameter("email", email)
                .getResultList();
        em.close();
        switch (((Integer)found.size()).compareTo(1)) {
            case -1: 
                return null;
            case 0:
                return (Users)found.get(0);
            default:
                throw new ServiceException("DataBase inconsistancy. Multiple emails existing.");
        }
    }

    public static Users findById(Integer id) {
        Users found;
        try {
            EntityManager em = AbstractFacade.getEntityManager();
            found = em.createNamedQuery("Users.findByUserId", Users.class)
                    .setParameter("userId", id)
                    .getSingleResult();
            return found;
        } catch (Exception ex) {
            //Writing to log
            return null;
        }
    }
    
        
    public static Users findByToken(String token) throws ServiceException {
        List found;
        EntityManager em = AbstractFacade.getEntityManager();
        found = em.createNamedQuery("Users.findByToken", Users.class)
                .setParameter("token", token)
                .getResultList();
        em.close();
        switch (((Integer)found.size()).compareTo(1)) {
            case -1: 
                return null;
            case 0:
                return (Users)found.get(0);
            default:
                throw new ServiceException("DataBase inconsistancy. Multiple Tokens existing.");
        }
    }
   
    /****************************************** 
    * !!!The same method is in AbsractFacade.java
    *       but with another return value
    *******************************************/
    public static void update(Users entity) throws ServiceException {
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = AbstractFacade.getEntityManager();
            et = em.getTransaction();
            et.begin();
            em.merge(entity);
            et.commit();
        } catch (Exception e) {
            if ((et != null) && et.isActive()) {
                et.rollback();
            }
            throw new ServiceException("Fail Updating DataBase.");
        } finally {
            if ((em != null) && em.isOpen()) {
                em.close();
            }
        }
    }  
}            
