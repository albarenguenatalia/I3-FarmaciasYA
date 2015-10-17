/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cynthia
 */
@Entity
@Table(name = "product_drugstore")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductDrugstore.findAll", query = "SELECT p FROM ProductDrugstore p"),
    @NamedQuery(name = "ProductDrugstore.findByIdProductDrugStore", query = "SELECT p FROM ProductDrugstore p WHERE p.idProductDrugStore = :idProductDrugStore"),
    @NamedQuery(name = "ProductDrugstore.findByPrice", query = "SELECT p FROM ProductDrugstore p WHERE p.price = :price")})
public class ProductDrugstore implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProduct_DrugStore")
    private Integer idProductDrugStore;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private Float price;
    @JoinColumn(name = "idDrugStore", referencedColumnName = "idDrugStore")
    @ManyToOne
    private Drugstore idDrugStore;
    @JoinColumn(name = "idProduct", referencedColumnName = "idProduct")
    @ManyToOne
    private Product idProduct;
    @OneToMany(mappedBy = "idProdutDrugStore")
    private Collection<OrderDetail> orderDetailCollection;

    public ProductDrugstore() {
    }

    public ProductDrugstore(Integer idProductDrugStore) {
        this.idProductDrugStore = idProductDrugStore;
    }

    public Integer getIdProductDrugStore() {
        return idProductDrugStore;
    }

    public void setIdProductDrugStore(Integer idProductDrugStore) {
        this.idProductDrugStore = idProductDrugStore;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Drugstore getIdDrugStore() {
        return idDrugStore;
    }

    public void setIdDrugStore(Drugstore idDrugStore) {
        this.idDrugStore = idDrugStore;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    @XmlTransient
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductDrugStore != null ? idProductDrugStore.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductDrugstore)) {
            return false;
        }
        ProductDrugstore other = (ProductDrugstore) object;
        if ((this.idProductDrugStore == null && other.idProductDrugStore != null) || (this.idProductDrugStore != null && !this.idProductDrugStore.equals(other.idProductDrugStore))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.ProductDrugstore[ idProductDrugStore=" + idProductDrugStore + " ]";
    }
    
}
