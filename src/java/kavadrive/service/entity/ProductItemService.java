/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.service.entity;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import kavadrive.entity.Product;
import kavadrive.entity.ProductItem;

/**
 *
 * @author Aleksey Dziuniak
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"productId", "name", "price", "quantity", "attribute","sum"})
class ProductItemService {
    
    private Integer productId;
    
    private String name;
    
    private Integer quantity;
    
    private String attribute;
    
    private BigDecimal price;
    
    private BigDecimal sum;
    
    public ProductItemService(){
        
    }
    
    public ProductItemService(Product product){
        this.productId = product.getProductId();
        this.name = product.getProductName();
        this.price = product.getPrice();
    }
    
    public ProductItemService(ProductItem productItem,int orderQuantity){
        this.productId = productItem.getProductId().getProductId();
        this.name = productItem.getProductId().getProductName();
        this.price = productItem.getProductId().getPrice();
        this.quantity = productItem.getProductQuantity()*orderQuantity;
        this.attribute = productItem.getProductId().getProductAttribute();
        this.sum = productItem.getProductId().getPrice().
                multiply(BigDecimal.valueOf(quantity));
    }
}
