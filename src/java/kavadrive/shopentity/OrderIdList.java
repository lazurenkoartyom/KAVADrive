package kavadrive.shopentity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aleksey Dziuniak
 */

@XmlRootElement(name="orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderIdList {

   @XmlElement(name="id", type = Integer.class)
   private List<Integer> orders;
    
    public OrderIdList(){
        orders = new ArrayList();
    }
    public void setOrders(List<Integer> orders){
        this.orders = orders;
    }
    
    public List<Integer> getOrders() {
        return orders;
    }
}
