/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.service.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import kavadrive.classes.Criteria;
import kavadrive.classes.ServiceException;
import kavadrive.dao.ProductItemDAO;
import kavadrive.entity.OrderItem;
import kavadrive.entity.ProductItem;

/**
 *
 * @author Aleksey Dziuniak
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"orderItemId","product", 
    "quantity","sum","productItemList"})
class ProductService {
        
    private Integer orderItemId;
    
    private ProductItemService product;
    
    private int quantity;
    
    private double sum;
    
    @XmlElementWrapper(name = "productItemList") 
    @XmlElement(name="productItem")
    private List<ProductItemService> productItemList = 
            new ArrayList<ProductItemService>();
    
    public ProductService(){
        
    }
     
    public ProductService(OrderItem orderItem) throws ServiceException{
        this.orderItemId = orderItem.getOrderItemId();
        this.product = new ProductItemService(orderItem.getProductId());
        this.quantity = orderItem.getProductQuantity();
        this.sum = orderItem.getItemPrice();

        Criteria criterias = new Criteria(ProductItemDAO.Parameters.PRODUCT_ITEM_ID.getName(),
                orderItem.getProductId().getSetListName().getProductItemId().getProductItemId());
                
        List<ProductItem> productList = ProductItemDAO.findByCriterias(criterias);
        for(ProductItem item : productList){
            ProductItemService productItem = new ProductItemService(item,quantity);
            productItemList.add(productItem);
        }
    }
}
