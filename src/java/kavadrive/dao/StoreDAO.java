/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import java.util.List;
import kavadrive.entity.Store;
import kavadrive.classes.ServiceException;

/**
 *
 * @author Aleksey Dziuniak
 */
public class StoreDAO extends AbstractDAO<Store> {
    
    private final static Class<Store> ENTITY_CLASS = Store.class;
    
    public enum Parameters {
        ID("storeId");
    
        private final String name;
        private Parameters(String name){
            this.name=name;
        }

        public String getName(){
            return name;
        }
    }
    
    public StoreDAO() {
    }
    
    public static <Store> void create(Store store) throws ServiceException{
        add(store);
    }
    
    public static <Store> void edit(Store store) throws ServiceException{
        update(store);
    }    
    
    public static <Store> void remove(Store store) throws ServiceException{
        delete(store);
    }     
    
    public static List<Store> findAll() throws ServiceException{
        return getAll(ENTITY_CLASS);
    }
    
    public static Store find(Object id) throws ServiceException{
        return getById(ENTITY_CLASS,id);
    }
    
    public static List<Store> findRange(int[] range) throws ServiceException{
        return getRange(ENTITY_CLASS,range);
    }
    
    public static int count() throws ServiceException{
        return getCount(ENTITY_CLASS);
    }
    
    public static <E> List<Store> findByParameter(Parameters name, E parameterValue) throws ServiceException{
        String nameParameter = name.getName();
        return getByParameter(ENTITY_CLASS, nameParameter, parameterValue);
    }
}
