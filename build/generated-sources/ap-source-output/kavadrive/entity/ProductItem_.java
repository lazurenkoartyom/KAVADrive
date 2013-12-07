package kavadrive.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import kavadrive.entity.Product;
import kavadrive.entity.ProductSetList;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-04T23:11:05")
@StaticMetamodel(ProductItem.class)
public class ProductItem_ { 

    public static volatile SingularAttribute<ProductItem, Integer> id;
    public static volatile ListAttribute<ProductItem, ProductSetList> productSetListList;
    public static volatile SingularAttribute<ProductItem, Integer> productItemId;
    public static volatile SingularAttribute<ProductItem, Integer> productQuantity;
    public static volatile SingularAttribute<ProductItem, Product> productId;

}