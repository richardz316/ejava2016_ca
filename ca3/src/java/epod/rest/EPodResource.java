package epod.rest;

import epod.business.PodBean;
import epod.model.Pod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

@RequestScoped
@Path("/epod")
public class EPodResource {

    @EJB
    private PodBean podBean;

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormParam("podId") String podId, @FormParam("note") String note,
            @FormParam("image") File file, @FormParam("time") long time) {
        
        if (podId == null || file == null || time <= 0) {
            return Response.status(Response.Status.PRECONDITION_FAILED).build();
        }
        
        Optional<List<Pod>> pods = podBean.findByPodId(podId);
        if(!pods.isPresent() || pods.get() == null || pods.get().size() <= 0){
            return Response.status(500).entity("pod is not found!").build();
        }
        
        Pod pod = null;
        
        if(pods.isPresent()){
            List<Pod> podItems = pods.get();
            if(null != podItems){
                pod = podItems.get(0);
            }
        }
        
        pod.setNote(note);
        pod.setDeliveryDate(new Date(time));
        
        try {
            pod.setImage(saveFile(file));
        } catch (IOException ex) {
            Logger.getLogger(EPodResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(500).entity("Upload Image failed!").build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    private byte[] saveFile(File file) throws IOException {

        FileInputStream fileInputStream = null;

        byte[] bFile = new byte[(int) file.length()];

        //convert file into array of bytes
        fileInputStream = new FileInputStream(file);
        fileInputStream.read(bFile);
        fileInputStream.close();
        
        return bFile;
    }

}
