package kavadrive.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import kavadrive.entity.Product;
import kavadrive.entity.ProductItem;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-04T23:11:05")
@StaticMetamodel(ProductSetList.class)
public class ProductSetList_ { 

    public static volatile SingularAttribute<ProductSetList, String> setListId;
    public static volatile SingularAttribute<ProductSetList, ProductItem> productItemId;
    public static volatile SingularAttribute<ProductSetList, String> productDescription;
    public static volatile ListAttribute<ProductSetList, Product> productList;
    public static volatile SingularAttribute<ProductSetList, String> setListName;

}