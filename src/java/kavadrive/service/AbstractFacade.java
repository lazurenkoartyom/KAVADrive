/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

import java.util.List;
import kavadrive.classes.Message;
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
    
    protected Response createResponse(){
        return Response.OK;
    }
    
    protected Response createResponse(Message message){
        return new Response(message);
    }   
    
    protected Response createResponse(int count){
        return new Response(count);
    }
    
    protected Response createResponse(T entity){
        return  new Response(entity);
    }
    
    protected Response createResponse(List<T> entityList){
         return entityList.isEmpty()
                    ? new Response(Message.NOT_FOUND_IN_DB) 
                    : new Response(entityList);
    }
    
    protected <T,E> Response createResponse(T entity, List<E> entityList){
        return entityList.isEmpty()
                    ? new Response(entity) 
                    : new Response(entity, entityList);
     }
}
