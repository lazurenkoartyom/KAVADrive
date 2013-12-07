package kavadrive.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import kavadrive.entity.Category;
import kavadrive.entity.OrderItem;
import kavadrive.entity.OrderSimpleItem;
import kavadrive.entity.ProductItem;
import kavadrive.entity.ProductSetList;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-04T23:11:05")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile ListAttribute<Product, ProductItem> productItemList;
    public static volatile SingularAttribute<Product, BigDecimal> price;
    public static volatile SingularAttribute<Product, String> productDescription;
    public static volatile SingularAttribute<Product, Category> categoryId;
    public static volatile ListAttribute<Product, OrderItem> orderItemList;
    public static volatile ListAttribute<Product, OrderSimpleItem> orderSimpleItemList;
    public static volatile SingularAttribute<Product, String> productName;
    public static volatile SingularAttribute<Product, String> productAttribute;
    public static volatile SingularAttribute<Product, Integer> productId;
    public static volatile SingularAttribute<Product, ProductSetList> setListName;

}