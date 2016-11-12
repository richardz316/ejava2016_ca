/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author E0015387
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d")})
public class Delivery implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "pkg_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int pkgId;
    
    private String name;
    
    private String address;
    
    private String phone;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
    
    //@OneToOne(mappedBy = "pod")
    //private Pod prod;

    public int getPkgId() {
        return pkgId;
    }

    public void setPkgId(int pkgId) {
        this.pkgId = pkgId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /*public Pod getProd() {
        return prod;
    }

    public void setProd(Pod prod) {
        this.prod = prod;
    }*/
    
    
    
}
