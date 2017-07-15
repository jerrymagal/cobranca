package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cobranca.enums.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "cadastroTitulo";
	
	@Autowired
	private Titulos repository;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		return retornaViewCadastro(new Titulo());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		repository.save(titulo);
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		return "redirect:/titulos/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> titulos = repository.findAll();
		ModelAndView view = new ModelAndView("pesquisaTitulo");
		view.addObject("titulos", titulos);
		return view; 
	}
	
	@RequestMapping("/editar/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Titulo titulo) {
		return retornaViewCadastro(titulo);
	}
	
	@ModelAttribute("comboStatus")
	public List<StatusTitulo> comboStatus() {
		return Arrays.asList(StatusTitulo.values());
	}
	
	private ModelAndView retornaViewCadastro(Titulo titulo) {
		return new ModelAndView(CADASTRO_VIEW).addObject("titulo", titulo);
	}
	
}
