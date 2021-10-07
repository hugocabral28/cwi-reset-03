
public class Registradora {

    public static void main(String[] args) {
//        System.out.println(ItensPorQuantidade.getSanduiche());
        System.out.println("######## primeiroBug ########");
        primeiroBug();
        System.out.println("######## segundoBug ########");
        segundoBug();
        System.out.println("######## terceiroBug ########");
        terceiroBug();
        System.out.println("######## quartoBug ########");
        quartoBug();
        System.out.println("######## quintoBug ########");
        quintoBug();
        System.out.println("######## sextoBug ########");
        sextoBug();

    }

    private static double registrarItem(String item, int quantidade) {
        int quantidadeNoEstoque = ItensPorQuantidade.retornaQuantidadeNoEstoque(item);
        double precoItem = 0;

        if(quantidadeNoEstoque >= quantidade){
            ItensPorQuantidade.retiraDoEstoque(item, quantidade);
        }else{
            if ("leite".equals(item) || "cafe".equals(item)) {
                ReposicaoFornecedor.reporItem(item);
            }
            ItensPorQuantidade.retiraDoEstoque(item, quantidade);
        }
        precoItem = (quantidadeNoEstoque>0) ? RelacaoPesoPreco.retornaPrecoProduto(item, quantidade) : 0;

        if (QuantidadeMinimaItem.precisaReposicao(item)) {
            if ("pao".equals(item) || "sanduiche".equals(item) || "torta".equals(item)) {
                if (!DataProjeto.cozinhaEmFuncionamento()) {
                    System.out.println("Cozinha fechada!");
                    System.out.println("A reposição da(o) " + item + " não está disponível.");
                    System.out.println("Estoque disponível: " + ((quantidadeNoEstoque<0)? 0 : quantidadeNoEstoque));
                    System.out.println("Calculando valor do pedido de acordo com estoque disponível");
                    precoItem = (quantidadeNoEstoque>0) ? RelacaoPesoPreco.retornaPrecoProduto(item, quantidadeNoEstoque) : 0;
                }
                if(DataProjeto.isDiaUtil()) {
                    ReposicaoCozinha.reporItem(item);
                }
            }

            if ("leite".equals(item) || "cafe".equals(item)) {
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

    }

    private static void segundoBug() {
        DataProjeto.criarDataComCozinhaEncerradaMasComDiaUtil();
        String item = "torta";
        int quantidade = 10;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

    }

    private static void terceiroBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
        String item = "cafe";
        int quantidade = 40;

        double precoTotal = registrarItem(item, quantidade);

        System.out.println(String.format("Valor total: %.2f", precoTotal));

    }

    private static void quartoBug() {
        DataProjeto.criarDataComCozinhaFuncionando();
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
