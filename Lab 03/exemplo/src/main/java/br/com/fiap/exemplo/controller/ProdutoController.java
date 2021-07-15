package br.com.fiap.exemplo.controller;

import br.com.fiap.exemplo.model.Produto;
import br.com.fiap.exemplo.repository.CategoriaRepository;
import br.com.fiap.exemplo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("cadastrar")
    public String abrirFormulario(Produto produto, Model model){
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "produto/form";
    }

    @PostMapping("cadastrar")
    public String processarForm(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()) {
            return "produto/form";
        }
        redirectAttributes.addFlashAttribute("msg", "Cadastrado!");
        repository.save(produto);
        return "redirect:listar";
    }

    @GetMapping("listar")
    public String listarProdutos(Model model){
        model.addAttribute("produtos", repository.findAll());
        return "produto/lista";
    }

    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") int codigo, Model model){
        model.addAttribute("produto",repository.findById(codigo));
        return "produto/form";
    }

    @PostMapping("excluir")
    public String remover(int codigo, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msg", "Removido!");
        repository.deleteById(codigo);
        return "redirect:listar";
    }
}
