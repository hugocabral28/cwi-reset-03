package br.com.cwi.reset.projeto1.controller;


import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService = new PetService();

    @GetMapping
    public List<Pet> consultarTodos() {
        return petService.ListarTodos();
    }

    @GetMapping("/{nome}")
    public Pet buscarPorNome(@PathVariable String nome) {
        return petService.buscarPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<Pet> cadastrarPet(@RequestBody Pet pet) {
        try {
            Pet petSalvo = petService.salvar(pet);
            return ResponseEntity.ok(petSalvo);
        } catch (PetJaExistenteException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity atualizarPet(@RequestBody Pet pet) {
        try {
            return ResponseEntity.ok(petService.atualizar(pet));
        } catch (PetNaoExistenteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity deletarPet(@PathVariable String nome) {
        try {
            petService.deletarPet(nome);
            return ResponseEntity.ok().build();
        } catch (PetNaoExistenteException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
