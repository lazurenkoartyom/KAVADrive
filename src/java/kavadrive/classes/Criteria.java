/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.classes;

/**
 *
 * @author Aleksey Dziuniak
 */
public class Criteria<T> {
    
    private String name; 
    
    private T value;
    
    private Criteria (){
    }
    
    public Criteria(String name, T value){
        this.name = name;
        this.value = value;
    }
    
    public String getName(){
        return this.name;
    }
    
    public T getValue(){
        return this.value;
    }
}
