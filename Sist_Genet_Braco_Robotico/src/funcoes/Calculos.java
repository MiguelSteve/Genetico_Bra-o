package funcoes;

import java.util.ArrayList;
import java.util.List;
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
    
    public static float[] avaliarPopulacao(int[][] populacao, int tamanhoSolucao, float[][] Custo_O, float[][] Custo_T) {
    int tamanhoPopulacao = populacao.length;
    float[] avaliacoes = new float[tamanhoPopulacao];

    for (int i = 0; i < populacao.length; i++) {
        avaliacoes[i] = Avalia(populacao[i], tamanhoSolucao, Custo_O, Custo_T);
    }

    return avaliacoes;
}

    public static int[][] gerarPopulacaoInicial(int tamanhoPopulacao, int tamanhoSolucao) {
        int[][] populacao = new int[tamanhoPopulacao][tamanhoSolucao];

        for (int i = 0; i < tamanhoPopulacao; i++) {
            populacao[i] = SolucaoIncial(tamanhoSolucao);
        }

        return populacao;
    }

   
 // Método para selecionar os indivíduos com avaliação menor que um determinado limite
    public static List<int[]> selecionarIndividuos(int[][] pop, float[] avaliacoes, float limite) {
        List<int[]> individuosSelecionados = new ArrayList<>();
        for (int i = 0; i < pop.length; i++) {
            if (avaliacoes[i] < limite) {
                individuosSelecionados.add(pop[i]);
            }
        }
        return individuosSelecionados;
    }
    
    

 public static List<int[]> realizarCruzamento(List<int[]> individuosSelecionados, int tamanhoPopulacao, int tamanhoSolucao) {
        List<int[]> novaPopulacao = new ArrayList<>();
        Random random = new Random();

        // Realizar cruzamento até preencher a nova população
        while (novaPopulacao.size() < tamanhoPopulacao) {
            // Escolher dois indivíduos aleatórios para cruzamento
            int index1 = random.nextInt(individuosSelecionados.size());
            int index2 = random.nextInt(individuosSelecionados.size());

            // Garantir que os índices escolhidos sejam diferentes
            while (index2 == index1) {
                index2 = random.nextInt(individuosSelecionados.size());
            }

            // Realizar o cruzamento entre os indivíduos selecionados nos índices escolhidos
            int[] pai1 = individuosSelecionados.get(index1);
            int[] pai2 = individuosSelecionados.get(index2);

            // Ponto de cruzamento (crossover point)
            int pontoCruzamento = random.nextInt(tamanhoSolucao);

            // Criar o filho resultante do cruzamento
            int[] filho = new int[tamanhoSolucao];
            for (int i = 0; i < tamanhoSolucao; i++) {
                if (i < pontoCruzamento) {
                    filho[i] = pai1[i];
                } else {
                    filho[i] = pai2[i];
                }
            }

            // Adicionar o filho à nova população
            novaPopulacao.add(filho);
        }

        return novaPopulacao;
    }

    public void aplicarMutacao(int[][] populacao, float taxaMutacao) {
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
