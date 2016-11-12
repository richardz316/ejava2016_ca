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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author E0015387
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Pod.findAll", query = "SELECT p FROM Pod p"),
    @NamedQuery(name = "Pod.findByPodId", query = "SELECT p FROM Pod p WHERE p.podId = :podId")})
public class Pod implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "pod_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int podId;
    
    @OneToOne
    @JoinColumn( name = "pkg_id", referencedColumnName = "pkg_id")
    private Delivery delivery;
    
    private String note;
    
    private byte[] image;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delivery_date")
    private Date deliveryDate;
    
    @Column(name = "ack_id")
    private String ackId;

    public int getPodId() {
        return podId;
    }

    public void setPodId(int podId) {
        this.podId = podId;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getAckId() {
        return ackId;
    }

    public void setAckId(String ackId) {
        this.ackId = ackId;
    }
    
    
    
}
