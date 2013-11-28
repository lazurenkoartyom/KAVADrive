/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import java.util.List;
import kavadrive.entity.Users;
import kavadrive.logic.ServiceException;

/**
 *
 * @author Aleksey
 */
public class UsersDAO extends AbstractDAO<Users> {
    
    private final static Class<Users> ENTITY_CLASS = Users.class;
    
    public enum Parameters {
        ID("userId"),
        TOKEN("token"),
        PASSWORD("userPassword"),
        PHONE("userPhone"),
        EMAIL("email");
    
        private final String name;
        private Parameters(String name){
            this.name=name;
        }

        public String getName(){
            return name;
        }
    }
    
    public UsersDAO() {
    }
    
    public static void create(Users user) throws ServiceException{
        add(user);
    }
    
    public static void edit(Users user) throws ServiceException{
        update(user);
    }    
    
    public static void remove(Users user) throws ServiceException{
        delete(user);
    }     
    
    public static List<Users> findAll() throws ServiceException{
        return getAll(ENTITY_CLASS);
    }
    
    public static Users find(Object id) throws ServiceException{
        return getById(ENTITY_CLASS,id);
    }
    
    public static List<Users> findRange(int[] range) throws ServiceException{
        return getRange(ENTITY_CLASS,range);
    }
    
    public static int count() throws ServiceException{
        return getCount(ENTITY_CLASS);
    }
    
    public static <E> List<Users> findByParameter(Parameters name, E parameterValue) throws ServiceException{
        String nameParameter = name.getName();
        return getByParameter(ENTITY_CLASS, nameParameter, parameterValue);
    }
}

