package br.com.cwi.reset.hugocabral.controller;

import br.com.cwi.reset.hugocabral.model.Ator;
import br.com.cwi.reset.hugocabral.response.AtorEmAtividade;
import br.com.cwi.reset.hugocabral.request.AtorRequest;
import br.com.cwi.reset.hugocabral.service.AtorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody @Valid AtorRequest atorRequest) throws Exception {
        this.atorService.criarAtor(atorRequest);
    }

    @GetMapping("/em_atividade")
    public List<AtorEmAtividade> listarAtoresEmAtividade(@RequestParam Optional<String> filtroNome) throws Exception {
        return this.atorService.listarAtoresEmAtividade(filtroNome);
    }

    @GetMapping("/{id}")
    public Ator consultarAtor(@PathVariable @Valid Integer id) throws Exception {
        return this.atorService.consultarAtor(id);
    }

    @GetMapping
    public List<Ator> consultarAtores() throws Exception {
        return this.atorService.consultarAtores();
    }

    @PutMapping("/{id}")
    public void atualizarAtor(@PathVariable @Valid Integer id, @RequestBody @Valid AtorRequest atorRequest) throws Exception {
        this.atorService.atualizarAtor(id, atorRequest);
    }

    @DeleteMapping("/{id}")
    public void removerAtor(@PathVariable @Valid Integer id) throws Exception {
        this.atorService.removerAtor(id);
    }
}
