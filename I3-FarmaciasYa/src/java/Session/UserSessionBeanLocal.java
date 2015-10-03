/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Model.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author albarenguenatalia
 */
@Local
public interface UserSessionBeanLocal {

    List<User> getAllUsers();
    
}
