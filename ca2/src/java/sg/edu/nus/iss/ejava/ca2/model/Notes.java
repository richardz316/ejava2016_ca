/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author E0015387
 */
public class Notes implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int noteid;
    
    private String userid;
    
    private String title;
    private String category;
    private String content;
    
    private Date postdate;
    
    
}
