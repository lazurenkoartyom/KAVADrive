/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import java.util.List;
import kavadrive.entity.OrderSimpleItem;
import kavadrive.classes.ServiceException;

/**
 *
 * @author Aleksey Dziuniak
 */
public class OrderSimpleItemDAO extends AbstractDAO<OrderSimpleItem> {
    
    private final static Class<OrderSimpleItem> ENTITY_CLASS = OrderSimpleItem.class;
    
    public enum Parameters {
        ID("id");
    
        private final String name;
        private Parameters(String name){
            this.name=name;
        }

        public String getName(){
            return name;
        }
    }
    
    public OrderSimpleItemDAO() {
    }
    
    public static <OrderSimpleItem> void create(OrderSimpleItem orderSimpleItem) throws ServiceException{
        add(orderSimpleItem);
    }
    
    public static <OrderSimpleItem> void edit(OrderSimpleItem orderSimpleItem) throws ServiceException{
        update(orderSimpleItem);
    }    
    
    public static <OrderSimpleItem> void remove(OrderSimpleItem orderSimpleItem) throws ServiceException{
        delete(orderSimpleItem);
    }     
    
    public static List<OrderSimpleItem> findAll() throws ServiceException{
        return getAll(ENTITY_CLASS);
    }
    
    public static OrderSimpleItem find(Object id) throws ServiceException{
        return getById(ENTITY_CLASS,id);
    }
    
    public static List<OrderSimpleItem> findRange(int[] range) throws ServiceException{
        return getRange(ENTITY_CLASS,range);
    }
    
    public static int count() throws ServiceException{
        return getCount(ENTITY_CLASS);
    }
    
    public static <E> List<OrderSimpleItem> findByParameter(Parameters name, E parameterValue) throws ServiceException{
        String nameParameter = name.getName();
        return getByParameter(ENTITY_CLASS, nameParameter, parameterValue);
    }
}
