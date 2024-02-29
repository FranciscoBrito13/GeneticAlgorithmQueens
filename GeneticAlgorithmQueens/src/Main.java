public class Main {
    public static void main(String[] args) {
        AlgoritmoGenetico ag = new AlgoritmoGenetico();

        TabuleiroRainhas sol = ag.iniciaProcura();
        if (sol == null) {
            System.out.println("Nao foi encontrada nenhuma solucao");
        } else {
            System.out.println(sol);
        }
    }
}