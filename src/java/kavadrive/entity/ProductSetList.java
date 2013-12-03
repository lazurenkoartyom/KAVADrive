/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "product_set_list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductSetList.findAll", query = "SELECT p FROM ProductSetList p"),
    @NamedQuery(name = "ProductSetList.findBySetListId", query = "SELECT p FROM ProductSetList p WHERE p.setListId = :setListId"),
    @NamedQuery(name = "ProductSetList.findBySetListName", query = "SELECT p FROM ProductSetList p WHERE p.setListName = :setListName")})
public class ProductSetList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "set_list_id")
    private String setListId;
    @Basic(optional = false)
    @Column(name = "set_list_name")
    private String setListName;
    @Basic(optional = false)
    @Lob
    @Column(name = "product_description")
    private String productDescription;
    @OneToMany(mappedBy = "setListName")
    private List<Product> productList;
    @JoinColumn(name = "product_item_id", referencedColumnName = "product_item_id")
    @ManyToOne(optional = false)
    private ProductItem productItemId;

    public ProductSetList() {
    }

    public ProductSetList(String setListId) {
        this.setListId = setListId;
    }

    public ProductSetList(String setListId, String setListName, String productDescription) {
        this.setListId = setListId;
        this.setListName = setListName;
        this.productDescription = productDescription;
    }

    public String getSetListId() {
        return setListId;
    }

    public void setSetListId(String setListId) {
        this.setListId = setListId;
    }

    public String getSetListName() {
        return setListName;
    }

    public void setSetListName(String setListName) {
        this.setListName = setListName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @XmlTransient
    @JsonIgnore
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ProductItem getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(ProductItem productItemId) {
        this.productItemId = productItemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (setListId != null ? setListId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductSetList)) {
            return false;
        }
        ProductSetList other = (ProductSetList) object;
        if ((this.setListId == null && other.setListId != null) || (this.setListId != null && !this.setListId.equals(other.setListId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kavadrive.entity.ProductSetList[ setListId=" + setListId + " ]";
    }
    
}
