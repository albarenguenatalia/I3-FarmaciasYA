/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.UserController;
import Model.User;
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
 
    private UserController userController;
    
    private String username;
    private String password;
    private String errorMessage;
    private User sessionUser;
    private boolean showMessage = false;
    
    public String IniciarSesion(){
        System.out.println("LoginView IniciarSesion");
        /*User returnedUser = UserController.getFacade().validateUser(username, password);
        if(returnedUser != null){
            sessionUser = returnedUser;
            getLoginSessionBean().Login(returnedUser);
            return "/index.xhtml";
        }
        this.errorMessage = "Nombre de Usuario o Contraseña inválido";
        this.showMessage = true;*/
        return "";
    }
    
  
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

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the showMessage
     */
    public boolean isShowMessage() {
        return showMessage;
    }

    /**
     * @param showMessage the showMessage to set
     */
    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }



    /**
     * @return the userController
     */
    public UserController getUserController() {
        return userController;
    }

    /**
     * @param userController the userController to set
     */
    public void setUserController(UserController userController) {
        this.userController = userController;
    }
}
