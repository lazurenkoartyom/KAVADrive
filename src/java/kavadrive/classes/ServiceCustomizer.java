/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kavadrive.classes;

import org.eclipse.persistence.config.SessionCustomizer; 
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.DatabaseLogin;

/**
 *
 * @author Aleksey
 */

public class ServiceCustomizer implements SessionCustomizer {

    public ServiceCustomizer(){
        
    }
    
    @Override
    public void customize(Session session) {
        DatabaseLogin login = (DatabaseLogin)session.getDatasourceLogin();
        login.setDelayBetweenConnectionAttempts(5000);
        login.setQueryRetryAttemptCount(3);
    }
}
