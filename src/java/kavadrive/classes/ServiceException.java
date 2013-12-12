/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.classes;

/**
 *
 * @author Aleksey Dziuniak
 */
public class ServiceException extends Exception {
    private String message;

    public ServiceException(Exception ex) {
        this.message = ex.getMessage();
    }
    
    public ServiceException(String message) {
        this.message = message;
    }

    /**
     * @return the Message
     */
    @Override
    public String getMessage() {
        return message;
    }
    
}
