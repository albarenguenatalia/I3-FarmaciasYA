/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Model.User;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author albarenguenatalia
 */
@ManagedBean(name="LoginSessionBean", eager=true)
@SessionScoped
public class LoginSessionBean implements Serializable{
    User user;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     public void Login(User loggedUser ){
        user = loggedUser;
   }
    
    public User GetUser(){
        return user;
    }
    
  

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
