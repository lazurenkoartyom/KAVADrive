/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.service;

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
}
