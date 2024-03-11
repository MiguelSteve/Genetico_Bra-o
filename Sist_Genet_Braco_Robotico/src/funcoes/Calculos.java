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
            solucao[i] = i;
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
        
        
        for(int i=0; i<qtd-1; i++){
            int AnguloAtual = (int) solucao[i];
            int ProximoAngulo = (int) solucao[i+1];
            
            CustoTotal += (AnguloAtual * anguloO[AnguloAtual][ProximoAngulo] * anguloT[AnguloAtual][ProximoAngulo]);
        }
        return CustoTotal;
    }
    
 

    
    }
    
  
