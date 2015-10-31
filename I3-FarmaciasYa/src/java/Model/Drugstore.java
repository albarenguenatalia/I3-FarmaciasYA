/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utils.Coord;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cynthia
 */
@Entity
@Table(name = "drugstore")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drugstore.findAll", query = "SELECT d FROM Drugstore d"),
    @NamedQuery(name = "Drugstore.findByIdDrugStore", query = "SELECT d FROM Drugstore d WHERE d.idDrugStore = :idDrugStore"),
    @NamedQuery(name = "Drugstore.findByName", query = "SELECT d FROM Drugstore d WHERE d.name like :name"),
    @NamedQuery(name = "Drugstore.findLikeName", query = "SELECT d FROM Drugstore d WHERE d.name like CONCAT('%', :name, '%')"),
    @NamedQuery(name = "Drugstore.findByAddress", query = "SELECT d FROM Drugstore d WHERE d.address = :address"),
    @NamedQuery(name = "Drugstore.findByTelephone", query = "SELECT d FROM Drugstore d WHERE d.telephone = :telephone"),
    @NamedQuery(name = "Drugstore.findByEmail", query = "SELECT d FROM Drugstore d WHERE d.email = :email"),
    @NamedQuery(name = "Drugstore.findByLatitud", query = "SELECT d FROM Drugstore d WHERE d.latitud = :latitud"),
    @NamedQuery(name = "Drugstore.findByLongitud", query = "SELECT d FROM Drugstore d WHERE d.longitud = :longitud")})
public class Drugstore implements Serializable, Comparable<Drugstore> {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDrugStore")
    private Collection<OrderRate> orderRateCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDrugStore")
    private Integer idDrugStore;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "Address")
    private String address;
    @Size(max = 45)
    @Column(name = "Telephone")
    private String telephone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Email")
    private String email;
    @Size(max = 45)
    @Column(name = "Latitud")
    private String latitud;
    @Size(max = 45)
    @Column(name = "Longitud")
    private String longitud;
    @OneToMany(mappedBy = "idDrugStore")
    private Collection<ProductDrugstore> productDrugstoreCollection;
    
    @Transient
    private double distance;
    @Transient
    private Coord coords;


    public Drugstore() {
    }

    public Drugstore(Integer idDrugStore) {
        this.idDrugStore = idDrugStore;
        this.latitud = "";
        this.longitud = "";
    }

    public Drugstore(Integer idDrugStore, String email) {
        this.idDrugStore = idDrugStore;
        this.email = email;
    }
    
    public double getDistance(){
        return this.distance;
    }
    
    public void setDistance(double distance){
        this.distance = distance;
    }

    public Integer getIdDrugStore() {
        return idDrugStore;
    }

    public void setIdDrugStore(Integer idDrugStore) {
        this.idDrugStore = idDrugStore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @XmlTransient
    public Collection<ProductDrugstore> getProductDrugstoreCollection() {
        return productDrugstoreCollection;
    }

    public void setProductDrugstoreCollection(Collection<ProductDrugstore> productDrugstoreCollection) {
        this.productDrugstoreCollection = productDrugstoreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDrugStore != null ? idDrugStore.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drugstore)) {
            return false;
        }
        Drugstore other = (Drugstore) object;
        if ((this.idDrugStore == null && other.idDrugStore != null) || (this.idDrugStore != null && !this.idDrugStore.equals(other.idDrugStore))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Drugstore[ idDrugStore=" + idDrugStore + " ]";
    }

    /**
     * @return the coords
     */
    public Coord getCoords() {
        if(this.coords == null){
            this.coords = new Coord(Double.parseDouble(this.latitud), Double.parseDouble(this.longitud));
        }
        return coords;
    }

    /**
     * @param coords the coords to set
     */
    public void setCoords(Coord coords) {
        this.coords = coords;
    }

    @Override
    public int compareTo(Drugstore o) {
        return (int)(this.distance - o.getDistance());
    }
    
    
    public String getStarsHtml(){
        return "<i class=\"fa fa-star yellow\"></i>" + 
                "<i class=\"fa fa-star yellow\"></i>" + 
                "<i class=\"fa fa-star yellow\"></i>" + 
                "<i class=\"fa fa-star yellow\"></i>" + 
                "<i class=\"fa fa-star grey\"></i>";
                
    }

    @XmlTransient
    public Collection<OrderRate> getOrderRateCollection() {
        return orderRateCollection;
    }

    public void setOrderRateCollection(Collection<OrderRate> orderRateCollection) {
        this.orderRateCollection = orderRateCollection;
    }
}
