/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Artyom
 */
@Entity
@Table(name = "product_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductItem.findAll", query = "SELECT p FROM ProductItem p"),
    @NamedQuery(name = "ProductItem.findById", query = "SELECT p FROM ProductItem p WHERE p.id = :id"),
    @NamedQuery(name = "ProductItem.findByProductItemId", query = "SELECT p FROM ProductItem p WHERE p.productItemId = :productItemId"),
    @NamedQuery(name = "ProductItem.findByProductQuantity", query = "SELECT p FROM ProductItem p WHERE p.productQuantity = :productQuantity")})
public class ProductItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "product_item_id")
    private int productItemId;
    @Basic(optional = false)
    @Column(name = "product_quantity")
    private int productQuantity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productItemId")
    private List<ProductSetList> productSetListList;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private Product productId;

    public ProductItem() {
    }

    public ProductItem(Integer id) {
        this.id = id;
    }

    public ProductItem(Integer id, int productItemId, int productQuantity) {
        this.id = id;
        this.productItemId = productItemId;
        this.productQuantity = productQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(int productItemId) {
        this.productItemId = productItemId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @XmlTransient
    @JsonIgnore
    public List<ProductSetList> getProductSetListList() {
        return productSetListList;
    }

    public void setProductSetListList(List<ProductSetList> productSetListList) {
        this.productSetListList = productSetListList;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductItem)) {
            return false;
        }
        ProductItem other = (ProductItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kavadrive.entity.ProductItem[ id=" + id + " ]";
    }
    
}
