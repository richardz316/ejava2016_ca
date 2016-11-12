package epod.rest;

import epod.business.PodBean;
import epod.exception.PodNotFoundException;
import epod.model.Pod;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
//import com.sun.jersey.multipart.FormDataParam;

@RequestScoped
@Path("/upload")
public class EPodResource {

    @EJB
    private PodBean podBean;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("podId") String podId,
            @FormDataParam("note") String note,
            @FormDataParam("image") InputStream uploadedInputStream,
            @FormDataParam("time") long time
        ) {
    
        System.out.println("############ begin uploadFile ############");
        
        // check if all form parameters are provided
        if (podId == null || uploadedInputStream == null || time <= 0) {
            return Response.status(400).entity("Invalid form data").build();
        }
        
        String errorMessage = null;
        
        try {
            podBean.savePod(Integer.valueOf(podId), note, saveFile(uploadedInputStream), new Date(time));
        } catch (IOException ex) {
            Logger.getLogger(EPodResource.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = "Save Pod failed!";
        } catch (PodNotFoundException ex) {
            Logger.getLogger(EPodResource.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = "pod is not found!";
        }
        
        if(errorMessage != null){
            System.out.println("############ upload failed with error: "+ errorMessage);
            return Response.status(500).entity(errorMessage).build();
        }
        
        Pod pod = podBean.findPod(Integer.valueOf(podId));
        
        podBean.sendToHq(pod);
        return Response.status(Response.Status.CREATED).build();
    }

    private byte[] saveFile(InputStream is) throws IOException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = is.read(data, 0, data.length)) != -1) {
          buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();
    }
    
    

}
