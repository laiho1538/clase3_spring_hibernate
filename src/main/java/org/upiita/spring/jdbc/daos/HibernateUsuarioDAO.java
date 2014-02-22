package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Usuario;

@Component("usuarioDAO")
public class HibernateUsuarioDAO implements UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Usuario buscaUsuarioPorId(Integer usuarioId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Usuario usuario = (Usuario) session.get(Usuario.class, usuarioId);
		session.getTransaction().commit();
		session.close();
		return usuario;
	}

	public void creaUsuario(Usuario usuario) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(usuario);
		session.getTransaction().commit();
		session.close();
	}

	public Usuario buscaPorEmailYPassword(String email, String password) {
		Session session = sessionFactory.openSession();

		Criteria criterio = session.createCriteria(Usuario.class);

		criterio.add(Restrictions.and(Restrictions.eq("email", email),
				Restrictions.eq("password", password)));

		// Estas 2 lineas van a hacer lo mismo que la de arriba ya que hibernate
		// pone el and implicito
		// criterio.add(Restrictions.eq("email", email));
		// criterio.add(Restrictions.eq("password", password));
		Usuario usuario = (Usuario) criterio.uniqueResult();

		session.close();
		return usuario;
	}

	public List<Usuario> buscaPorNombre(String nombre) {
		
		Session session = sessionFactory.openSession();
		Criteria criterio = session.createCriteria(Usuario.class);
		criterio.add(Restrictions.like("nombre","%"+nombre+"%"));
		List<Usuario> usuarios = criterio.list();
		session.close();
		return usuarios;
	}

}
