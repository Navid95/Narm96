package com.Student_Taxi_initial_Services;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	
//	sample service : first implementation
    @GET
    @Path("hi%20{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("name") String name) {
    	System.out.println("hi"+name);
        return "hi "+name;
    }
}
