/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import java.util.List;
import kavadrive.entity.OrderItem;
import kavadrive.classes.ServiceException;

/**
 *
 * @author Aleksey Dziuniak
 */
public class OrderItemDAO extends AbstractDAO<OrderItem> {
    
    private final static Class<OrderItem> ENTITY_CLASS = OrderItem.class;
    
    public enum Parameters {
        ID("orderItemId"),
        ORDER("orderId"),
        PRODUCT("productId");
    
    
        private final String name;
        private Parameters(String name){
            this.name=name;
        }

        public String getName(){
            return name;
        }
    }
    
    public OrderItemDAO() {
    }
    
    public static <OrderItem> void create(OrderItem orderItem) throws ServiceException{
        add(orderItem);
    }
    
    public static <OrderItem> void edit(OrderItem orderItem) throws ServiceException{
        update(orderItem);
    }    
    
    public static <OrderItem> void remove(OrderItem orderItem) throws ServiceException{
        delete(orderItem);
    }     
    
    public static List<OrderItem> findAll() throws ServiceException{
        return getAll(ENTITY_CLASS);
    }
    
    public static OrderItem find(Object id) throws ServiceException{
        return getById(ENTITY_CLASS,id);
    }
    
    public static List<OrderItem> findRange(int[] range) throws ServiceException{
        return getRange(ENTITY_CLASS,range);
    }
    
    public static int count() throws ServiceException{
        return getCount(ENTITY_CLASS);
    }
    
    public static <E> List<OrderItem> findByParameter(Parameters name, E parameterValue) throws ServiceException{
        String nameParameter = name.getName();
        return getByParameter(ENTITY_CLASS, nameParameter, parameterValue);
    }

    public static List<OrderItem> clearParameters(List<OrderItem> list, Parameters... names){
        for(Parameters param : names){
            for(OrderItem item : list){
                switch(param){
                    case ORDER: item.setOrderId(null);
                        break;
                    case PRODUCT: item.setProductId(null);
                        break;
                }
            }
        }
        return list;
    }
}

