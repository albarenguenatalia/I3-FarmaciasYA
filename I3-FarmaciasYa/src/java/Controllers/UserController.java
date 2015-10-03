/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.User;
import Session.UserSessionBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author albarenguenatalia
 * 
 * This are the Backing Beans
 */
@ManagedBean(name = "User", eager = true)
public class UserController {
    @EJB
    UserSessionBeanLocal userSession;
    private String lala = "access User Controller";

    public String getLala() {
        return lala;
    }

    public void setLala(String lala) {
        this.lala = lala;
    }
      
    public List<User> listUsers(){
        System.out.println("Here on UserController getList");
        List<User> result = userSession.getAllUsers();
        System.out.println(result.get(0).getName());
        return result;
    }
    
    public String getFirstLastname(){
        System.out.println("Here on UserController getFirstLastname");
        List<User> result = userSession.getAllUsers();
        return result.get(0).getName();
    }
    
       
    public static boolean ValidateUserPassword(String user, String password){
       return user.equals("usuario@user.com") && password.equals("123456");
    }
 
    
    
}
