package com.algaworks.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;

@Service
public class TituloService {
	
	@Autowired
	private Titulos repository;
	
	public void salvar(Titulo titulo) {
		try {
			repository.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido!");
		}
	}
	
	public void excluir(Long codigo) {
		repository.delete(codigo);
	}
	
	public List<Titulo> listar() {
		return repository.findAll();
	}

}
