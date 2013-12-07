package kavadrive.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import kavadrive.entity.Product;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-04T23:11:05")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile SingularAttribute<Category, String> categoryName;
    public static volatile ListAttribute<Category, Product> productList;
    public static volatile SingularAttribute<Category, Integer> categoryId;
    public static volatile SingularAttribute<Category, Integer> categoryParentId;

}