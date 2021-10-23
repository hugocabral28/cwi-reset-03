package br.com.cwi.reset.projeto1.controller;


import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> consultarTodos() {
        return petService.ListarTodos();
    }

    @GetMapping("/{nome}")
    public Pet buscarPorNome(@PathVariable String nome) throws PetNaoExistenteException {
        return petService.buscarPorNome(nome);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet cadastrarPet(@RequestBody Pet pet) throws PetJaExistenteException{
        return petService.salvar(pet);
    }

    @PutMapping
    public Pet atualizarPet(@RequestBody Pet pet) throws PetNaoExistenteException{
            return petService.atualizar(pet);
    }

    @DeleteMapping("/{id}")
    public void deletarPet(@PathVariable Integer id) throws PetNaoExistenteException{
            petService.deletarPet(id);
    }

}
