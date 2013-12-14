/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.service.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import kavadrive.classes.Criteria;
import kavadrive.classes.ServiceException;
import kavadrive.dao.OrderItemDAO;
import kavadrive.entity.OrderItem;
import kavadrive.entity.Orders;
import kavadrive.entity.Users;

/**
 *
 * @author Aleksey Dziuniak
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"orderId", "quantity","price",
    "dateStart","status","user","productList"})
public class OrderService {
    
    private Integer orderId;
    
    private int quantity;

    private BigDecimal price;
    
    private Date dateStart;
    
    private String status;
    
    private UserService user;
    
    
    @XmlElementWrapper(name = "products") 
    @XmlElement(name="product")
    private List<ProductService> productList = new ArrayList<ProductService>();
    
    public OrderService(){
        
    }
    
    public OrderService(Orders order) throws ServiceException{
        this.orderId = order.getOrderId();
        this.quantity = order.getItemQuantity();
        this.price = order.getPrice();
        this.dateStart = order.getDateStart();
        this.status = order.getStatus();
        this.user = new UserService(order.getUserId());
        
        Criteria criterias = new Criteria(
                OrderItemDAO.Parameters.ORDER.getName(),order);
                
        List<OrderItem> list = OrderItemDAO.findByCriterias(criterias);
        for(OrderItem item : list){
            ProductService product = new ProductService(item);
            productList.add(product);
        }
    }
    
}
