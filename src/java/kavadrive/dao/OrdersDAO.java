/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import java.util.List;
import kavadrive.entity.Orders;
import kavadrive.classes.ServiceException;

/**
 *
 * @author Aleksey
 */
public class OrdersDAO  extends AbstractDAO<Orders> {
    
    private final static Class<Orders> ENTITY_CLASS = Orders.class;
    
    private static class OrderStatus {
        final static String NEW = "NEW";
        final static String PROCEEDING = "PROCEEDING";
        final static String CLOSED = "CLOSED";
        final static String CANCELED = "CANCELED";
   }
    
    public enum Parameters {
        ID("orderId"),
        USER("userId");
    
        private final String name;
        private Parameters(String name){
            this.name=name;
        }

        public String getName(){
            return name;
        }
    }
    
    public OrdersDAO() {
    }
    
    public static void create(Orders order) throws ServiceException{
        order.setStatus(OrderStatus.NEW);
        add(order);
    }
    
    public static <Orders> void edit(Orders order) throws ServiceException{
        update(order);
    }    
    
    public static <Orders> void remove(Orders order) throws ServiceException{
        delete(order);
    }     
    
    public static List<Orders> findAll() throws ServiceException{
        return getAll(ENTITY_CLASS);
    }
    
    public static Orders find(Object id) throws ServiceException{
        return getById(ENTITY_CLASS,id);
    }
    
    public static List<Orders> findRange(int[] range) throws ServiceException{
        return getRange(ENTITY_CLASS,range);
    }
    
    public static int count() throws ServiceException{
        return getCount(ENTITY_CLASS);
    }
    
    public static <E> List<Orders> findByParameter(Parameters name, E parameterValue) throws ServiceException{
        String nameParameter = name.getName();
        return getByParameter(ENTITY_CLASS, nameParameter, parameterValue);
    }
}