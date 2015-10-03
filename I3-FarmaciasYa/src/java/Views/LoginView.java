/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.UserController;
import Session.LoginSessionBean;
import Session.UserSessionBean;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Cynthia
 */
@ManagedBean(name = "LoginViewBean", eager = true)
@ViewScoped
public class LoginView implements Serializable{
    @ManagedProperty("#{LoginSessionBean}")
    private LoginSessionBean loginSessionBean;
    private String username;
    private String password;
    
    public String IniciarSesion(){
        if(UserController.ValidateUserPassword(username, password)){
            loginSessionBean.Login();
            return "/index.xhtml";
        }
        return "";
            
        
    }
    
    public void setLoginSessionBean(LoginSessionBean loginSessionBean) {
        this.loginSessionBean = loginSessionBean;
    }
  
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
