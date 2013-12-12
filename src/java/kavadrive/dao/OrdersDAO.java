/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import kavadrive.classes.Criteria;
import java.util.ArrayList;
import java.util.List;
import kavadrive.entity.Orders;
import kavadrive.classes.ServiceException;
import kavadrive.entity.OrderItem;

/**
 *
 * @author Aleksey
 */
public class OrdersDAO  extends AbstractDAO<Orders> {

    private final static Class<Orders> ENTITY_CLASS = Orders.class;
    
    public static class Status {
        public final static String NEW = "NEW";
        public final static String ACCEPTED = "ACCEPT";
        public final static String COMPLETED = "COMPLETE";
        public final static String CANCELLED = "CANCEL";
    }

    public enum Parameters {
        ID("orderId"),
        USER("userId"),
        STORE("storeId"),
        STATUS("status");
    
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
        order.setStatus(Status.NEW);
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
    
    public static <E> List<Orders> findByCriterias(Criteria... criterias) throws ServiceException{
         return getByCriterias(ENTITY_CLASS, criterias);
    }
    
    public static List<Orders> clearParameters(List<Orders> list, OrdersDAO.Parameters... names) throws ServiceException{
        for(OrdersDAO.Parameters param : names){
            setParameterInEntityList(list, param, null);
        }
        return list;
    }

    public static void setParameterInEntityList(List<Orders> list, Parameters param, String value) throws ServiceException {
        for(Orders item : list){
            switch(param){
                case STATUS: item.setStatus(value);
                    edit(item);
                    break;
            }
        }
    }
    
    public static List<Orders> getOrdersByListIds(List<Integer> ids) throws ServiceException{
        List<Orders> orders = new ArrayList();
        for (Integer id : ids){
            Orders order = find(id);
            orders.add(order);
        }
        return orders;
    }
            
}