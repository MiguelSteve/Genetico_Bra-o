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
    public static float[][] Custo_O;
    public static float[][] Custo_T;    
    
    //Variavel da Distancia dos Objetos
    public static float DistanciaObjeto = 3;
    

    public static float getMinO() {
        return minO;
    }

    public static float[][] getCusto_O() {
        return Custo_O;
    }

    public static void setCusto_O(float[][] Custo_O) {
        Calculos.Custo_O = Custo_O;
    }

    public static float[][] getCusto_T() {
        return Custo_T;
    }

    public static void setCusto_T(float[][] Custo_T) {
        Calculos.Custo_T = Custo_T;
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
            float[] linha = new float[qtd];

            for(int j=0; j<qtd; j++){
                if(i!=j){
                    linha[i] = random.nextFloat(min, max);
                    
                }
                else{
                    linha[i] = 0;
                }
            
            matriz[i][j] += linha[i];
            }
        }
     return matriz;
    }
    
    //Funcao para gerar a Solucao Inicial
    public static int[] SolucaoIncial(int qtd)
    {
        //Criando o vetor que armazenara a solucao
        int[] solucao = new int [qtd];
        
        //Instanciando a biblioteca de randomizacao
        Random random = new Random();
        
        //Laco para guardar os valores de 0 ao numero limite de problemas dentro do vetor
        for(int i=0; i<qtd; i++){
            solucao[i] = i;
        }
        
        //Embaralha os valores
        for(int i=solucao.length - 1; i>0; i--){
            int index = random.nextInt(i + 1);
            int temp = solucao[index];
            solucao[index] = solucao[i];
            solucao[i] = temp;
            
        }
        return solucao;
    }
    
    public static float Avalia(int[] solucao, int qtd, float[][] Custo_O, float[][] Custo_T){
        float avalia = 0;
        
        for(int i=0; i<qtd-1; i++){
            
                avalia += Custo_O[solucao[i]][solucao[i+1]] + Custo_T[solucao[qtd - 1]][solucao[0]];
        }
        return avalia;
        
    }

    
    }
    
  
