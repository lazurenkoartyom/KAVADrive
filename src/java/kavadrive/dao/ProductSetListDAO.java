/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import kavadrive.classes.Criteria;
import java.util.List;
import kavadrive.entity.ProductSetList;
import kavadrive.classes.ServiceException;
import static kavadrive.dao.AbstractDAO.getByCriterias;
import kavadrive.entity.Category;

/**
 *
 * @author Aleksey Dziuniak
 */
public class ProductSetListDAO extends AbstractDAO<ProductSetList> {
    
    private final static Class<ProductSetList> ENTITY_CLASS = ProductSetList.class;
    
    public enum Parameters {
        ID("setListId");
    
        private final String name;
        private Parameters(String name){
            this.name=name;
        }

        public String getName(){
            return name;
        }
    }
    
    public ProductSetListDAO() {
    }
    
    public static <ProductSetList> void create(ProductSetList setList) throws ServiceException{
        add(setList);
    }
    
    public static <ProductSetList> void edit(ProductSetList setList) throws ServiceException{
        update(setList);
    }    
    
    public static <ProductSetList> void remove(ProductSetList setList) throws ServiceException{
        delete(setList);
    }     
    
    public static List<ProductSetList> findAll() throws ServiceException{
        return getAll(ENTITY_CLASS);
    }
    
    public static ProductSetList find(Object id) throws ServiceException{
        return getById(ENTITY_CLASS,id);
    }
    
    public static List<ProductSetList> findRange(int[] range) throws ServiceException{
        return getRange(ENTITY_CLASS,range);
    }
    
    public static int count() throws ServiceException{
        return getCount(ENTITY_CLASS);
    }

    public static List<ProductSetList> findByCriterias(Criteria... criterias) throws ServiceException{
         return getByCriterias(ENTITY_CLASS, criterias);
    }   
}
