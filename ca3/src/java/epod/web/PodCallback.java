/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.web;

import epod.business.PodBean;
import epod.model.Pod;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yeojc
 */
@WebServlet("/callback")
public class PodCallback extends HttpServlet {

    @EJB
    private PodBean podBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String ackId = request.getParameter("ackId");
        int podId = Integer.parseInt(request.getParameter("podId"));
        Pod pod = podBean.findPod(podId);        

        if (pod != null) {
            pod.setAckId(ackId);
            podBean.updateAckId(pod);
        } else {
            System.out.println("Pod not found for: " + podId);
        }
    }
}
