package br.com.ufu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import br.com.ufu.view.UsuarioView;

@Path("/login")
public class LoginControllerImpl {
	
	@POST
	@Path("/efetuarLogin")
	@Consumes("application/json")
	public Boolean entrarFilaDisciplina( @Context HttpServletRequest request, UsuarioView usuarioView ) {
		
		if (usuarioView.getUsuario().equals("UFU") && usuarioView.getSenha().equals("ufu@2015")) {
			
			HttpSession session = request.getSession();
			
			session.setAttribute("usuario", usuarioView.getUsuario());
			
			session.setAttribute("administrador", true);
			
			return true;
			
		}
		
		return false;
		
	}
	
	
	@GET
	@Path("/validaUsuarioAdministrador")
	@Produces("application/json")
	public Boolean validaUsuarioAdministrador(@Context HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Boolean administrador = (Boolean) session.getAttribute("administrador");
		
		return administrador;
 
	}
	
}
