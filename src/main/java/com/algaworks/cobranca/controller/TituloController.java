package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		return new ModelAndView("cadastroTitulo");
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		repository.save(titulo);
		ModelAndView view = new ModelAndView("cadastroTitulo");
		view.addObject("mensagem", "Título salvo com sucesso!");
		return view;
	}
	
	@RequestMapping
	public String pesquisar() {
		return "pesquisaTitulo";
	}
	
	@ModelAttribute("comboStatus")
	public List<StatusTitulo> comboStatus() {
		return Arrays.asList(StatusTitulo.values());
	}
	
}
