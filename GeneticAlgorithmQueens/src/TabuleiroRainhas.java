public class TabuleiroRainhas implements Comparable<TabuleiroRainhas>{
    private static int TAMANHO_TABULEIRO;
    private int[] posicoes_rainhas;

    public TabuleiroRainhas(int tamanho_tabuleiro){
        TAMANHO_TABULEIRO = tamanho_tabuleiro;
        criarVetorAleatoriamente();
    }

    public TabuleiroRainhas(int[] vetor){
        posicoes_rainhas = vetor;
    }

    private void criarVetorAleatoriamente(){
        posicoes_rainhas = new int[TAMANHO_TABULEIRO];
        for(int i = 0; i < posicoes_rainhas.length; i++){
            posicoes_rainhas[i] = (int) (Math.random()* TAMANHO_TABULEIRO);
        }
    }

    public int calculaFitness() {
        int fitness = (TAMANHO_TABULEIRO * (TAMANHO_TABULEIRO - 1)) / 2;

        // Verify rows and diagonals (considering both main and anti-diagonals)
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = i + 1; j < TAMANHO_TABULEIRO; j++) {
                if (posicoes_rainhas[i] == posicoes_rainhas[j] ||
                        Math.abs(i - j) == Math.abs(posicoes_rainhas[i] - posicoes_rainhas[j])) {
                    fitness--;
                }
            }
        }

        return fitness;
    }

    public String toString(){
        String result = "";
        for(int linha = TAMANHO_TABULEIRO - 1; linha >= 0; linha--){
            for(int i = 0; i < posicoes_rainhas.length; i++){
               if(posicoes_rainhas[i] == linha){
                   result += "Q ";
               } else {
                   result += ". ";
               }
            }
            result += System.lineSeparator();
        }
        return result + System.lineSeparator() + "Fitness= " + calculaFitness();
    }

    @Override
    public int compareTo(TabuleiroRainhas o) {
       return Integer.compare(o.calculaFitness(), calculaFitness());
    }

    public int[] getPosicoes_rainhas(){
       return posicoes_rainhas;
    }

    public void changeRandValue(){
        int rand = (int) (Math.random() * TAMANHO_TABULEIRO);
        posicoes_rainhas[rand] = (int) (Math.random() * TAMANHO_TABULEIRO);

    }


}
