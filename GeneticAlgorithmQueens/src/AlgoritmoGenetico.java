import java.util.Arrays;

public class AlgoritmoGenetico {
    private static final int NUM_POPULACAO = 100;
    private static final double PROB_MUTACAO = 0.05;

    private static final double PROB_SELECAO = 0.2;
    private static final double NUM_GERACOES = 1000000;


    private static final int TAMANHO_TABULEIRO = 100;
    private TabuleiroRainhas[] populacao;

    public AlgoritmoGenetico(){
        populacao = new TabuleiroRainhas[NUM_POPULACAO];
        criaPopulacao();


    }

    private void criaPopulacao() {
        for(int i = 0; i < NUM_POPULACAO; i++){
            TabuleiroRainhas tr = new TabuleiroRainhas(TAMANHO_TABULEIRO);
            populacao[i] = tr;
        }

    }

    public TabuleiroRainhas iniciaProcura(){
        for(int i = 0; i < NUM_GERACOES; i++){
            TabuleiroRainhas[] novaPopulacao = new TabuleiroRainhas[NUM_POPULACAO];
            Arrays.sort(populacao);

            System.out.println("Numero da geração: " + i);

            for(int k = 0; k < NUM_POPULACAO; k++){
                System.out.println(populacao[k]);
            }
            if(populacao[0].calculaFitness() == (TAMANHO_TABULEIRO * (TAMANHO_TABULEIRO - 1)) / 2){
                System.out.println("Resultado encontrado na geração: " + i);
                return populacao[0];
            }

            for(int j = 0; j < NUM_POPULACAO/2; j++){
                TabuleiroRainhas paiUm = selectParent();
                TabuleiroRainhas paiDois = selectParent();
                TabuleiroRainhas[] filhos = crossOver(paiUm, paiDois);
                novaPopulacao[j] = filhos[0];
                novaPopulacao[j + 50] = filhos[1];
            }
            for(int z = 0; z < NUM_POPULACAO; z++){
                double rand = Math.random();
                if(rand < 0.05){
                    novaPopulacao[z].changeRandValue();
                }
            }
            populacao = novaPopulacao;
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

        int rand = (int) (Math.random() * TAMANHO_TABULEIRO);
        while(rand == 0 || rand == TAMANHO_TABULEIRO){
            rand = (int) (Math.random() * TAMANHO_TABULEIRO);
        }
        int[] aVector = new int[TAMANHO_TABULEIRO];
        int[] bVector = new int[TAMANHO_TABULEIRO];

        for(int i = 0; i < TAMANHO_TABULEIRO; i++){
            if(i < rand) {
                aVector[i] = a.getPosicoes_rainhas()[i];
                bVector[i] = b.getPosicoes_rainhas()[i];
            } else {
                aVector[i] = b.getPosicoes_rainhas()[i];
                bVector[i] = a.getPosicoes_rainhas()[i];
            }
        }

        TabuleiroRainhas[] rainhas = new TabuleiroRainhas[2];
        rainhas[0] = new TabuleiroRainhas(aVector);
        rainhas[1] = new TabuleiroRainhas(bVector);

        return rainhas;

    }

}
