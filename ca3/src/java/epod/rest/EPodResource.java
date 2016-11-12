package epod.rest;

import epod.business.PodBean;
import epod.exception.PodNotFoundException;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
//import com.sun.jersey.multipart.FormDataParam;

@RequestScoped
@Path("/upload")
public class EPodResource {

    @EJB
    private PodBean podBean;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormParam("epodId") String podId,
            @FormParam("note") String note,
//            @FormParmData("image") File file,
            @FormDataParam("image") InputStream uploadedInputStream,
	    @FormDataParam("image") FormDataContentDisposition fileDetail,
            @FormParam("time") long time) {
    
        // check if all form parameters are provided
//        if (podId == null || uploadedInputStream == null || fileDetail == null || time <= 0) {
//            return Response.status(400).entity("Invalid form data").build();
//        }
        
        String errorMessage = null;
        
//        try {
//            podBean.savePod(Integer.valueOf(podId), note, saveFile(uploadedInputStream), new Date(time));
//        } catch (IOException ex) {
//            Logger.getLogger(EPodResource.class.getName()).log(Level.SEVERE, null, ex);
//            errorMessage = "Save Pod failed!";
//        } catch (PodNotFoundException ex) {
//            Logger.getLogger(EPodResource.class.getName()).log(Level.SEVERE, null, ex);
//            errorMessage = "pod is not found!";
//        }
//        
//        if(errorMessage != null){
//            return Response.status(500).entity(errorMessage).build();
//        }
        
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
