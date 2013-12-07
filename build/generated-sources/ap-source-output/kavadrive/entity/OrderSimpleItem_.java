package kavadrive.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import kavadrive.entity.Orders;
import kavadrive.entity.Product;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-04T23:11:05")
@StaticMetamodel(OrderSimpleItem.class)
public class OrderSimpleItem_ { 

    public static volatile SingularAttribute<OrderSimpleItem, Integer> orderSimpleItemId;
    public static volatile SingularAttribute<OrderSimpleItem, Integer> productQuantity;
    public static volatile SingularAttribute<OrderSimpleItem, Orders> orderId;
    public static volatile SingularAttribute<OrderSimpleItem, Product> productId;

}