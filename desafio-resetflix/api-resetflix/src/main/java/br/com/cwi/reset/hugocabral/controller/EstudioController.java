package br.com.cwi.reset.hugocabral.controller;

import br.com.cwi.reset.hugocabral.FakeDatabase;
import br.com.cwi.reset.hugocabral.domain.Estudio;
import br.com.cwi.reset.hugocabral.request.EstudioRequest;
import br.com.cwi.reset.hugocabral.service.EstudioService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudios")
public class EstudioController {
    private EstudioService estudioService;

    public EstudioController() {
        this.estudioService = new EstudioService(FakeDatabase.getInstance());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio(@RequestBody EstudioRequest estudioRequest) throws Exception {
        this.estudioService.criarEstudio(estudioRequest);
    }

    @GetMapping
    public List<Estudio> consultarEstudios(@RequestParam Optional<String> filtroNome) throws Exception {
        return this.estudioService.consultarEstudios(filtroNome);
    }

    @GetMapping("/{id}")
    public Estudio consultarEstudios(@PathVariable Integer id) throws Exception {
        return this.estudioService.consultarEstudio(id);
    }

}
