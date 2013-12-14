/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.service.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import kavadrive.classes.ServiceException;
import kavadrive.entity.Orders;
import kavadrive.entity.Store;

/**
 *
 * @author Aleksey Dziuniak
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"storeId","name","town","street",
    "houseNumber", "ordersList"})
public class StoreServiceOrdersList {
    
    private Integer storeId;
    
    private String name;
    
    private String town;
    
    private String street;

    private String houseNumber;

    @XmlElementWrapper(name = "orders") 
    @XmlElement(name="order")
    private List<OrderService> ordersList = new ArrayList<OrderService>() ;
    
    public StoreServiceOrdersList(){
        
    }    
    
    public StoreServiceOrdersList(Store store, List<Orders> list) throws ServiceException {
        this.storeId = store.getStoreId();
        for(Orders item : list){
            OrderService order = new OrderService(item);
            ordersList.add(order);
        }
    }
}
