/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ejava.ca2.web;

/**
 *
 * @author rzhao
 */
import java.io.Serializable;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.UserTransaction;
import sg.edu.nus.iss.ejava.ca2.business.GroupBean;
import sg.edu.nus.iss.ejava.ca2.business.UserBean;

@ViewScoped
@Named
public class LoginView implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EJB private UserBean userBean;
    @EJB private GroupBean groupBean;
    
    @Resource UserTransaction ut;
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @PermitAll
    public String login() {

        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequest();

        try {
            req.login(username, password);
        } catch (ServletException ex) {
            FacesMessage msg = new FacesMessage("Incorrect login");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return ("");
        }

        return "/secure/menu";
        
        //return "/secure/menu?faces-redirect=true";

    }
    
    @PermitAll
    public String register() {
        try {
            ut.begin();
            userBean.add(username, password);
            groupBean.add("user role", username);
            ut.commit();
            return "registersuccess";
        }
        catch (Exception ex) {
            FacesMessage msg = new FacesMessage("Error encountered. Please contact system admin");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        return null;
    }

}
