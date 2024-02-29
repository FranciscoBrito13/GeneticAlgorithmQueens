import java.util.Arrays;

public class AlgoritmoGenetico {
    private static final int NUM_POPULACAO = 100;
    private static final double PROB_MUTACAO = 0.05;

    private static final double PROB_SELECAO = 0.2;
    private static final double NUM_GERACOES = 1000;

    private static final int TAMANHO_TABULEIRO = 8;
    private TabuleiroRainhas[] populacao;

    public AlgoritmoGenetico(){
        populacao = new TabuleiroRainhas[NUM_POPULACAO];
        criaPopulacao();
        for(int i = 0; i < 100; i++){
           System.out.println(populacao[i]);
        }

    }

    private void criaPopulacao() {
        for(int i = 0; i < NUM_POPULACAO; i++){
            TabuleiroRainhas tr = new TabuleiroRainhas(TAMANHO_TABULEIRO);
            populacao[i] = tr;
        }

    }

    public TabuleiroRainhas iniciaProcura(){

        Arrays.sort(populacao);
        for(int i = 0; i < NUM_GERACOES; i++){
            if(populacao[0].calculaFitness() == 28){
                break;
            }
            for(int j = 0; j < NUM_POPULACAO/2; j++){
                TabuleiroRainhas paiUm = selectParent();
                TabuleiroRainhas paiDois = selectParent();
                TabuleiroRainhas[] filhos = crossOver(paiUm, paiDois);
            }
        }
        return null;
    }

    private TabuleiroRainhas selectParent(){
        TabuleiroRainhas paiUm = populacao[(int) (Math.random()*NUM_POPULACAO)];
        TabuleiroRainhas paiDois = populacao[(int) (Math.random()*NUM_POPULACAO)];
        if(paiUm.calculaFitness() > paiDois.calculaFitness()){
            return paiUm;
        }
        return paiDois;
    }

    private TabuleiroRainhas[] crossOver(TabuleiroRainhas a, TabuleiroRainhas b){
        int rand = (int) (Math.random()*TAMANHO_TABULEIRO);

        int temp1 = a.getPosicoes_rainhas()[rand];
        int temp2 = b.getPosicoes_rainhas()[rand];

        a.setPosicoes_rainhas(rand, temp2);

        return a;

    }


}
