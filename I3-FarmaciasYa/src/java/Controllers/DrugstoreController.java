package Controllers;

import Model.Drugstore;
import Model.Product;
import Model.ProductDrugstore;
import Session.DrugstoreFacade;
import java.io.IOException;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "drugstoreController")
@SessionScoped
public class DrugstoreController implements Serializable {
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
    @ManagedProperty(value = "#{orderController}")
    private OrderController orderController;
    @Inject
    private Session.DrugstoreFacade ejbFacade;
    private boolean showCreateUserMessage;
    private String createUserResultMessage;
    private String drugstoreName;
    private List<Drugstore> drugstoreList;
    private List<ProductDrugstore> drugstoreProductList; 

    public DrugstoreController() {
        showCreateUserMessage = false;
        createUserResultMessage = "";
        drugstoreName = "";
    }

    public DrugstoreFacade getFacade() {
        return ejbFacade;
    }
    
    public void findByName() {
        this.drugstoreList = ejbFacade.findByName(this.drugstoreName);
    }
    
    public String testing(){
        System.out.println("Llego a testing");
        return "cart.xhtml";
    }
    
    public String selectDrugstore(Drugstore drugstore){
        sessionController.setSelectedDrugstore(drugstore);
        try {
             Collection<ProductDrugstore> coll =  sessionController.getSelectedDrugstore().getProductDrugstoreCollection();
                if (coll instanceof List) {
                     drugstoreProductList = (List)coll;
                }  
                else {
                    drugstoreProductList = new ArrayList<>(coll);
                }
                FacesContext.getCurrentInstance().getExternalContext().redirect("product_drugstore_list.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DrugstoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
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

    /**
     * @return the drugstoreList
     */
    public List<Drugstore> getDrugstoreList() {
        return drugstoreList;
    }

    /**
     * @param drugstoreList the drugstoreList to set
     */
    public void setDrugstoreList(List<Drugstore> drugstoreList) {
        this.drugstoreList = drugstoreList;
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
     * @return the drugstoreProductList
     */
    public List<ProductDrugstore> getDrugstoreProductList() {
        return drugstoreProductList;
    }
    
    public String selectProductDrugstore(ProductDrugstore pd){
        orderController.addProductToCart(pd);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("cart.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(DrugstoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public String save(String rowid) {
        System.out.println("here " + rowid);
        return "";
        //String jsParam = Util.getJsParam("repeat:" + rowid + ":x");
        //System.out.println("jsParam: " + jsParam); //persist...
    }
    

    /**
     * @param drugstoreProductList the drugstoreProductList to set
     */
    public void setDrugstoreProductList(List<ProductDrugstore> list) {
        this.setDrugstoreProductList(list);
    }

    /**
     * @return the orderController
     */
    public OrderController getOrderController() {
        return orderController;
    }

    /**
     * @param orderController the orderController to set
     */
    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
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
