public class ItensPorQuantidade {
    static int fatiaDeTorta = 16;

    static int pao = 3600;
    static int torta = 4 * fatiaDeTorta;
    static int sanduiche = 20;
    static int leite = 20;
    static int cafe = 20;

    public static int retornaQuantidadeNoEstoque(String item){
        int quantidadeNoEstoque = 0;

        if ("pao".equals(item)) {
            quantidadeNoEstoque = pao;
        }

        if ("torta".equals(item)) {
            quantidadeNoEstoque = torta;
        }

        if ("leite".equals(item)) {
            quantidadeNoEstoque = leite;
        }

        if ("cafe".equals(item)) {
            quantidadeNoEstoque = cafe;
        }

        if ("sanduiche".equals(item)) {
            quantidadeNoEstoque = sanduiche;
        }
        return quantidadeNoEstoque;
    }

}
