/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import java.util.List;
import kavadrive.entity.Role;
import kavadrive.classes.ServiceException;

/**
 *
 * @author Aleksey Dziuniak
 */
public class RoleDAO extends AbstractDAO<Role> {
    
    private final static Class<Role> ENTITY_CLASS = Role.class;
    
    public enum Parameters {
        ID("roleId");
    
        private final String name;
        private Parameters(String name){
            this.name=name;
        }

        public String getName(){
            return name;
        }
    }
    
    public RoleDAO() {
    }
    
    public static <Role> void create(Role role) throws ServiceException{
        add(role);
    }
    
    public static <Role> void edit(Role role) throws ServiceException{
        update(role);
    }    
    
    public static <Role> void remove(Role role) throws ServiceException{
        delete(role);
    }     
    
    public static List<Role> findAll() throws ServiceException{
        return getAll(ENTITY_CLASS);
    }
    
    public static Role find(Object id) throws ServiceException{
        return getById(ENTITY_CLASS,id);
    }
    
    public static List<Role> findRange(int[] range) throws ServiceException{
        return getRange(ENTITY_CLASS,range);
    }
    
    public static int count() throws ServiceException{
        return getCount(ENTITY_CLASS);
    }
    
    public static <E> List<Role> findByParameter(Parameters name, E parameterValue) throws ServiceException{
        String nameParameter = name.getName();
        return getByParameter(ENTITY_CLASS, nameParameter, parameterValue);
    }
}
