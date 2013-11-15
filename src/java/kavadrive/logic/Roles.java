/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.logic;

/**
 *
 * @author Администратор
 */
public enum Roles {
    ADMINISTRATOR (1), 
    MANAGER (2), 
    USER (3);
    
    private final int id;
    private Roles(int id){
        this.id=id;
    }
    
    public int getId(){
        return id;
    }
}

