/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.web;

/**
 *
 * @author benjaminng
 */
public class NotesEvent {
    
    private final String category;
    
    public NotesEvent(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
    
    
    
}
