package funcoes;

import java.util.Random;

public class Calculos {
    public static int tamanhoProblema;
    public static float minO, maxO, maxT, minT;
    public static float DistanciaObjeto = 3;

    public static float getMinO() {
        return minO;
    }

    public static void setMinO(float minO) {
        Calculos.minO = minO;
    }

    public static float getMaxO() {
        return maxO;
    }

    public static void setMaxO(float maxO) {
        Calculos.maxO = maxO;
    }

    public static float getMaxT() {
        return maxT;
    }

    public static void setMaxT(float maxT) {
        Calculos.maxT = maxT;
    }

    public static float getMinT() {
        return minT;
    }

    public static void setMinT(float minT) {
        Calculos.minT = minT;
    }

    public static int getTamanhoProblema() {
        return tamanhoProblema;
    }

    public static void setTamanhoProblema(int tamanhoProblema) {
        Calculos.tamanhoProblema = tamanhoProblema;
    }

    // Função para gerar o problema
    public static float[][] gerarProblema(int qtd, float min, float max, Random random) {
        float[][] matriz = new float[qtd][qtd];

        for (int i = 0; i < qtd; i++) {
            float[] linha = new float[qtd];

            for (int j = 0; j < qtd; j++) {
                if (i != j) {
                    linha[j] = min + (max - min) * random.nextFloat();
                } else {
                    linha[j] = 0;
                }

                matriz[i][j] = linha[j];
            }
        }
        return matriz;
    }

    // Função para gerar a solução inicial
    public static int[] SolucaoIncial(int qtd) {
        int[] solucao = new int[qtd];
        Random random = new Random();

        for (int i = 0; i < qtd; i++) {
            solucao[i] = i;
        }

        for (int i = solucao.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = solucao[index];
            solucao[index] = solucao[i];
            solucao[i] = temp;
        }
        return solucao;
    }

public static float Custo(int[] solucao, int qtd, float[][] Custo_O, float[][] Custo_T) {
    float custo = 0;
    int tempo = 32;

    for (int i = 0; i < qtd - 1; i++) {
        int pontoAtual = solucao[i];
        int proximoPonto = solucao[i + 1];

        // Verifica se os índices estão dentro dos limites válidos para evitar erros
        if (pontoAtual >= 0 && pontoAtual < Custo_O.length &&
            proximoPonto >= 0 && proximoPonto < Custo_O.length) {
            custo += (Custo_O[pontoAtual][proximoPonto] * (Custo_T[pontoAtual][proximoPonto] * tempo));
        } else {
            // Caso algum índice esteja fora dos limites, tratar de forma adequada
            throw new IllegalArgumentException("Índice inválido em solução.");
        }
    }

    return custo;
}


   public static double PrecisaoPosicionamento(int[] solucao, float[][] Custo_O) {
    double precisao = 0;

    for (int i = 0; i < solucao.length - 1; i++) {
        int pontoAtual = solucao[i];
        int proximoPonto = solucao[i + 1];

        if (pontoAtual >= 0 && pontoAtual < Custo_O.length && proximoPonto >= 0 && proximoPonto < Custo_O.length) {
            precisao += Custo_O[pontoAtual][proximoPonto];
        } else {
            throw new IllegalArgumentException("Índice inválido em solução.");
        }
    }

    return precisao;
}

    public static float Avalia(int[] solucao, int qtd, float[][] Custo_O, float[][] Custo_T) {
        float avalia = 0;
        int D = 3;
        double Distancia = 7.5;

        float custo = Custo(solucao, qtd, Custo_O, Custo_T);
        double precisao = PrecisaoPosicionamento(solucao, Custo_O);

        if (precisao <= 3) {
            avalia = (float) (custo / 5 + D * Distancia);
        } else {
            float diferencaPrecisao = (float) (precisao - 3);
            avalia = (float) (custo / 5 + D * Distancia + diferencaPrecisao);
        }

        return avalia;
    }

