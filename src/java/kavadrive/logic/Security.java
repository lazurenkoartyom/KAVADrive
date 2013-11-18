/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.logic;


import java.math.BigInteger;
import java.util.Date;
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
import kavadrive.entity.Users;
import kavadrive.logic.Roles;
import kavadrive.logic.ServiceException;
import kavadrive.logic.TokenFactory;
import kavadrive.logic.UserDAO;

/**
 *
 * @author Artyom
 */
@Path("security")
public class Security {
    
    private static final long TOKEN_EXPIRE_PERIOD = 2592000L; //milliseconds for 30 days

    @Context
    private HttpServletRequest request;
    
    @Context 
    private HttpServletResponse response;

//    private Users foundUser = null;

//    @GET
//    @Path("login")
//    @Produces(MediaType.APPLICATION_XML)
//    public Response login() {
//        return new Response("Method not allowed.", -1);
//    }

    @GET
    @Path("logout")
    @Produces(MediaType.APPLICATION_XML)
    public Response logout(){
        try {
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", null);
            return Response.OK;
        } catch (Exception ex) {
            //There is no UserInfo in current session
            return new Response (null, "U r not logged in.", -1);
        }
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
        UserInfo userFromSession = (UserInfo)session.getAttribute("userInfo");
        if(userFromSession != null) {
            return true;
        }else{
            return false;
        }
    }
    
    /************* Login by token ****************/
    private Response loginByToken(String token) throws ServiceException {
        Users loggingInUser;
        loggingInUser = UserDAO.findByToken(token);

        if(loggingInUser == null) {
            response.addCookie(new Cookie("Token", null));
            return new Response(null, "Not found user with this token. Try to relogin.", -1);
        }
        checkTokenExpireAndUpdateToken(loggingInUser);
        setSessionAttribute(loggingInUser);
        return Response.OK;
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
        }while(isExistingInDataBase(user, token));
        user.setToken(token);
        user.setTokenCreate(BigInteger.valueOf(new Date().getTime()+TOKEN_EXPIRE_PERIOD));
        UserDAO.update(user);//update data in DataBase
    }
    
    private boolean isExistingInDataBase(Users user, String token) throws ServiceException {
        user = UserDAO.findByToken(token);

        if (user == null){ return false; }
        return true;
    }
    
    private void setSessionAttribute(Users user) throws ServiceException{
        Cookie cookie = new Cookie("Token", user.getToken());
        int expiry = getCookieMaxAge(user);
        if(expiry>0){
            cookie.setMaxAge(expiry);
        }else{
            resetSecretCodAndChangeToken(user);
        }
//        cookie.setSecure(true);
        cookie.setPath("/KAVADrive/webresources");
        response.addCookie(cookie);
        user.setUserPassword(null);
        UserInfo userInfo = new UserInfo(user);
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", userInfo);
    }
    
    private int getCookieMaxAge(Users user) {
        long now = new Date().getTime();
        long tokenCreate = user.getTokenCreate().longValue();
        int expiry = (int)(tokenCreate-now)/1000;
        return expiry;
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
            foundUser = UserDAO.findByPhone(phone);
            if(foundUser==null) {
                return new Response(null, "A phone number is wrong.", -1);
            }
        } else if (email != null) {
            foundUser = UserDAO.findByEMail(email);
            if(foundUser==null) {
                return new Response(null, "A e-mail is wrong.", -1);
            }
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
            return Response.OK;
        }else{
            return new Response(null, "Wrong registration code.", -1);
        }
    }
    
    public static boolean checkClientRole (HttpServletRequest request,Roles... roles) throws ServiceException {
        
        String token = getTokenFromSession(request);

        if(token != null){
            return isValidRole(token, roles);
        }else{
            return false;
        }
    }
    
    private static boolean isValidRole(String token,Roles... roles) throws ServiceException{
        Users client = UserDAO.findByToken(token);
        
        if(client == null) {
            return false;
        }
        for (Roles role : roles){
            if(client.getRoleId()==role.getId()){
                return true;
            }
        }
        return false;
    }

    public static String getTokenFromSession(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if ("Token".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }    
}