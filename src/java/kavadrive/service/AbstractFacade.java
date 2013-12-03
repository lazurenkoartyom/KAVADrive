/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

import java.util.List;
import kavadrive.classes.Response;

/**
 *
 * @author  Aleksey Dziuniak
 */
public abstract class AbstractFacade<T> {
    
    protected abstract Response create(T entity);
    protected abstract Response edit(T entity);
    protected abstract Response remove(Integer id);
    protected abstract Response find(Integer id);
    protected abstract Response findAll();
    protected abstract Response findRange(Integer from, Integer to);
    protected abstract Response count();
    
    protected Response createMessage(){
        return Response.OK;
    }
    
    protected Response createMessage(String errorMessage){
        return new Response(errorMessage, -1);
    }
    
    protected Response createMessage(int count){
        return new Response(count, "OK", 0);
    }
    
    protected Response createMessage(T entity){
        return  new Response(entity, "OK", 0);
    }
    
    protected Response createMessage(List<T> entityList){
         return entityList.isEmpty()
                    ? new Response("No object found in DB", -1) 
                    : new Response(entityList, "OK", 0);
    }
    
    protected <T,E> Response createMessage(T entity, List<E> entityList){
        return entityList.isEmpty()
                    ? new Response(entity, null, "OK", 0) 
                    : new Response(entity, entityList, "OK", 0);
     }
}
