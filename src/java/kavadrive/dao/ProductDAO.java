/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import kavadrive.classes.Criteria;
import java.util.List;
import kavadrive.entity.Product;
import kavadrive.classes.ServiceException;

/**
 *
 * @author Aleksey Dziuniak
 */
public class ProductDAO extends AbstractDAO<Product> {
    
    private final static Class<Product> ENTITY_CLASS = Product.class;
    
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
    
    public ProductDAO() {
    }
    
    public static <Product> void create(Product product) throws ServiceException{
        add(product);
    }
    
    public static <Product> void edit(Product product) throws ServiceException{
        update(product);
    }    
    
    public static <Product> void remove(Product product) throws ServiceException{
        delete(product);
    }     
    
    public static List<Product> findAll() throws ServiceException{
        return getAll(ENTITY_CLASS);
    }
    
    public static Product find(Object id) throws ServiceException{
        return getById(ENTITY_CLASS,id);
    }
    
    public static List<Product> findRange(int[] range) throws ServiceException{
        return getRange(ENTITY_CLASS,range);
    }
    
    public static int count() throws ServiceException{
        return getCount(ENTITY_CLASS);
    }
    
    public static List<Product> findByCriterias(Criteria... criterias) throws ServiceException{
         return getByCriterias(ENTITY_CLASS, criterias);
    }
}
