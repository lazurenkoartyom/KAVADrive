/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.classes;

/**
 *
 * @author Artyom
 */
public class Messages {
    public static final Message OK = new Message("OK", 0);
    public static final Message NO_RESULT = new Message("No result for your request.", -1);
    public static final Message INVALID_ACTIVATION_CODE = new Message("Invalid activation code", -2);
    public static final Message COMMON_ERROR = new Message("Common error", -3);
    
    private static class Message {
        private static String message = null;
        private static int code;
        
        private Message(String mess, int code) {
            this.message = mess;
            this.code = code;
        }
    }
    
    private Messages(){
    }
    
    public String getMessage() {
        return Message.message;
    }
    
    public int getCode() {
        return Message.code;
    }
}
