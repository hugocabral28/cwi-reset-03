package br.com.cwi.reset.hugocabral.controller;

import br.com.cwi.reset.hugocabral.model.Diretor;
import br.com.cwi.reset.hugocabral.request.DiretorRequest;
import br.com.cwi.reset.hugocabral.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDiretor(@RequestBody @Valid DiretorRequest diretorRequest) throws Exception {
        this.diretorService.cadastrarDiretor(diretorRequest);
    }

    @GetMapping
    public List<Diretor> listarDiretores(@RequestParam(required = false) String filtroNome) throws Exception {
        return this.diretorService.listarDiretores(filtroNome);
    }

    @GetMapping("/{id}")
    public Diretor consultarDiretor(@PathVariable @Valid Integer id) throws Exception {
        return this.diretorService.consultarDiretor(id);
    }

    @PutMapping("/{id}")
    public void atualizarDiretor(@PathVariable @Valid Integer id, @RequestBody @Valid DiretorRequest diretorRequest) throws Exception {
        this.diretorService.atualizarDiretor(id, diretorRequest);
    }

    @DeleteMapping("/{id}")
    public void removerDiretores(@PathVariable @Valid Integer id) throws Exception {
        this.diretorService.removerDiretores(id);
    }
}
