public class RelacaoPesoPreco {

    public static double retornaPrecoProduto(String item, int qtd) {
        double precoTotal = 0;
        int fatiasDaTorta = 16;

        if ("pao".equals(item)) {
            ItensPorQuantidade.retiraDoEstoque(item, qtd);
            precoTotal = 12.75 * (qtd * 60 / 1000);
        }

        if ("torta".equals(item)) {
            ItensPorQuantidade.retiraDoEstoque(item, qtd);
            precoTotal = (96.00 * qtd) / fatiasDaTorta;
        }

        if ("leite".equals(item)) {
            ItensPorQuantidade.retiraDoEstoque(item, qtd);
            precoTotal = 4.48 * qtd;
        }

        if ("cafe".equals(item)) {
//            while(ItensPorQuantidade.getCafe()<=qtd){
//                ReposicaoFornecedor.reporItem(item);
//            }
            ItensPorQuantidade.retiraDoEstoque(item, qtd);
            precoTotal = 9.56 * qtd;
        }

        if ("sanduiche".equals(item)) {
            ItensPorQuantidade.retiraDoEstoque(item, qtd);
            precoTotal = 4.5 * qtd;
        }

        return precoTotal;
    }

}
