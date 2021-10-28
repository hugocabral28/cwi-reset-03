package br.com.cwi.reset.hugocabral.controller;

import br.com.cwi.reset.hugocabral.model.Filme;
import br.com.cwi.reset.hugocabral.request.FilmeRequest;
import br.com.cwi.reset.hugocabral.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio(@RequestBody @Valid FilmeRequest filmeRequest) throws Exception {
        this.filmeService.criarFilme(filmeRequest);
    }

    @GetMapping
    public List<Filme> consultarFilmes(
            @RequestParam String nomeFilme,
            @RequestParam String nomeDiretor,
            @RequestParam String nomePersonagem,
            @RequestParam String nomeAtor) throws Exception {
        return filmeService.consultarFilmes(nomeFilme, nomeDiretor, nomePersonagem, nomeAtor);
    }

    @DeleteMapping("/{id}")
    public void removerFilme(@PathVariable @Valid Integer id) throws Exception {
        this.filmeService.removerFilme(id);
    }

}
