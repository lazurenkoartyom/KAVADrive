package kavadrive.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import kavadrive.entity.Orders;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-04T23:11:05")
@StaticMetamodel(Store.class)
public class Store_ { 

    public static volatile SingularAttribute<Store, String> storeStreet;
    public static volatile SingularAttribute<Store, Date> timeOpen;
    public static volatile SingularAttribute<Store, String> phone;
    public static volatile SingularAttribute<Store, String> storeName;
    public static volatile SingularAttribute<Store, String> storeDescription;
    public static volatile SingularAttribute<Store, Date> timeClose;
    public static volatile ListAttribute<Store, Orders> ordersList;
    public static volatile SingularAttribute<Store, BigDecimal> longitude;
    public static volatile SingularAttribute<Store, BigDecimal> latitude;
    public static volatile SingularAttribute<Store, String> storeTown;
    public static volatile SingularAttribute<Store, Integer> storeId;
    public static volatile SingularAttribute<Store, String> storeHouseNumber;

}