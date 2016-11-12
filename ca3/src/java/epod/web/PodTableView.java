/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.web;

import epod.business.PodBean;
import epod.model.Pod;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author rzhao
 */
@ViewScoped
@Named
public class PodTableView  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EJB
    private PodBean podBean;
    
    private List<Pod> podItems;

    public List<Pod> getPodItems() {
        return podItems;
    }

    public void setPodItems(List<Pod> podItems) {
        this.podItems = podItems;
    }
    
    @PostConstruct
    private void init() {

        Optional<List<Pod>> pods = podBean.findAll();

        if (pods.isPresent()) {
            podItems = pods.orElse(podItems);
        } 
    }
    
    
}
