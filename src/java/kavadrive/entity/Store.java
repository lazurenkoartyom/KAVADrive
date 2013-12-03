/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Artyom
 */
@Entity
@Table(name = "store")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s"),
    @NamedQuery(name = "Store.findByStoreId", query = "SELECT s FROM Store s WHERE s.storeId = :storeId"),
    @NamedQuery(name = "Store.findByStoreName", query = "SELECT s FROM Store s WHERE s.storeName = :storeName"),
    @NamedQuery(name = "Store.findByTimeOpen", query = "SELECT s FROM Store s WHERE s.timeOpen = :timeOpen"),
    @NamedQuery(name = "Store.findByTimeClose", query = "SELECT s FROM Store s WHERE s.timeClose = :timeClose"),
    @NamedQuery(name = "Store.findByStoreStreet", query = "SELECT s FROM Store s WHERE s.storeStreet = :storeStreet"),
    @NamedQuery(name = "Store.findByStoreHouseNumber", query = "SELECT s FROM Store s WHERE s.storeHouseNumber = :storeHouseNumber"),
    @NamedQuery(name = "Store.findByStoreTown", query = "SELECT s FROM Store s WHERE s.storeTown = :storeTown"),
    @NamedQuery(name = "Store.findByLatitude", query = "SELECT s FROM Store s WHERE s.latitude = :latitude"),
    @NamedQuery(name = "Store.findByLongitude", query = "SELECT s FROM Store s WHERE s.longitude = :longitude"),
    @NamedQuery(name = "Store.findByPhone", query = "SELECT s FROM Store s WHERE s.phone = :phone"),
    @NamedQuery(name = "Store.findByStoreDescription", query = "SELECT s FROM Store s WHERE s.storeDescription = :storeDescription")})
public class Store implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "store_id")
    private Integer storeId;
    @Basic(optional = false)
    @Column(name = "store_name")
    private String storeName;
    @Basic(optional = false)
    @Column(name = "time_open")
    @Temporal(TemporalType.TIME)
    private Date timeOpen;
    @Basic(optional = false)
    @Column(name = "time_close")
    @Temporal(TemporalType.TIME)
    private Date timeClose;
    @Basic(optional = false)
    @Column(name = "store_street")
    private String storeStreet;
    @Basic(optional = false)
    @Column(name = "store_house_number")
    private String storeHouseNumber;
    @Column(name = "store_town")
    private String storeTown;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Basic(optional = false)
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Column(name = "phone")
    private String phone;
    @Column(name = "store_description")
    private String storeDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeId")
    private List<Orders> ordersList;

    public Store() {
    }

    public Store(Integer storeId) {
        this.storeId = storeId;
    }

    public Store(Integer storeId, String storeName, Date timeOpen, Date timeClose, String storeStreet, String storeHouseNumber, BigDecimal latitude, BigDecimal longitude) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.storeStreet = storeStreet;
        this.storeHouseNumber = storeHouseNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(Date timeOpen) {
        this.timeOpen = timeOpen;
    }

    public Date getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(Date timeClose) {
        this.timeClose = timeClose;
    }

    public String getStoreStreet() {
        return storeStreet;
    }

    public void setStoreStreet(String storeStreet) {
        this.storeStreet = storeStreet;
    }

    public String getStoreHouseNumber() {
        return storeHouseNumber;
    }

    public void setStoreHouseNumber(String storeHouseNumber) {
        this.storeHouseNumber = storeHouseNumber;
    }

    public String getStoreTown() {
        return storeTown;
    }

    public void setStoreTown(String storeTown) {
        this.storeTown = storeTown;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
    }

    @XmlTransient
    @JsonIgnore
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeId != null ? storeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Store)) {
            return false;
        }
        Store other = (Store) object;
        if ((this.storeId == null && other.storeId != null) || (this.storeId != null && !this.storeId.equals(other.storeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kavadrive.entity.Store[ storeId=" + storeId + " ]";
    }
    
}
