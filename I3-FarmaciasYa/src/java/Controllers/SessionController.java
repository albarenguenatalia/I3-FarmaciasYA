/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.User;
import Session.UserFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author albarenguenatalia
 */
@ManagedBean(name = "sessionController")
@SessionScoped
public class SessionController implements Serializable{
    private User current;
    private boolean showLoginResultMessage;
    private String loginResultMessage;
    @EJB
    private Session.UserFacade ejbFacade;
    private int currentUserId;
    
    public SessionController(){
        showLoginResultMessage = false;
        loginResultMessage = "";
        currentUserId = -1;
    }
    
    public UserFacade getFacade() {
        return ejbFacade;
    }
    
    public User getCurrent() {
        if (current == null) {
            current = new User();
            currentUserId = -1;
        }
        currentUserId = current.getUserId();
        return current;
    }
    
    public String login(){
        User checkedUser = getFacade().validateUser(current.getUsername(), current.getPassword());
        if(checkedUser != null){
            current = checkedUser;
            return "/index.xhtml";
        }
        current = new User();
        this.setLoginResultMessage("Nombre de Usuario o Contraseña inválido");
        this.setShowLoginResultMessage(true);
        return null;
    }
    
    /**
     * @return the loginResultMessage
     */
    public String getLoginResultMessage() {
        return loginResultMessage;
    }

    /**
     * @param loginResultMessage the loginResultMessage to set
     */
    public void setLoginResultMessage(String loginResultMessage) {
        this.loginResultMessage = loginResultMessage;
    }
    
      /**
     * @return the showLoginResultMessage
     */
    public boolean isShowLoginResultMessage() {
        return showLoginResultMessage;
    }

    /**
     * @param showLoginResultMessage the showLoginResultMessage to set
     */
    public void setShowLoginResultMessage(boolean showLoginResultMessage) {
        this.showLoginResultMessage = showLoginResultMessage;
    }
    
    
    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SessionController controller = (SessionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sessionController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof User) {
                User o = (User) object;
                return String.format("User with id: %d, Username: %s, Address: %s", o.getUserId(), o.getUsername(), o.getAddress());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + User.class.getName());
            }
        }

    }

}
