package br.com.isidrocorp.eventdash.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.isidrocorp.eventdash.model.Usuario;

/* esse cara é a "ponte" para gerar os SQLs de Inserts, Deletes etc 
 * 
 */


// Veja o Query by Method em JPA 
public interface UsuarioDAO extends CrudRepository<Usuario, Integer>  {
			
	public Usuario findByEmailOrRacf(String email, String racf);
}
