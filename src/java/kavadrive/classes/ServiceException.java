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
    private String Message;

    public ServiceException(String Message) {
        this.Message = Message;
    }

    /**
     * @return the Message
     */
    @Override
    public String getMessage() {
        return Message;
    }
    
}