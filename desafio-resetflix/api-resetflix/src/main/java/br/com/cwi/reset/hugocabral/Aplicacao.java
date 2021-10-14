package br.com.cwi.reset.hugocabral;

import br.com.cwi.reset.hugocabral.service.AtorService;
import br.com.cwi.reset.hugocabral.teste.ator.TestAtor;

public class Aplicacao {

    public static void main(String[] args) throws Exception {
        FakeDatabase fakeDatabase = new FakeDatabase();
        AtorService atorService = new AtorService(fakeDatabase);

        /*String nome = "Will Smith";
        LocalDate dataNascimento = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade = 1986;

        AtorRequest atorRequest = new AtorRequest(nome, dataNascimento, statusCarreira, anoInicioAtividade);
        atorService.criarAtor(atorRequest);

        AtorRequest atorRequest2 = new AtorRequest(
                "Will Smit",
                LocalDate.of(1968, Month.SEPTEMBER, 25),
                statusCarreira,
                anoInicioAtividade);

            atorService.criarAtor(atorRequest2);

        List<Ator> atores = fakeDatabase.recuperaAtores();

        System.out.println("Deve conter 1 ator, quantidade encontrada: " + atores.size());
        System.out.println("Primeiro ator deve ser 'Will Smith', valor encontrado: " + atores.get(0).getNome());
        System.out.println("Primeiro ator deve ser id '1', valor encontrado: " + atores.get(0).getId());

        System.out.println("Primeiro ator deve ser 'Will Smith', valor encontrado: " + atores.get(1).getNome());
        System.out.println("Primeiro ator deve ser id '2', valor encontrado: " + atores.get(1).getId());*/


        /*####### Testes Consultar Ator #######*/
        TestAtor.cadastraDados();
//        TestAtor.testConsultarAtorId();
//        TestAtor.buscarTodosAtores();
        TestAtor.listarAtoresComFiltro();
        /*####### Testes Consultar Diretor #######*/


    }
}
