/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.rest;

import epod.business.PodBean;
import epod.model.Delivery;
import epod.model.Pod;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;

/**
 *
 * @author E0015387
 */
public class EPodResourceTest {
    
    @Test
    public void testUpload() throws IOException {
	PodBean task = new PodBean();
	Delivery deliver = new Delivery();
	deliver.setPkgId(1);
	deliver.setName("Tester");
	deliver.setAddress("Test Address");
	
	Pod pod = new Pod();
	pod.setDelivery(deliver);
	pod.setPodId(1);
	pod.setNote("Testing123");
        
        File fnew=new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg");
        BufferedImage originalImage=ImageIO.read(fnew);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", baos );
        byte[] imageInByte=baos.toByteArray();
        
        pod.setImage(imageInByte);
        
        
	task.sendToHq(pod);
	
    }
}
