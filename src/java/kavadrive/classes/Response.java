/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.classes;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import kavadrive.entity.Category;
import kavadrive.entity.OrderItem;
import kavadrive.entity.OrderSimpleItem;
import kavadrive.entity.Orders;
import kavadrive.entity.Product;
import kavadrive.entity.ProductItem;
import kavadrive.entity.ProductSetList;
import kavadrive.entity.Role;
import kavadrive.entity.Store;
import kavadrive.entity.Users;
import kavadrive.logic.StoreServiceOrdersList;

/**
 * Class, userd to response to requests. Contains resulting object, 
 * int error code and String error message.
 * 
 * @author Artyom,Aleksey Dziuniak
 */
@XmlRootElement
@XmlSeeAlso({Users.class, 
    Product.class, 
    Store.class,
    Orders.class,
    Category.class
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"entity", "entityList", "errorMessage", "errorCode"})

public class Response<T> implements Serializable {

    @XmlElement(name="errorMessage")
    private String errorMessage;
    
    @XmlElement(name="errorCode")
    private int errorCode;
    
    @XmlElements({
        @XmlElement(name="Category",        type = Category.class),
        @XmlElement(name="OrderItem",       type = OrderItem.class),
        @XmlElement(name="OrderSimpleItem", type = OrderSimpleItem.class),
        @XmlElement(name="Orders",          type = Orders.class),
        @XmlElement(name="Product",         type = Product.class),
        @XmlElement(name="ProductItem",     type = ProductItem.class),
        @XmlElement(name="ProductSetList",  type = ProductSetList.class),
        @XmlElement(name="Role",            type = Role.class),
        @XmlElement(name="Store",           type = Store.class),
        @XmlElement(name="Users",           type = Users.class),
        @XmlElement(name="Count",           type = Integer.class),
        @XmlElement(name="ShopOrders",      type = StoreServiceOrdersList.class),
    })
    private T entity ;
    
    @XmlElementWrapper(name = "entityList") 
    @XmlElements({
        @XmlElement(name="Category",        type = Category.class),
        @XmlElement(name="OrderItem",       type = OrderItem.class),
        @XmlElement(name="OrderSimpleItem", type = OrderSimpleItem.class),
        @XmlElement(name="Orders",          type = Orders.class),
        @XmlElement(name="Product",         type = Product.class),
        @XmlElement(name="ProductItem",     type = ProductItem.class),
        @XmlElement(name="ProductSetList",  type = ProductSetList.class),
        @XmlElement(name="Role",            type = Role.class),
        @XmlElement(name="Store",           type = Store.class),
        @XmlElement(name="Users",           type = Users.class),
    })
    private List<T> entityList ;

    static public final Response OK = new Response(Message.OK);
    static public final Response EMPTY = new Response(Message.NO_RESULT);
    
    public Response(){
        
    }
    
    public Response(Message message) {
        this.entity = null;
        this.entityList = null;
        this.errorMessage = message.getMessage();
        this.errorCode = message.getCode();
    }

    public Response(T entity) {
        this(Message.OK);
        this.entity = entity;
    }
    
    public Response(List<T> entityList) {
        this(Message.OK);
        this.entityList = entityList;
    }

    public Response(T entity, List<T> entityList) {
        this(Message.OK);
        this.entity = entity;
        this.entityList = entityList;
    }
    
    public static Response create(){
        return Response.OK;
    }
    
    public static Response create(Message message){
        return new Response(message);
    }   
    
    public static Response create(int count){
        return new Response(count);
    }
    
    public static <T> Response create(T entity){
        return  new Response(entity);
    }
    
    public static <T> Response create(List<T> entityList){
         return entityList.isEmpty()
                    ? new Response(Message.NOT_FOUND_IN_DB) 
                    : new Response(entityList);
    }
    
    public static <T,E> Response create(T entity, List<E> entityList){
        return entityList.isEmpty()
                    ? new Response(entity) 
                    : new Response(entity, entityList);
     }
}
