package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.PetRepository;

import java.util.List;

public class PetService {
    private PetRepository repository = new PetRepository();

    public Pet salvar(Pet pet) throws PetJaExistenteException{
        Pet petJaexistente = repository.findByNome(pet.getNome());

        if (petJaexistente != null) {
            throw new PetJaExistenteException(pet.getNome());
        }

        repository.save(pet);
        return pet;
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
            throw new PetNaoExistenteException("Pet com o nome " + pet.getNome() + " não existe");
        }
        return repository.update(pet);
    }
}
