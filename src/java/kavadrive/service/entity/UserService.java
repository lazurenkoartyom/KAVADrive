/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.service.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import kavadrive.entity.Users;

/**
 *
 * @author Aleksey Dziuniak
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"userId", "name", "phone","email"})
class UserService {
    
    private Integer userId;

    private String name;

    private String phone;

    private String email;
    
    public UserService(){
        
    }
    
    public UserService(Users user){
        this.userId = user.getUserId();
        this.name = user.getUserName();
        this.phone = user.getUserPhone();
        this.email = user.getEmail();
    }
}
