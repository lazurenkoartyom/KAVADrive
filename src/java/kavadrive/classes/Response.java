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

/**
 * Class, userd to response to requests. Contains resulting object, 
 * int error code and String error message.
 * 
 * @author Artyom,Aleksey Dziuniak
 */
@XmlRootElement
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

    static public final Response OK = new Response("OK", 0);
    static public final Response EMPTY = new Response("No result for your request.", -1);
    
    public Response() {
    }

    public Response(T entity) {
        this.entity = entity;
        this.errorMessage = "OK";
        this.errorCode = 0;
    }
    
    public Response(String errorMessage, int errorCode) {
        this.entity = null;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
    
    public Response(T entity, String errorMessage, int errorCode) {
        this.entity = entity;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
    public Response(List<T> entityList, String errorMessage, int errorCode) {
        this.entityList = entityList;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public Response(T entity, List<T> entityList, String errorMessage, int errorCode) {
        this.entity = entity;
        this.entityList = entityList;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
