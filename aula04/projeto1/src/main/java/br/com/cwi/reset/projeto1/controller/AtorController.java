package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.exception.AtorNaoExistenteException;
import br.com.cwi.reset.projeto1.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @PostMapping
    public Ator cadastrarAtor(@RequestBody Ator ator) throws AtorNaoExistenteException {
        return atorService.cadastrarAtor(ator);
    }
    @GetMapping
    public List<Ator> listarTodos() {
        return atorService.listarTodos();
    }

    @GetMapping("/{nome}")
    public Ator buscarPorNome(@PathVariable String nome) {
        return atorService.procurarPorNome(nome);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        atorService.deletar(id);
    }

    @GetMapping("/by-filter")
    public List<Ator> buscarPorFiltro(@RequestParam Integer numeroOscars, Integer anoNascimento) {
        AtorController service;
        return atorService.buscarPorFiltro(numeroOscars, anoNascimento);
    }
}
