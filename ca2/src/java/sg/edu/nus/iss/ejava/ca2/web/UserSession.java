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
import java.io.Serializable;
import java.security.Principal;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named
public class UserSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject private Principal user;

    public String getUsername() {
        return (user.getName());
    }
    
    public void setUserName(String n) {}

    public String logout() {

            ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest req = (HttpServletRequest)ctx.getRequest();
            HttpServletResponse resp = (HttpServletResponse)ctx.getResponse();
            try {
                HttpSession session = req.getSession();
		System.out.println("logout user: " + req.getRemoteUser());
		session.invalidate();
		return "/login?faces-redirect=true";
            } catch (Exception ex) { ex.printStackTrace();}
            
            return null;
    }
	
}
