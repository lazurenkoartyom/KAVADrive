/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import kavadrive.classes.Criteria;
import java.util.List;
import kavadrive.entity.Category;
import kavadrive.classes.ServiceException;
import static kavadrive.dao.AbstractDAO.getByCriterias;
import kavadrive.entity.Orders;

/**
 *
 * @author Aleksey Dziuniak
 */
public class CategoryDAO extends AbstractDAO<Category> {
    
    private final static Class<Category> ENTITY_CLASS = Category.class;

    public enum Parameters {
        ID("id");
    
        private final String name;
        private Parameters(String name){
            this.name=name;
        }

        public String getName(){
            return name;
        }
    }
    
    public CategoryDAO() {
    }
    
    public static <Category> void create(Category category) throws ServiceException{
        add(category);
    }
    
    public static <Category> void edit(Category category) throws ServiceException{
        update(category);
    }    
    
    public static <Category> void remove(Category category) throws ServiceException{
        delete(category);
    }     
    
    public static List<Category> findAll() throws ServiceException{
        return getAll(ENTITY_CLASS);
    }
    
    public static Category find(Object id) throws ServiceException{
        return getById(ENTITY_CLASS,id);
    }
    
    public static List<Category> findRange(int[] range) throws ServiceException{
        return getRange(ENTITY_CLASS,range);
    }
    
    public static int count() throws ServiceException{
        return getCount(ENTITY_CLASS);
    }
    
    public static List<Category> findByCriterias(Criteria... criterias) throws ServiceException{
         return getByCriterias(ENTITY_CLASS, criterias);
    }
}

