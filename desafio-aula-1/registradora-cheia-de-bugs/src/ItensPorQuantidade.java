public class ItensPorQuantidade {
    static int fatiaDeTorta = 16;

    private static int pao = 3600;
    private static int torta = 4 * fatiaDeTorta;
    private static int sanduiche = 20;
    private static int leite = 20;
    private static int cafe = 20;

    public static int retornaQuantidadeNoEstoque(String item){
        int quantidadeItem = 0;
        if ("pao".equals(item)) {
            quantidadeItem = getPao();
        }

        if ("torta".equals(item)) {
            quantidadeItem = getTorta();
        }

        if ("leite".equals(item)) {
            quantidadeItem = getLeite();
        }

        if ("cafe".equals(item)) {
            quantidadeItem = getCafe();
        }

        if ("sanduiche".equals(item)) {
            quantidadeItem = getSanduiche();
        }
        return quantidadeItem;
    }

    public static void retiraDoEstoque(String item, int quantidadeItem){
        int retiraItem = 0;
        if ("pao".equals(item)) {
            retiraItem = getPao() - quantidadeItem;
            ItensPorQuantidade.setPao(retiraItem);
        }

        if ("torta".equals(item)) {
            retiraItem = getTorta() - quantidadeItem;
            ItensPorQuantidade.setTorta(retiraItem);
        }

        if ("leite".equals(item)) {
            retiraItem = getLeite() - quantidadeItem;
            ItensPorQuantidade.setLeite(retiraItem);
        }

        if ("cafe".equals(item)) {
            retiraItem = getCafe() - quantidadeItem;
            ItensPorQuantidade.setCafe(retiraItem);
        }

        if ("sanduiche".equals(item)) {
            retiraItem = getSanduiche() - quantidadeItem;
            ItensPorQuantidade.setSanduiche(retiraItem);
        }
    }

    public static int getPao() {
        return pao;
    }

    public static void setPao(int pao) {
        ItensPorQuantidade.pao = pao;
    }

    public static int getTorta() {
        return torta;
    }

    public static void setTorta(int torta) {
        ItensPorQuantidade.torta = torta;
    }

    public static int getSanduiche() {
        return sanduiche;
    }

    public static void setSanduiche(int sanduiche) {
        ItensPorQuantidade.sanduiche = sanduiche;
    }

    public static int getLeite() {
        return leite;
    }

    public static void setLeite(int leite) {
        ItensPorQuantidade.leite = leite;
    }

    public static int getCafe() {
        return cafe;
    }

    public static void setCafe(int cafe) {
        ItensPorQuantidade.cafe = cafe;
    }
}
