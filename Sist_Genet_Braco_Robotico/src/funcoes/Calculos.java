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
    public static int minimo, maximo;
    

    public static int getTamanhoProblema() {
        return tamanhoProblema;
    }

    public static void setTamanhoProblema(int tamanhoProblema) {
        Calculos.tamanhoProblema = tamanhoProblema;
    }
    

    public static int getMinimo() {
        return minimo;
    }

    public static void setMinimo(int minimo) {
        Calculos.minimo = minimo;
    }

    public static int getMaximo() {
        return maximo;
    }

    public static void setMaximo(int maximo) {
        Calculos.maximo = maximo;
    }
    
    
    //Funcao para  Gerar o Problema
    public static float[][] gerarProblema(int qtd, int min, int max, Random random){
        //Criando a matriz do angulo que o braco percorrera
        float [][] angulo = new float[qtd][qtd];
        
        //Lacos para percorrer cada posicao na matriz
        for(int h=0; h<qtd; h++){
            for(int v=0; v<qtd; v++){
                
                //Condicao para conferir se o braco esta fora da diagonal principal
                if(h!=v){
                    //caso seja verdadeiro, adiciona os numeros aleatorios dentro da matriz do angulo
                    angulo[h][v] = random.nextFloat(max - min + 1) + 1;
                }
                else{
                    //caso o braco esteja na diagonal principal o angulo e definido como 0 
                    angulo[h][v] = 0;
                }
            }
        }
     return angulo;
     
   
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
        for(int i=0; i<qtd; i++){
            float index = (float) Math.random();
            int randomIndex = (int) (index * qtd);
            float temp = solucao[i];
            solucao[i] = solucao[randomIndex];
            solucao[randomIndex] = temp;
        }
        return solucao;
    }

    
    }
    
  
