package sg.edu.nus.iss.ejava.ca.business;


import sg.edu.nus.iss.ejava.ca.model.Appointment;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author E0015387
 */
@Stateless
public class AppointmentBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public Optional<List<Appointment>> getAllAppointmentByEmail(String email) {
        TypedQuery<Appointment> query = em.createNamedQuery(
				"Appointment.findByEmail", Appointment.class);
        query.setParameter("email", email);
        return Optional.ofNullable(query.getResultList());
    }
    
}
