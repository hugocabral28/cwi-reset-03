public class ReposicaoCozinha {

    static void reporItem(String item) {
        if ("pao".equals(item)) {
            int quantidadeNoEstoque = ItensPorQuantidade.getPao();
            ItensPorQuantidade.setPao(quantidadeNoEstoque + 3600);
        }
        if ("torta".equals(item)) {
            int quantidadeNoEstoque = ItensPorQuantidade.getTorta();
            ItensPorQuantidade.setTorta(quantidadeNoEstoque + 4);
        }
        if ("sanduiche".equals(item)) {
            int quantidadeNoEstoque = ItensPorQuantidade.getSanduiche();
            ItensPorQuantidade.setSanduiche(quantidadeNoEstoque + 20);
        }
    }
}
