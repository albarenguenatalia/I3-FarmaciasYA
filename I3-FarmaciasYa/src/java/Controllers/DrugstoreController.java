package Controllers;

import Model.Drugstore;
import Session.DrugstoreFacade;
import Utils.Mail;
import Utils.OneWayHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "drugstoreController")
@SessionScoped
public class DrugstoreController implements Serializable {
    @EJB
    private Session.DrugstoreFacade ejbFacade;
    private boolean showCreateUserMessage;
    private String createUserResultMessage;
    private String drugstoreName;
   

    public DrugstoreController() {
        showCreateUserMessage = false;
        createUserResultMessage = "";
        drugstoreName = "valor inicial";
    }

    public DrugstoreFacade getFacade() {
        return ejbFacade;
    }
    
    public String otherMethod(){
        System.out.println("IN OTHER METHOD");
        return null;
    }
    
    public String findByName() {
        System.err.println("In DRUGSTORE CONTROLLER");
        List<Drugstore> foundDrugstore = ejbFacade.findByName(this.drugstoreName);
        if(foundDrugstore == null){
            System.out.println("No se encontraron farmacias jaja");
        }else{
            for(Drugstore d: foundDrugstore){
                System.out.println(d.getName());
            }
            
        }
        
        return "template/register.xhtml";
    }

    /**
     * @return the drugstoreName
     */
    public String getDrugstoreName() {
        return drugstoreName;
    }

    /**
     * @param drugstoreName the drugstoreName to set
     */
    public void setDrugstoreName(String drugstoreName) {
        this.drugstoreName = drugstoreName;
    }

  

    @FacesConverter(forClass = Drugstore.class)
    public static class DrugstoreControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DrugstoreController controller = (DrugstoreController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "drugstoreController");
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
            if (object instanceof Drugstore) {
                Drugstore o = (Drugstore) object;
                return String.format("Drugstore with id: %d, : %s, Name: %s, Address: %s", o.getIdDrugStore(), o.getName(), o.getAddress());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Drugstore.class.getName());
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
