/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Drugstore;
import Model.Order1;
import Model.OrderDetail;
import Model.ProductDrugstore;
import Model.User;
import Session.UserFacade;
import Utils.OneWayHash;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SessionController implements Serializable {

    private User current;
    private Order1 currentOrder;
    private String password;
    private boolean showLoginResultMessage;
    private boolean userLogged;
    private String loginResultMessage;
    @EJB
    private Session.UserFacade ejbFacade;
    private int currentUserId;
    private Drugstore selectedDrugstore;

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
        if (current == null) {
            current = new User();
        }
        return current;
    }

    public String login() {
        try {
            System.out.println("LOGUEANDO _________________________________________");
            setUserLogged(false);
            this.setLoginResultMessage("");
            this.setShowLoginResultMessage(false);
            OneWayHash hash = OneWayHash.getInstance();
            System.out.println(current.getEmail() + password);
            byte[] passenc = hash.hashSHA256(password, (current.getEmail() + password).getBytes());
            User checkedUser = getFacade().validateUser(current.getEmail(), passenc);
            if (checkedUser != null) {
                setCurrent(checkedUser);
                setUserLogged(true);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/I3-FarmaciasYa/template/index.xhtml");
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

    public String logout() {
        System.out.println("Logout...");
        setCurrent(null);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/I3-FarmaciasYa/template/index.xhtml");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "/I3-FarmaciasYa/template/index.xhtml";
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

    public void resetOrder() {
        if (this.current.getIdUser() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            currentOrder = new Order1();
            currentOrder.setIdUser(current);
            currentOrder.setTotal((float) 0);
            currentOrder.setOrderDetailCollection(new ArrayList<OrderDetail>());
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the currentOrder
     */
    public Order1 getCurrentOrder() {
        if (currentOrder == null) {
            if (this.current.getIdUser() == null) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                currentOrder = new Order1();
                currentOrder.setIdUser(current);
                currentOrder.setTotal((float) 0);
                currentOrder.setOrderDetailCollection(new ArrayList<OrderDetail>());
            }
        }
        return currentOrder;
    }

    /**
     * @param currentOrder the currentOrder to set
     */
    public void setCurrentOrder(Order1 currentOrder) {
        this.currentOrder = currentOrder;
    }

    /**
     * @return the selectedDrugstore
     */
    public Drugstore getSelectedDrugstore() {
        return selectedDrugstore;
    }

    /**
     * @param selectedDrugstore the selectedDrugstore to set
     */
    public void setSelectedDrugstore(Drugstore selectedDrugstore) {
        /*Simplification: all products come from the same drugstore*/
        if (this.current.getIdUser() == null) {
            selectedDrugstore = null;
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            if (this.currentOrder != null
                    && this.currentOrder.getOrderDetailCollection().size() > 0) {

                if (!this.currentOrder.getOrderDrugstore().equals(selectedDrugstore)) {
                    this.currentOrder.setOrderDetailCollection(new ArrayList<OrderDetail>());
                }
            }
            this.selectedDrugstore = selectedDrugstore;
        }

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
