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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import kavadrive.entity.Category;
import kavadrive.entity.Orders;
import kavadrive.entity.Product;
import kavadrive.entity.Store;
import kavadrive.entity.Users;

/**
 * Class, userd to response to requests, where response is a List of objects. 
 * Contains resulting object, 
 * int error code and String error message.
 * 
 * @author Artyom
 */
@XmlRootElement(name = "Response_List")
@XmlSeeAlso({Users.class, 
    Product.class, 
    Store.class,
    Orders.class,
    Category.class
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"entity", "errorCode", "errorMessage"})

public class Response_List<T> implements Serializable {
    
    static public final Response_List EMPTY = new Response_List(null, "No result for your request.", -1);


    @XmlElement(name="errorMessage")
    private String errorMessage;
    @XmlElement(name="errorCode")
    private int errorCode;
    @XmlElementWrapper(name = "entityList") 
//    @XmlElement(name="entity")
    private List<T> entity ;

    public Response_List() {
    }
    
    public Response_List(List<T> entity) {
        this.entity = entity;
        this.errorMessage = "OK";
        this.errorCode = 0;
    }

    public Response_List(String errorMessage, int errorCode) {
        this.entity = null;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
    
    public Response_List(List<T> entity, String errorMessage, int errorCode) {
        this.entity = entity;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String error) {
        this.errorMessage = error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }    

    /**
     * @return the object, added to response
     */
    public List<T> getEntity() {
        return entity;
    }

    /**
     * @param entity an object to add to Response
     */
    public void setEntity(List<T> entity) {
        this.entity = entity;
    }

}
