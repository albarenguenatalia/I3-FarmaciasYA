/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cynthia
 */
@Entity
@Table(name = "order_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetail o"),
    @NamedQuery(name = "OrderDetail.findByIdOrderDetail", query = "SELECT o FROM OrderDetail o WHERE o.idOrderDetail = :idOrderDetail"),
    @NamedQuery(name = "OrderDetail.findByPrice", query = "SELECT o FROM OrderDetail o WHERE o.price = :price")})
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrder_Detail")
    private Integer idOrderDetail;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private Float price;
    @JoinColumn(name = "idOrder", referencedColumnName = "idOrder")
    @ManyToOne
    private Order1 idOrder;
    @JoinColumn(name = "idProdut_DrugStore", referencedColumnName = "idProduct_DrugStore")
    @ManyToOne
    private ProductDrugstore idProdutDrugStore;
    @Column(name = "Quantity")
    private Integer quantity;
    

    public OrderDetail() {
    }

    public OrderDetail(Integer idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public Integer getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(Integer idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Order1 getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Order1 idOrder) {
        this.idOrder = idOrder;
    }

    public ProductDrugstore getIdProdutDrugStore() {
        return idProdutDrugStore;
    }

    public void setIdProdutDrugStore(ProductDrugstore idProdutDrugStore) {
        this.idProdutDrugStore = idProdutDrugStore;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrderDetail != null ? idOrderDetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetail)) {
            return false;
        }
        OrderDetail other = (OrderDetail) object;
        if ((this.idOrderDetail == null && other.idOrderDetail != null) || (this.idOrderDetail != null && !this.idOrderDetail.equals(other.idOrderDetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.OrderDetail[ idOrderDetail=" + idOrderDetail + " ]";
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.setPrice(this.getIdProdutDrugStore().getPrice() * this.quantity);
    }
    
}
