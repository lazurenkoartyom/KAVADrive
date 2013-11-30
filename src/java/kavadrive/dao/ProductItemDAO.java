/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kavadrive.dao;

import java.util.List;
import kavadrive.entity.ProductItem;
import kavadrive.classes.ServiceException;

/**
 *
 * @author Aleksey Dziuniak
 */
public class ProductItemDAO extends AbstractDAO<ProductItem> {
    
    private final static Class<ProductItem> ENTITY_CLASS = ProductItem.class;
    
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
    
    public ProductItemDAO() {
    }
    
    public static <ProductItem> void create(ProductItem productItem) throws ServiceException{
        add(productItem);
    }
    
    public static <ProductItem> void edit(ProductItem productItem) throws ServiceException{
        update(productItem);
    }    
    
    public static <ProductItem> void remove(ProductItem productItem) throws ServiceException{
        delete(productItem);
    }     
    
    public static List<ProductItem> findAll() throws ServiceException{
        return getAll(ENTITY_CLASS);
    }
    
    public static ProductItem find(Object id) throws ServiceException{
        return getById(ENTITY_CLASS,id);
    }
    
    public static List<ProductItem> findRange(int[] range) throws ServiceException{
        return getRange(ENTITY_CLASS,range);
    }
    
    public static int count() throws ServiceException{
        return getCount(ENTITY_CLASS);
    }
    
    public static <E> List<ProductItem> findByParameter(Parameters name, E parameterValue) throws ServiceException{
        String nameParameter = name.getName();
        return getByParameter(ENTITY_CLASS, nameParameter, parameterValue);
    }
}
