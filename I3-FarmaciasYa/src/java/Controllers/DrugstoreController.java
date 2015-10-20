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
        System.out.println("BUSCANDO " + this.drugstoreName);
        System.out.println(ejbFacade);
        this.drugstoreList = ejbFacade.findByName(this.drugstoreName);
    }
    
    public String selectDrugstore(int id){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("product_drugstore_list.xhtml?id=" + id);
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
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.get("id") != null)
        {
            try
            {
                int idDrugstore = Integer.parseInt(params.get("id"));
                Drugstore currentDrugstore = ejbFacade.find(idDrugstore);
                Collection<ProductDrugstore> coll = currentDrugstore.getProductDrugstoreCollection();
                if (coll instanceof List) {
                     drugstoreProductList = (List)coll;
                }  
                else {
                    drugstoreProductList = new ArrayList<>(coll);
                }
                return drugstoreProductList;
              
            }
            catch(NumberFormatException nfe)
            {
                drugstoreProductList = null;
                nfe.printStackTrace();
                return drugstoreProductList;
            }
        }
        drugstoreProductList = null;
        return drugstoreProductList;
       
    }
    
    public void addProductToCart(){
        ProductDrugstore pd = new ProductDrugstore();
        System.out.println("DRUGSTORE CONTROLLER addProductToCart ");
        System.out.println(pd);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/cart.xhtml?drugstoreId=" 
                    + pd.getIdDrugStore().getIdDrugStore() + "&productId=" + pd.getIdProduct().getIdProduct());
        } catch (IOException ex) {
            Logger.getLogger(DrugstoreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param drugstoreProductList the drugstoreProductList to set
     */
    public void setDrugstoreProductList(List<ProductDrugstore> list) {
        this.setDrugstoreProductList(list);
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
