/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.web;

import sg.edu.nus.iss.ejava.ca2.model.Notes;

/**
 *
 * @author benjaminng
 */
public class NotesEvent {
    
    private final Notes notes;
    
    public NotesEvent(Notes notes) {
        this.notes = notes;
    }

    public Notes getNotes() {
        return notes;
    }
    
    
    
}
