/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epod.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author E0015387
 */
@ApplicationPath("/")
public class AppConfig extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(EPodResource.class);
        s.add(MultiPartFeature.class);
        s.add(ItemResource.class);
        return s;
    }
}
