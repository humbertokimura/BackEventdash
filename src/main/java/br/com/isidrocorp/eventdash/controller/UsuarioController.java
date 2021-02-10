package br.com.isidrocorp.eventdash.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.eventdash.dao.UsuarioDAO;
import br.com.isidrocorp.eventdash.model.Usuario;

@CrossOrigin("*")
@RestController
public class UsuarioController {

	@Autowired // o Autowired indica a INJECAO de DEPENDENCIAS
	// a propria maq do tomcat faz a inicialização new. Já pode usar.
	private UsuarioDAO dao;

//		@GetMapping("/usuarios")
//		public ArrayList<Usuario> recuperarTodos(){
//			ArrayList<Usuario> lista;
//			lista = (ArrayList<Usuario>)dao.findAll();
//			return lista;
//		}

	// No postman os dados de racf, senha, email são capturado pelo @RequestBody abaixo
	@PostMapping("/login")
	public ResponseEntity<Usuario> fazerLogin(@RequestBody Usuario dados) {

		Usuario resultado = dao.findByEmailOrRacf(dados.getEmail(), dados.getRacf());
		if (resultado != null) { // se o usuario existe no banco de dados
			if (resultado.getSenha().equals(dados.getSenha())) {    // as senhas batem
				resultado.setSenha("********");
				return ResponseEntity.ok(resultado);
			}
			return ResponseEntity.status(401).build();  // usuario existe, mas senha não confere
		}
		return ResponseEntity.notFound().build();
	}
}
