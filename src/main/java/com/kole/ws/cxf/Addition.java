package com.kole.ws.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/addition")
public interface Addition {
	
	@GET
	@Produces("text/plain")
	@Path("/{arg1}/{arg2}")
	Integer add(@PathParam("arg1")Integer arg1, @PathParam("arg2s")Integer arg2);
}
