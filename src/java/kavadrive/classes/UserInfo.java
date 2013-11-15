/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.classes;

import kavadrive.entity.Users;
import javax.management.relation.Role;
import javax.management.relation.RoleList;

/**
 *
 * @author Artyom
 */
public class UserInfo {
    private Users  client;
    String  ErrorText  = "";
    boolean  LoginFlag = false;

    private RoleList roles = new RoleList();

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void deleteRole(Role role){
        this.roles.remove(role);
    }

    public boolean checkRole(Role role){
        return this.roles.contains(role);
    }

    public UserInfo(){
    }

    public UserInfo(Users client) {
        this.client = client;
    }
    
    public UserInfo(Users client, RoleList roles){
        this.client = client;
        LoginFlag = false;
        this.roles = roles;
    }

    public String GetUser() {
        return getClient().getUserName();
    }

    public void Login(Users client) {
        LoginFlag = true;
        ErrorText = "";
        this.setClient(client);
    }

    public void SetError(String TheText) {
        ErrorText = TheText;
    }

    public String GetError() {
        return ErrorText;
    }

    public boolean IsLogin() {
        return LoginFlag;
    }

    public void Logout()
    {
        LoginFlag = false;
        setClient(null);
        ErrorText = "";
    }    

    /**
     * @return the client
     */
    public Users getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Users client) {
        this.client = client;
    }
}
