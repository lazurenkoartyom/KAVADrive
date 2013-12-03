/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Artyom
 */
@Entity
@Table(name = "order_simple_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderSimpleItem.findAll", query = "SELECT o FROM OrderSimpleItem o"),
    @NamedQuery(name = "OrderSimpleItem.findByOrderSimpleItemId", query = "SELECT o FROM OrderSimpleItem o WHERE o.orderSimpleItemId = :orderSimpleItemId"),
    @NamedQuery(name = "OrderSimpleItem.findByProductQuantity", query = "SELECT o FROM OrderSimpleItem o WHERE o.productQuantity = :productQuantity")})
public class OrderSimpleItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_simple_item_id")
    private Integer orderSimpleItemId;
    @Column(name = "product_quantity")
    private Integer productQuantity;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne
    private Product productId;
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne
    private Orders orderId;

    public OrderSimpleItem() {
    }

    public OrderSimpleItem(Integer orderSimpleItemId) {
        this.orderSimpleItemId = orderSimpleItemId;
    }

    public Integer getOrderSimpleItemId() {
        return orderSimpleItemId;
    }

    public void setOrderSimpleItemId(Integer orderSimpleItemId) {
        this.orderSimpleItemId = orderSimpleItemId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderSimpleItemId != null ? orderSimpleItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderSimpleItem)) {
            return false;
        }
        OrderSimpleItem other = (OrderSimpleItem) object;
        if ((this.orderSimpleItemId == null && other.orderSimpleItemId != null) || (this.orderSimpleItemId != null && !this.orderSimpleItemId.equals(other.orderSimpleItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kavadrive.entity.OrderSimpleItem[ orderSimpleItemId=" + orderSimpleItemId + " ]";
    }
    
}
