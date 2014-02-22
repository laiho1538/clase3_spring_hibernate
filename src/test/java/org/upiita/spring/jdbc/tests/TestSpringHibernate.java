package org.upiita.spring.jdbc.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.jdbc.daos.UsuarioDAO;
import org.upiita.spring.jdbc.entidades.Usuario;

public class TestSpringHibernate {

	public static void main(String[] args) {
		// creamos el contexto de Spring
		ApplicationContext contexto = new ClassPathXmlApplicationContext(
				"/contexto.xml");
		UsuarioDAO usuarioDAO = (UsuarioDAO) contexto.getBean("usuarioDAO");
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(3);
		usuario.setNombre("Alejandro");
		usuario.setEmail("alejandro@email.com");
		usuario.setPassword("123");

		usuarioDAO.creaUsuario(usuario);
		System.out.println("datos guardados");
		
		Usuario usuarioBD = usuarioDAO.buscaUsuarioPorId(3);
		System.out.println("usuario encontrado es:" + usuarioBD);
		
		Usuario usuarioCriteria = usuarioDAO.buscaPorEmailYPassword("alejandro@email.com", "123");
		System.out.println("Usuario por email y password es = " + usuarioCriteria);
		
		//PRUEBA DEL CRITERIO LIKE
		List<Usuario> usuarios = usuarioDAO.buscaPorNombre("z");
		System.out.println("usuarios por nombre " + usuarios);

	}

}