    public static int[][] gerarPopulacaoInicial(int tamanhoPopulacao, int tamanhoSolucao) {
        int[][] populacao = new int[tamanhoPopulacao][tamanhoSolucao];

        for (int i = 0; i < tamanhoPopulacao; i++) {
            populacao[i] = SolucaoIncial(tamanhoSolucao);
        }

        return populacao;
    }

    public static void Mutacao(int[][] populacao, float deltaT) {
        Random random = new Random();
        int individuoIndex = random.nextInt(populacao.length);
        int acaoIndex = random.nextInt(populacao[individuoIndex].length);
        populacao[individuoIndex][acaoIndex] += deltaT;
    }

   public static int[][] executarAlgoritmoGenetico(int tamanhoPopulacao, int tamanhoSolucao,
                                                float[][] Custo_O, float[][] Custo_T, int numeroGeracoes,
                                                float taxaCruzamento, float taxaMutacao) {
    Random random = new Random();

    // 1. Gerar população inicial
    int[][] populacao = gerarPopulacaoInicial(tamanhoPopulacao, tamanhoSolucao);

    // Executar o algoritmo por um número específico de gerações
    for (int geracao = 0; geracao < numeroGeracoes; geracao++) {

        // 2. Selecionar o melhor indivíduo da população
        int[] melhorIndividuoIndex = selecionarMelhoresIndividuos(populacao, tamanhoSolucao, Custo_O, Custo_T);

        // 3. Realizar o cruzamento (crossover)
        int[][] novaPopulacao = realizarCruzamento(populacao, melhorIndividuoIndex, taxaCruzamento);

        // 4. Aplicar mutação na nova população
        aplicarMutacao(novaPopulacao, taxaMutacao);

        // Atualizar a população para a próxima geração
        populacao = novaPopulacao;
    }

    return populacao;
}


   public static int[] selecionarMelhoresIndividuos(int[][] populacao, int qtd, float[][] Custo_O, float[][] Custo_T) {
    int[] melhoresIndividuos = new int[1]; // Ajuste para tamanho 1
    float menorCusto = Float.POSITIVE_INFINITY;

    for (int i = 0; i < populacao.length; i++) {
        int[] solucao = populacao[i];
        float custo = Custo(solucao, qtd, Custo_O, Custo_T);
        double precisao = PrecisaoPosicionamento(solucao, Custo_O);

        if (precisao <= 3 && custo < menorCusto) {
            menorCusto = custo;
            melhoresIndividuos[0] = i; // Ajuste aqui para armazenar apenas um índice
        }
    }

    return melhoresIndividuos;
}


public static int[][] realizarCruzamento(int[][] populacao, int[] melhoresIndividuos, float taxaCruzamento) {
        Random random = new Random();
        int[][] novaPopulacao = new int[populacao.length][populacao[0].length];

        int numFilhos = (int) (populacao.length * taxaCruzamento);

        for (int i = 0; i < numFilhos; i++) {
            int pai1Index = melhoresIndividuos[random.nextInt(melhoresIndividuos.length)];
            int pai2Index = melhoresIndividuos[random.nextInt(melhoresIndividuos.length)];

            int[] pai1 = populacao[pai1Index];
            int[] pai2 = populacao[pai2Index];
            int[] filho = new int[pai1.length];

            int pontoCorte = random.nextInt(pai1.length);

            for (int j = 0; j < pai1.length; j++) {
                filho[j] = (j <= pontoCorte) ? pai1[j] : pai2[j];
            }

            novaPopulacao[i] = filho;
        }

        for (int i = numFilhos; i < populacao.length; i++) {
            novaPopulacao[i] = populacao[i];
        }

        return novaPopulacao;
    }

    public static void aplicarMutacao(int[][] populacao, float taxaMutacao) {
    Random random = new Random();

    for (int i = 0; i < populacao.length; i++) {
        if (random.nextFloat() < taxaMutacao) {
            for (int geneIndex = 0; geneIndex < populacao[i].length; geneIndex++) {
                populacao[i][geneIndex] += 0.5f * populacao[i][geneIndex];
            }
        }
    }
}

}
