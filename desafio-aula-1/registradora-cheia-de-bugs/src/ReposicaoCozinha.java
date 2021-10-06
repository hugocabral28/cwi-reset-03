public class ReposicaoCozinha {

    static void reporItem(String item) {
        if ("paes".equals(item)) {
            ItensPorQuantidade.setPao(3600);
        }
        if ("torta".equals(item)) {
            ItensPorQuantidade.setTorta(4);
        }
        if ("sanduiche".equals(item)) {
            ItensPorQuantidade.setSanduiche(20);
        }
    }
}
