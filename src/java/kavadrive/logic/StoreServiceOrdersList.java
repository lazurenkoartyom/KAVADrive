/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.logic;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import kavadrive.entity.Orders;

/**
 *
 * @author Aleksey Dziuniak
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"storeId", "ordersList"})
public class StoreServiceOrdersList {
    
    @XmlElement(name="storeId")
    private Integer storeId;
    
    @XmlElementWrapper(name = "orders") 
    @XmlElement(name="order")
    private List<Orders> ordersList ;
    
    StoreServiceOrdersList(){
        
    }    
    
    StoreServiceOrdersList(Integer storeId, List<Orders> list) {
        this.storeId = storeId;
        this.ordersList = list;
    }
    
    public void setOrdersList(List<Orders> list){
        this.ordersList = list;
    }
    
    public List<Orders> getOrdersList(){
        return this.ordersList;
    }   
}
