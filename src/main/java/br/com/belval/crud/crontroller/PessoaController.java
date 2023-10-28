package br.com.belval.crud.crontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.belval.crud.model.Pessoa;
import br.com.belval.crud.model.TipoPessoa;
import br.com.belval.crud.repository.PessoaRepository;
import br.com.belval.crud.repository.TipoPessoaRepository;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private TipoPessoaRepository tipoRepository;

	@GetMapping("/pessoa/novo")
	public String novo(Model model) {
		Iterable<TipoPessoa> tipos = tipoRepository.findAll();
		model.addAttribute("tipos", tipos);
		model.addAttribute("pessoa", new Pessoa());
		return "pessoa";
	}
	
	@GetMapping("/pessoa/{id}/edit")
	public String editar(@PathVariable int id, Model model) {
		
		Pessoa pessoa = repository.findById(id);
		
		if (pessoa == null) {
			return "cadastro-nao-encontrado";
		}
		
		model.addAttribute("tipos", tipoRepository.findAll());
		model.addAttribute("pessoa", pessoa);
		
		return "pessoa";
	}
	
	@PostMapping("/pessoa/novo") // ver o que fazer
	public ModelAndView novo(Pessoa pessoa, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/pessoa/list");
		
		if (pessoa.getId() == 0) {
			redirectAttributes.addFlashAttribute("msg","Cadastro Criado com Sucesso!");
		} else {
			redirectAttributes.addFlashAttribute("msg","Cadastro atualizado!");
		}
		repository.save(pessoa);
		
		return modelAndView;
	}

	@GetMapping("/pessoa/list") // ver tbm
	public String list(Model model) {
		model.addAttribute("pessoa", repository.findByAtivo(true));
		return "lista-pessoa";
	}
	
	@GetMapping("/pessoa/{id}")
	public String detalhe(@PathVariable int id, Model model) {
		Pessoa pessoa = repository.findById(id);
		
		if (pessoa != null) {
			model.addAttribute("pessoa", pessoa);
			return "detalhe-pessoa"; // ver tbm
		}
		
		return "cadastro-nao-encontrado";
	}

	@GetMapping("/pessoa/{id}/excluir")
	public String excluir(@PathVariable int id, RedirectAttributes redirectAttributes) {
		//repository.deleteById(id);
		Pessoa pessoa = repository.findById(id);
		pessoa.setAtivo(false);
		repository.save(pessoa);
		
		redirectAttributes.addFlashAttribute("msg","Cadastro excluído!");
		
		return "redirect:/pessoa/list";
	}
	
	@GetMapping("/post-to-post-form")
	public String getPostToPostForm() {
		return "post-to-post-form";
	}
	
	@PostMapping("/redirectPostToPost")
	public ModelAndView redirectPostToPost(HttpServletRequest request) {
	    request.setAttribute(
	      View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
	    return new ModelAndView("redirect:/redirectedPostToPost");
	}
	
	@PostMapping("/redirectedPostToPost")
	public ModelAndView redirectedPostToPost() {
	    return new ModelAndView("post-to-post-form");
	}
}
