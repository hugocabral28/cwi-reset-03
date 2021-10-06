
public class Registradora {

    public static void main(String[] args) {
        System.out.println("######## primeiroBug ########");
        primeiroBug();
        System.out.println("######## segundoBug ########");
        segundoBug();
        System.out.println("######## terceiroBug ########");
        terceiroBug();
        System.out.println("######## quartoBug ########");
        quartoBug();
//        System.out.println("######## quintoBug ########");
//        quintoBug();
//        System.out.println("######## sextoBug ########");
//        sextoBug();
    }

    private static double registrarItem(String item, int quantidade) {
        int quantidadeNoEstoque = ItensPorQuantidade.retornaQuantidadeNoEstoque(item);
        if(quantidadeNoEstoque < quantidade){
            if ("leite".equals(item) || "cafe".equals(item)) {
                System.out.println("Fornecedor repondo item!");
                ReposicaoFornecedor.reporItem(item);
            }
            if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    System.out.println("Cozinha fechada!");
                    System.out.println("A reposição da(o) " + item + " não está disponível.");
                    System.out.println("Estoque disponível: " + quantidadeNoEstoque);
                    System.out.println("Calculando valor do pedido de acordo com estoque disponível...");
                }
                System.out.println("Cozinha repondo item!");
                ReposicaoCozinha.reporItem(item);
            }
        }
        double precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidade);

        if (QuantidadeMinimaItem.precisaReposicao(item)) {
            if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    System.out.println("Cozinha fechada!");
                    System.out.println("A reposição da(o) " + item + " não está disponível.");
                    System.out.println("Estoque disponível: " + quantidadeNoEstoque);
                    System.out.println("Calculando valor do pedido de acordo com estoque disponível...");
                    precoItem = RelacaoPesoPreco.retornaPrecoProduto(item, quantidadeNoEstoque);
                }
                System.out.println("Cozinha repondo item!");
                ReposicaoCozinha.reporItem(item);
            }

            if ("leite".equals(item) || "cafe".equals(item)) {
                System.out.println("Fornecedor repondo item!");
                ReposicaoFornecedor.reporItem(item);
            }
        }


        return precoItem;
    }

    private static void primeiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "sanduiche";
        int quantidade = 4;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque de sanduiche " + ItensPorQuantidade.getSanduiche());
    }

    private static void segundoBug() {
        DataProjeto.criarDataComCozinhaEncerradaMasComDiaUtil();
        String item = "torta";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque de torta " + ItensPorQuantidade.getTorta());
    }

    private static void terceiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "cafe";
        int quantidade = 40;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque de cafe " + ItensPorQuantidade.getCafe());
    }

    private static void quartoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);
        System.out.println("Cliente 1");
        System.out.println(String.format("Valor total: %.2f", precoTotal));
        System.out.println("Estoque de sanduiche " + ItensPorQuantidade.getSanduiche());

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);
        System.out.println("Cliente 2");
        System.out.println(String.format("Valor total: %.2f", precoTotal2));
        System.out.println("Estoque de sanduiche " + ItensPorQuantidade.getSanduiche());
    }

    private static void quintoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "pao";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));
    }

    private static void sextoBug() {
        DataProjeto.criarDataComCozinhaEncerradaSemDiaUtil();
        // Cliente 1
        String item = "sanduiche";
        int quantidade = 20;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

        // Cliente 2
        String item2 = "sanduiche";
        int quantidade2 = 5;

        double precoTotal2 = registrarItem(item2, quantidade2);

        System.out.println(String.format("Valor total: %.2f", precoTotal2));
    }

}
