package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    public Pet salvar(Pet pet) throws PetJaExistenteException{
        Pet petJaexistente = repository.findByNome(pet.getNome());

        if (petJaexistente != null) {
            throw new PetJaExistenteException(pet.getNome());
        }
        return repository.save(pet);
    }

    public List<Pet> ListarTodos(){
        return repository.findAll();
    }

    public Pet buscarPorNome(String nome){
       return repository.findByNome(nome);
    }

    public void deletarPet(String nomepet) throws PetNaoExistenteException {
        Pet petExistente = repository.findByNome(nomepet);
        if (petExistente == null) {
            throw new PetNaoExistenteException(nomepet);
        }
        repository.delete(petExistente);
    }

    public Pet atualizar(Pet pet) throws PetNaoExistenteException{
        Pet petExistente = buscarPorNome(pet.getNome());
        if (petExistente == null) {
            throw new PetNaoExistenteException(pet.getNome());
        }
        return repository.update(pet);
    }
}
