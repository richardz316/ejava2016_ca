package sg.edu.nus.iss.ejava.ca.business;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.Stateless;
import sg.edu.nus.iss.ejava.ca.model.People;
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
public class PeopleBean {
    
    @PersistenceContext
    private EntityManager em;
    
    public void add(String name, String email) {
        People people = new People();
        people.setPid(UUID.randomUUID().toString().substring(0, 8));
        people.setEmail(email);
        people.setName(name);
        
        em.persist(people);
    }
    
    public Optional<People> getPeopleByEmail(String email) {
        TypedQuery<People> query = em.createNamedQuery(
				"People.findByEmail", People.class);
        query.setParameter("email", email);
        List<People> result = query.getResultList();
        if (result.size() > 0)
                return (Optional.of(result.get(0)));
        return (Optional.empty());
    }
    
}
