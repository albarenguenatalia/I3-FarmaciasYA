/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.User;
import Session.UserFacade;
import Utils.OneWayHash;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;

/**
 *
 * @author albarenguenatalia
 */
@ManagedBean(name = "sessionController")
@SessionScoped
public class SessionController implements Serializable {

    private User current;
    private String password;
    private boolean showLoginResultMessage;
    private boolean userLogged;
    private String loginResultMessage;
    @EJB
    private Session.UserFacade ejbFacade;
    private int currentUserId;

    public SessionController() {
        showLoginResultMessage = false;
        userLogged = false;
        loginResultMessage = "";
        currentUserId = -1;
    }

    public UserFacade getFacade() {
        return ejbFacade;
    }

    public User getCurrent() {
        /*if (current == null) {
            current = new User();
            setCurrentUserId(-1);
        }
        System.err.println(current.getIdUser());
        setCurrentUserId(current.getIdUser());
        return current;*/
        return null;
    }

    public String login() {
        try { 
            setUserLogged(false);
            this.setLoginResultMessage("");
            this.setShowLoginResultMessage(false);
            OneWayHash hash = OneWayHash.getInstance();
            byte[] passenc = hash.hashSHA256(password, (current.getEmail()+ password).getBytes());
            User checkedUser = getFacade().validateUser(current.getEmail(), passenc);
            if (checkedUser != null) {
                setCurrent(checkedUser);
                setUserLogged(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                return "";
            } else { 
                setCurrent(new User());
                this.setLoginResultMessage(ResourceBundle.getBundle("/Utils.Bundle").getString("ErrorLoginMessage"));
                this.setShowLoginResultMessage(true);
                System.out.println("Error de login.Actualiza el model");
                return "";
            }
        } catch (Exception e) { 
            e.printStackTrace();
            setCurrent(new User());
            this.setLoginResultMessage("Error hasheando pass");
            //this.setLoginResultMessage(ResourceBundle.getBundle("/Utils.Bundle").getString("ErrorLoginMessage"));
            this.setShowLoginResultMessage(true);
            System.out.println("Error de login.Actualiza el model");
            return "";
        }
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

    /**
     * @return the userLogged
     */
    public boolean isUserLogged() {
        return userLogged;
    }

    /**
     * @param userLogged the userLogged to set
     */
    public void setUserLogged(boolean userLogged) {
        this.userLogged = userLogged;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(User current) {
        this.current = current;
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
     * @return the currentUserId
     */
    public int getCurrentUserId() {
        return currentUserId;
    }

    /**
     * @param currentUserId the currentUserId to set
     */
    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
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
                return String.format("User with id: %d, Username: %s, Address: %s", o.getIdUser(), o.getEmail(), o.getAddress());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + User.class.getName());
            }
        }

    }

}
