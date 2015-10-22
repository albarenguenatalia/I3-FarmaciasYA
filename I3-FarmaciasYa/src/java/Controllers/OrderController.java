package Controllers;

import Model.Drugstore;
import Model.Order1;
import Model.OrderDetail;
import Model.Product;
import Model.ProductDrugstore;
import Model.User;
import Session.OrderFacade;
import Session.UserFacade;
import Utils.Mail;
import Utils.OneWayHash;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.criteria.Predicate;

@ManagedBean(name = "orderController")
@SessionScoped
public class OrderController implements Serializable {
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
    @ManagedProperty(value = "#{drugstoreController}")
    private DrugstoreController drugstoreController;
    @Inject
    private Session.OrderFacade ejbFacade;
    private List<OrderDetail> orderDetailList;
    private ProductDrugstore selectedProductDrugstore;
   
   
    public OrderController() {
            }

    public OrderFacade getFacade() {
        return ejbFacade;
    }
    
    
    public String create() {
       return "";
    }

    public List<OrderDetail> getOrderDetailList() {       
      Order1 currentOrder = sessionController.getCurrentOrder();
      Collection<OrderDetail> coll = currentOrder.getOrderDetailCollection();
        if (coll instanceof List) {
            orderDetailList = (List)coll;
        }  
        else {
                orderDetailList = new ArrayList<>(coll);
        }
         return orderDetailList;
    }      
    
    public String addProductToCart(){ 
        System.out.println("aca esta");
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if(params.get("drugstoreId") != null && params.get("productId") != null)
        {
            try
            {
                int idDrugstore = Integer.parseInt(params.get("drugstoreId"));
                int idProduct = Integer.parseInt(params.get("productId"));
                Drugstore currentDrugstore = getDrugstoreController().getFacade().find(idDrugstore);
                if(currentDrugstore != null){
                    Collection<ProductDrugstore> coll = currentDrugstore.getProductDrugstoreCollection();
                    List<ProductDrugstore> pdFound = coll.stream()
                            .filter( pd -> pd.getIdDrugStore().equals(idDrugstore)
                                    && pd.getIdProduct().equals(idProduct) )
                            .collect(Collectors.toList());

                    /**
                     * Add field date and get the last one
                     */
                    ProductDrugstore productDrugstore = pdFound.get(0);
                    OrderDetail od = new OrderDetail();
                    od.setIdProdutDrugStore(productDrugstore);
                    od.setIdOrder(sessionController.getCurrentOrder());
                    od.setQuantity(1);        
                    sessionController.getCurrentOrder().getOrderDetailCollection().add(od);

                    for(OrderDetail orderD: sessionController.getCurrentOrder().getOrderDetailCollection()){
                        System.out.println(orderD.getIdProdutDrugStore().getIdProduct().getName());
                    }

                }
            }
            catch(NumberFormatException nfe)
            {
                nfe.printStackTrace();
            }
        }
        System.out.println("No me llegaron los id");
        return "";
    }
       

    public String update() {
        try {
            getFacade().edit(sessionController.getCurrentOrder());
           //"UserUpdated"));
            return "View";
        } catch (Exception e) {
           //"PersistenceErrorOccured"));
            return null;
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(sessionController.getCurrentOrder());
           //"UserDeleted"));
        } catch (Exception e) {
            e.printStackTrace();
           //"PersistenceErrorOccured"));
        }
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
     * @return the drugstoreController
     */
    public DrugstoreController getDrugstoreController() {
        return drugstoreController;
    }

    /**
     * @param drugstoreController the drugstoreController to set
     */
    public void setDrugstoreController(DrugstoreController drugstoreController) {
        this.drugstoreController = drugstoreController;
    }


   
    @FacesConverter(forClass = Order1.class)
    public static class  OrderControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
             OrderController controller = ( OrderController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "orderController");
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
            if (object instanceof  Order1) {
                 Order1 o = ( Order1) object;
                return String.format("Order with id: %d,  IdOrder: %s", o.getIdOrder());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + User.class.getName());
            }
        }

    }
    
   }
