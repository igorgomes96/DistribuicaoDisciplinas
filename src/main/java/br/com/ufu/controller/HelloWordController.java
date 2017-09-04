/*package br.com.ufu.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.ufu.model.Professor2;
import br.com.ufu.repository.AppTest;

@Path("/sampleservice")
public class HelloWordController {
	
	@GET
	@Path("/teste/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@Path("/hello")
	@Produces("text/plain")
	public String hello(){
		return "Hello World";   
	}
	
	@GET
	@Path("/professores")
	@Produces("application/json")
	public Response professores(){
		AppTest appTest = new AppTest();
		List<Professor2> professores = appTest.testApp(); 
		String json = new Gson().toJson(professores);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}


}
*/