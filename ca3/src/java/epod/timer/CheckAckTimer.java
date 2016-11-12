/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.timer;

import epod.business.PodBean;
import epod.model.Pod;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TimerService;

/**
 *
 * @author yeojc
 */
@Singleton
public class CheckAckTimer {

    @EJB
    private PodBean podBean;

    @Schedule(second = "*/15", minute = "*", hour = "*", persistent = false)
    public void CheckAckId() {
        List<Pod> podList = podBean.checkAckId();

        if (podList.size() != 0) {
            System.out.println("Contain pods to resend: " + podList.size());
        } else {
            System.out.println("Nothing to resend: " + podList.size());
        }

        System.out.println("checked: " + new Date());

    }
}
