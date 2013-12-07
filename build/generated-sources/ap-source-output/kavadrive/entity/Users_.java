package kavadrive.entity;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import kavadrive.entity.Orders;
import kavadrive.entity.Role;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-04T23:11:05")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> userSIF;
    public static volatile ListAttribute<Users, Orders> ordersList;
    public static volatile SingularAttribute<Users, String> userPassword;
    public static volatile SingularAttribute<Users, BigInteger> tokenCreate;
    public static volatile SingularAttribute<Users, Integer> secretCod;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, String> address;
    public static volatile SingularAttribute<Users, String> token;
    public static volatile SingularAttribute<Users, Integer> userId;
    public static volatile SingularAttribute<Users, String> userName;
    public static volatile SingularAttribute<Users, String> userPhone;
    public static volatile SingularAttribute<Users, String> userDescription;
    public static volatile SingularAttribute<Users, String> skype;
    public static volatile SingularAttribute<Users, Role> roleId;

}