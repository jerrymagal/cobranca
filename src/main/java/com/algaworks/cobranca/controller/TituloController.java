package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.cobranca.enums.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private Titulos repository;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		return new ModelAndView("cadastroTitulo").addObject("titulo", new Titulo());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView salvar(@Validated Titulo titulo, Errors errors) {
		
		ModelAndView view = new ModelAndView("cadastroTitulo");
		
		if(errors.hasErrors()) {
			return view;
		}
		
		repository.save(titulo);
		view.addObject("mensagem", "Título salvo com sucesso!");
		return view;
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> titulos = repository.findAll();
		ModelAndView view = new ModelAndView("pesquisaTitulo");
		view.addObject("titulos", titulos);
		return view; 
	}
	
	@ModelAttribute("comboStatus")
	public List<StatusTitulo> comboStatus() {
		return Arrays.asList(StatusTitulo.values());
	}
	
}
