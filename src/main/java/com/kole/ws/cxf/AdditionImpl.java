package com.kole.ws.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/addition")
public class AdditionImpl implements Addition {

	@GET
	@Produces("text/plain")
	@Path("/{arg1}/{arg2}")
	public Integer add(@PathParam("arg1")Integer arg1, @PathParam("arg2")Integer arg2) {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return new Integer(arg1.intValue() + arg2.intValue());
	}

}
