/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.logic;


import kavadrive.classes.ServiceException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.servlet.http.Cookie;
import javax.ws.rs.core.MediaType;
import kavadrive.classes.Response;
import kavadrive.classes.UserInfo;
import kavadrive.dao.UsersDAO;
import static kavadrive.dao.UsersDAO.Parameters.*;
import kavadrive.entity.Role;
import kavadrive.entity.Users;

/**
 *
 * @author Artyom,Aleksey Dziuniak
 */
@Path("security")
public class Security {
    
    private static final long TOKEN_EXPIRE_PERIOD = 2592000000L; //milliseconds for 30 days
    private final static String COOKIE_NAME = "Token";
    private final static String COOKIE_PATH = "/KAVADrive/webresources/";
    private final static String ATTRIBUTE_NAME = "userInfo";
   
    
    @Context
    private HttpServletRequest request;
    
    @Context 
    private HttpServletResponse response;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response log(){
        return logout();
    }

    @GET
    @Path("logout")
    @Produces(MediaType.APPLICATION_XML)
    public Response logout(){
        try {
            HttpSession session = request.getSession();
            clearToken();
            session.invalidate();
            return Response.OK;
        } catch (Exception ex) {
            //There is no UserInfo in current session
            return new Response (null, "U r not logged in.", -1);
        }
    }
    
    private void clearToken() {
        Cookie cookie = new Cookie(COOKIE_NAME, "");
        cookie.setPath(COOKIE_PATH);
        response.addCookie(cookie);
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response login(Users loggingInClient) {
        try {
            if(isClientLoggedIn()) {
                return new Response(null, "U are already logged in.", -1);
            }
        
            String token = getTokenFromSession(request);

            if(token != null && !("".equals(token))){
                return loginByToken(token);
            } else {
                return loginByPhoneOrEmail(loggingInClient);
            }
        }catch (ServiceException ex) {
                return new Response(null, ex.getMessage(), -1);
        }
    }
    
    private boolean isClientLoggedIn(){
        HttpSession session = request.getSession();
        UserInfo userFromSession = (UserInfo)session.getAttribute(ATTRIBUTE_NAME);
        return userFromSession != null;
    }
    
    /************* Login by token ****************/
    private Response loginByToken(String token) throws ServiceException {
        List<Users> users = UsersDAO.findByParameter(TOKEN, token);
        
        if(users.isEmpty()) {
            clearToken();
            return new Response(null, "Not found user with this token. Try to relogin.", -1);
        }
        Users loggingInUser = users.get(0);
        checkTokenExpireAndUpdateToken(loggingInUser);
        setSessionAttribute(loggingInUser);
        return  new Response(loggingInUser);
    }

    private void checkTokenExpireAndUpdateToken(Users user) throws ServiceException{
        long now = new Date().getTime();
        if(user.getTokenCreate().compareTo(BigInteger.valueOf(now))==-1){
                resetSecretCodAndChangeToken(user);
        }
    }
    
    private void resetSecretCodAndChangeToken(Users user) throws ServiceException{
        if(user.getSecretCod()!= null){
            user.setSecretCod(null);
        }        
        String token;
        do{
            token = TokenFactory.getNewValue();
        }while(isExistingInDataBase(token));
        user.setToken(token);
        user.setTokenCreate(BigInteger.valueOf(new Date().getTime()+TOKEN_EXPIRE_PERIOD));
        UsersDAO.edit(user);//update data in DataBase
    }
    
    private boolean isExistingInDataBase(String token) throws ServiceException {
        List<Users> users = UsersDAO.findByParameter(TOKEN, token);
        return !users.isEmpty();
    }
    
    private void setSessionAttribute(Users user) throws ServiceException{
        Cookie cookie = new Cookie(COOKIE_NAME, user.getToken());
        int tokenSecToExpire = getCookieMaxAge(user);
        cookie.setMaxAge(tokenSecToExpire);
        cookie.setPath(COOKIE_PATH);
        response.addCookie(cookie);
        user.setUserPassword(null);
        UserInfo userInfo = new UserInfo(user);
        HttpSession session = request.getSession();
        session.setAttribute(ATTRIBUTE_NAME, userInfo);
    }
    
    private int getCookieMaxAge(Users user) {
        long currentDateTime = new Date().getTime();
        long tokenCreateDateTime = user.getTokenCreate().longValue();
        int tokenSecToExpire = (int)((tokenCreateDateTime - currentDateTime)/1000);
        return tokenSecToExpire;
    }
    
    /*************** Login by phone or email ***********/
    private Response loginByPhoneOrEmail(Users loggingInClient) throws ServiceException{            
        
        String phone = loggingInClient.getUserPhone();
        String email = loggingInClient.getEmail();
        String password = loggingInClient.getUserPassword();
        Integer secretCode = loggingInClient.getSecretCod();
        
        //Checking phone and email and searching user in DB
        Users foundUser;
        if (phone != null) {
            List<Users> users = UsersDAO.findByParameter(PHONE, phone);
            if(users.isEmpty()) {
                return new Response(null, "A phone number is wrong.", -1);
            }
            foundUser = users.get(0);
        } else if (email != null) {
            List<Users> users = UsersDAO.findByParameter(EMAIL, email);
            if(users.isEmpty()) {
                return new Response(null, "A e-mail is wrong.", -1);
            }
            foundUser = users.get(0);
       } else {
            //User did not provide phone number or e-mail
            return new Response(null, "You must provide a phone number or e-mail.", -1);
        }
        
        //Checking password
        if (foundUser.getUserPassword()==null 
                || (!foundUser.getUserPassword().equals(password))) {
            return new Response(null, "Wrong user or password.", -1);
        }
        
        //Checking secretCode
        if (foundUser.getSecretCod()==null 
                || foundUser.getSecretCod().equals(secretCode)) {
            resetSecretCodAndChangeToken(foundUser);
            setSessionAttribute(foundUser);
            return new Response(foundUser);
        }else{
            return new Response(null, "Wrong registration code.", -2);
        }
    }
    
    public static boolean checkClientRole (HttpServletRequest request, Role... roles) throws ServiceException {
        
        String token = getTokenFromSession(request);

        if(token != null){
            return isValidRole(token, roles);
        }else{
            return false;
        }
    }
    
    private static boolean isValidRole(String token,Role... roles) throws ServiceException{
        List<Users> users = UsersDAO.findByParameter(TOKEN, token);
        if(users.isEmpty()) {
            return false;
        }
        Users client = users.get(0);
        for (Role role : roles){
            if(client.getRoleId().equals(role)){
                return true;
            }
        }
        return false;
    }

    public static String getTokenFromSession(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for(Cookie cookie : cookies) {
            if (COOKIE_NAME.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}