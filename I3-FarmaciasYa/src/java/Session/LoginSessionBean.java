/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

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
    String user;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     public void Login(){
        user = "Usuario";
        System.out.println(user);
   }
    
    public String GetUser(){
        return user;
    }
    
  

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
