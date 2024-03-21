/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcoes;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 *
 * @author Migue
 */
public class Calculos {
    public static int tamanhoProblema;
    public static float minO, maxO, maxT, minT;
    public static float[] Angulo1;
    public static float[] Angulo2;
    //Variavel de normalizacao entre os componentes (Obtido empiricamente)
    public static float D = (float) 4.17;
    
    
    //Variavel da Distancia dos Objetos
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
    

    
    
    //Funcao para  Gerar o Problema
    //MinO = minimo objetos, MinT = minimo tempo
    public static float[][] gerarProblema(int qtd, float min, float max, Random random) {
        
        float[][] matriz = new float[qtd][qtd];
        
        for(int i=0; i<qtd; i++){
            for(int j=0; j<qtd; j++){
                if(i!=j){
                    matriz[i][j] = random.nextFloat(max - min + 1) + min;
                }
                else{
                    matriz[i][j] = 0;
                }
            }
        }
     return matriz;
    }
    
    //Funcao para gerar a Solucao Inicial
    public static float[] SolucaoIncial(int qtd)
    {
        //Criando o vetor que armazenara a solucao
        float[] solucao = new float [qtd];
        
        //Instanciando a biblioteca de randomizacao
        Random random = new Random();
        
        //Laco para guardar os valores de 0 ao numero limite de problemas dentro do vetor
        for(int i=0; i<qtd; i++){
            solucao[i] = i+1;
        }
        
        //Embaralha os valores
        for(int i=solucao.length - 1; i>0; i--){
            int index = random.nextInt(i + 1);
            float temp = solucao[index];
            solucao[index] = solucao[i];
            solucao[i] = temp;
            
        }
        return solucao;
    }
    
    public static float Avalia(float[] solucao, int qtd, float[][] Angulo1, float[][] Angulo2){
        
        float CustoTotal = 0;

        int[] pesoA = new int [5];
        int valorPesoA = 1;
        int[] pesoB = {1, 3, 10};
        
        int[] tempo = new int [255];
        int valorTempo = 1;
        
        int CustoTotalAgente = 0;
        
        float[] sol = SolucaoIncial(qtd);
        
        
        //Preenche os valores do Peso A deacordo com a tabela do artigo
        for(int i=0; i<5; i++){
            if(valorPesoA == 3){
                valorPesoA++;
            }
            pesoA[i] = valorPesoA;
            valorPesoA++;
           
        }
        
        for(int i=0; i<255; i++){
            tempo[i] = valorTempo;
            valorTempo++;
        }
        
        //Valor Incial do Custo dos Agentes
        int Custo = 0;

        //Laco para calcular o custo de Cada Agente de 1 a 255 segundos
        for(int i=0; i<pesoA.length; i++){
            for(int j=0; j<pesoB.length; j++){
                for(int t=0; t<tempo.length; t++){
                    
                    Custo += CalculoAgente(pesoA[i], pesoB[j], tempo[t]);
                    
                }
            }           
        }

        
        //Laco para Calcular a soma do Custo de todos os Agentes com base na Solucao Inicial 
        for(int i=0; i<sol.length; i++){
            
            CustoTotalAgente += calcularCustoAgente((int)sol[i], pesoB, tempo);
        }
        
        System.out.println(CustoTotalAgente);
        
        System.out.println(Custo);
        
        
        //Aplica a Avaliacao do problema
        CustoTotal += Avaliar(CustoTotalAgente, D, DistanciaObjeto);
        
        System.out.println(CustoTotal);
        
        

        return CustoTotal;
        
     
    }
    
    //Calculo de custo de cada Agente
    private static int CalculoAgente(int id, int acao, int tempo){
        return id * acao * tempo;
    }
    
 public static int calcularCustoAgente(int id, int[] acoes, int[] tempos) {
        int custoTotal = 0;
        for (int acao : acoes) {
            for (int tempo : tempos) {
                custoTotal += CalculoAgente(id, acao, tempo);
            }
        }
        return custoTotal;
    }
    
    //Calculo para Avaliar
    private static float Avaliar(int custo, float D, float DistanciaObjeto){
        
        
        float avaliacao = custo/5 + D * DistanciaObjeto;
        
        return avaliacao;
    }

    
    }
    
  
