package kavadrive.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import kavadrive.entity.OrderItem;
import kavadrive.entity.OrderSimpleItem;
import kavadrive.entity.Store;
import kavadrive.entity.Users;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-04T23:11:05")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, BigDecimal> price;
    public static volatile SingularAttribute<Orders, Integer> itemQuantity;
    public static volatile SingularAttribute<Orders, String> status;
    public static volatile SingularAttribute<Orders, Users> userId;
    public static volatile ListAttribute<Orders, OrderItem> orderItemList;
    public static volatile ListAttribute<Orders, OrderSimpleItem> orderSimpleItemList;
    public static volatile SingularAttribute<Orders, Date> dateEnd;
    public static volatile SingularAttribute<Orders, Date> dateStart;
    public static volatile SingularAttribute<Orders, Store> storeId;
    public static volatile SingularAttribute<Orders, Integer> orderId;

}