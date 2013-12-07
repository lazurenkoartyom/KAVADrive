/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.classes;

/**
 *
 * @author Artyom, Aleksey Dziuniak
 */
public class Message {
    public static final Message OK = new Message("OK", 0);
    public static final Message NO_RESULT = new Message("No result for your request.", -1);
    public static final Message COMMON_ERROR = new Message("Common error.", -3);
    public static final Message NOT_FOUND_IN_DB = new Message("No object found in DB.", -4);
    
    //Security
    public static final Message INVALID_ACTIVATION_CODE = new Message("Invalid activation code.", -2);
    public static final Message USER_IS_NOT_LOGGED_IN = new Message("U r not logged in.", -5);
    public static final Message USER_IS_LOGGED_IN = new Message("U are already logged in.", -6);
    public static final Message INVALID_TOKEN = new Message("Not found user with this token. Try to relogin.", -7);
    public static final Message INVALID_PHONE_NUMBER = new Message("A phone number is wrong.", -8);
    public static final Message INVALID_EMAIL = new Message("A e-mail is wrong.", -9);
    public static final Message EMPTY_PHONE_NUMBER_AND_EMAIL = new Message("You must provide a phone number or e-mail.", -10);
    public static final Message INVALID_USER_OR_PASSWORD = new Message("Wrong user or password.", -11);
    
    private static int SERVICE_EXCEPTION_CODE = -13;
    
    //UserFacadeREST
    public static final Message INVALID_DATA = new Message("Data for user is not correct.", -14);    
    public static final Message INVALID_ROLE = new Message("U do not have enough permissions.", -15);
    public static final Message EXISTING_PHONE_NUMBER = new Message("User already exists with such phone number.", -16);
    public static final Message EXISTING_EMAIL = new Message("User already exists with such email.", -17);    
    
    private final String message;
    private final int code;

    private Message(String mess, int code) {
        this.message = mess;
        this.code = code;
    }
        
    public String getMessage() {
        return message;
    }
    
    public int getCode() {
        return code;
    }
    
    public static Message catchException(ServiceException ex){
        return new Message(ex.getMessage(),SERVICE_EXCEPTION_CODE);
    }
}