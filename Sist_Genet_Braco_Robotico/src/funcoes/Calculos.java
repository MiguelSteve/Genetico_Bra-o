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
 public static float[][][] gerarProblema(int qtd, float minO, float maxO, float minT, float maxT, Random random) {
        float[][] anguloO = new float[qtd][qtd];
        float[][] anguloT = new float[qtd][qtd];

        for (int h = 0; h < qtd; h++) {
            for (int v = 0; v < qtd; v++) {
                if (h != v) {
                    anguloO[h][v] = minO + random.nextFloat() * (maxO - minO);
                    anguloT[h][v] = minT + random.nextFloat() * (maxT - minT);
                } else {
                    anguloO[h][v] = 0;
                    anguloT[h][v] = 0;
                }
            }
        }
        return new float[][][]{anguloO, anguloT};
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
    
    public static float Avalia(float[] solucao, int qtd, float[][] anguloO, float[][] anguloT){
        
                float CustoTotal = 0;

        int[] pesoA = new int [5];
        int valorPesoA = 1;
        int[] pesoB = {1, 3, 10};
        
        int[] tempo = new int [255];
        int valorTempo = 1;
        
        int NumAgentes = pesoA.length;
        int CustoTotalAgente;
        
        //Preenche os valores do Peso A deacordo com a tabela do artigo
        for(int i=0; i<5; i++){
            if(valorPesoA == 3){
                valorPesoA++;
            }
            pesoA[i] = valorPesoA;
            valorPesoA++;
            
            //Imprimindo valores para verificar se esta correto
            System.out.println(pesoA[i]);
        }
        
        for(int i=0; i<255; i++){
            tempo[i] = valorTempo;
            valorTempo++;
        }
        
        //Valor Incial do Custo dos Agentes
        int Custo = 0;

        
        for(int i=0; i<pesoA.length; i++){
            for(int j=0; j<pesoB.length; j++){
                for(int t=0; t<tempo.length; t++){
                    
                    Custo += CalculoAgente(pesoA[i], pesoB[j], tempo[t]);
                    
                }
            }
                        
        }
        
        System.out.println(Custo);
        
        CustoTotal += Avaliar(Custo, D, DistanciaObjeto);
        
        System.out.println(CustoTotal);
        
        

        return CustoTotal;
        
     
    }
    
    //Calculo de custo de cada Agente
    private static int CalculoAgente(int id, int acao, int tempo){
        return id * acao * tempo;
    }
    
    
    //Calculo para Avaliar
    private static float Avaliar(int custo, float D, float DistanciaObjeto){
        
        float avaliacao = custo/5 + D * DistanciaObjeto;
        
        return avaliacao;
    }

    
    }
    
  
