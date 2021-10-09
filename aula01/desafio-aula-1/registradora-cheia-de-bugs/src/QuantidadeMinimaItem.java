public class QuantidadeMinimaItem {

    public static boolean precisaReposicao(String item) {
        if ("pao".equals(item)) {
            return ItensPorQuantidade.getPao() < 600;
        }

        if ("torta".equals(item)) {
            return ItensPorQuantidade.getTorta() < 10;
        }

        if ("sanduiche".equals(item)) {
            return ItensPorQuantidade.getSanduiche() <= 1;
        }

        if ("cafe".equals(item)) {
            return ItensPorQuantidade.getCafe() < 12;
        }

        if ("leite".equals(item)) {
            return ItensPorQuantidade.getLeite() < 12;
        }

        return false;
    }

}
