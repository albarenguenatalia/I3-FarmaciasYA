package Controllers;

import Model.User;
import Session.UserFacade;
import Utils.Mail;
import Utils.OneWayHash;

import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "userController")
@SessionScoped
public class UserController implements Serializable {
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
    @EJB
    private Session.UserFacade ejbFacade;
    private User current;
    private String password;
    private String confirmPassword;
    private boolean showCreateUserMessage;
    private String createUserResultMessage;


    public UserController() {
        showCreateUserMessage = false;
        createUserResultMessage = "";
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

    public String create() {
        setCreateUserResultMessage("");
        try {
            if (getFacade().findByEmail(current.getEmail()) != null) {
                setCreateUserResultMessage(ResourceBundle.getBundle("/Utils.Bundle").getString("UserAlreadyExists"));
                setShowCreateUserMessage(true);
                return "";
            }
            else if (!confirmPassword.equals(password)) {
                setCreateUserResultMessage(ResourceBundle.getBundle("/Utils.Bundle").getString("ConfirmPasswordError"));
                setShowCreateUserMessage(true);
                return "";
            }
            else {
                current.setPassword(OneWayHash.getInstance().getPasswordHash(password, current.getEmail(), password));
                current.setCreatedate(new Date());
                
                getFacade().create(current);
                getSessionController().setCurrent(current);
                getSessionController().setPassword(password);
                Mail.sendMail(current.getEmail(),
                ResourceBundle.getBundle("/Utils.Bundle").getString("WelcomeEmailSubject"), 
                ResourceBundle.getBundle("/Utils.Bundle").getString("WelcomeEmailBody").replace("John Doe", current.getName() + " " + current.getLastName()));
                String result = getSessionController().login();
                if (result != null && !result.equals("")) {
                    return "index.html";
                }
                return "";
            }        
        } catch (Exception e) {
            //"PersistenceErrorOccured"));
            return null;
        }
    }

  

    public String update() {
        try {
            getFacade().edit(current);
           //"UserUpdated"));
            return "View";
        } catch (Exception e) {
           //"PersistenceErrorOccured"));
            return null;
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
           //"UserDeleted"));
        } catch (Exception e) {
            e.printStackTrace();
           //"PersistenceErrorOccured"));
        }
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return the sessionController
     */
    public SessionController getSessionController() {
        return sessionController;
    }

    /**
     * @param sessionController the sessionController to set
     */
    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
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

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
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
                return String.format("User with id: %d, Username: %s, Address: %s", o.getIdUser(), o.getName(), o.getAddress());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + User.class.getName());
            }
        }

    }
    
    public boolean isShowCreateUserMessage() {
        return showCreateUserMessage;
    }

    public void setShowCreateUserMessage(boolean showCreateUserMessage) {
        this.showCreateUserMessage = showCreateUserMessage;
    }

    public String getCreateUserResultMessage() {
        return createUserResultMessage;
    }

    public void setCreateUserResultMessage(String createUserResultMessage) {
        this.createUserResultMessage = createUserResultMessage;
    }

}
