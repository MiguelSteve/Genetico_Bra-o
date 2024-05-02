/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcoes;
import java.beans.Customizer;
import java.text.ParseException;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Migue
 */
public class Calculos {
    public static int tamanhoProblema;
    public static float minO, maxO, maxT, minT;    
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
            
                avalia += (Custo_O[solucao[i]][solucao[i+1]] * Custo_T[solucao[i]][solucao[i+1]]);
        }
        avalia += (Custo_O[solucao[qtd - 1]][0] * Custo_T[solucao[qtd - 1]][solucao[0]]);
        return avalia;
        
    }
    
    public static int[] Sucessores_Sol_Melhor(int[] atual, float Va, int qtd, float[][] Custo_O, float[][] Custo_T, int indice){
        int [] melhor = atual.clone();
        float Vm = Va;
        int[] sucessor ;
        
        for(int i=0; i<qtd; i++){
            sucessor = atual.clone();
            int aux = sucessor[i];
            sucessor[i] = sucessor[indice];
            sucessor[indice] = aux;
            float Vs = Avalia(sucessor, qtd, Custo_O, Custo_T);
            
            if(Vs < Vm){
                melhor = sucessor.clone();
                Vm = Vs;
            }
            
        }
        return melhor;
    }
    
    public static float Sucessores_Vm(int[] atual, float Va, int qtd, float[][] Custo_O, float[][] Custo_T, int indice){
        int [] melhor = atual.clone();
        float Vm = Va;
        int[] sucessor;
        
        for(int i=0; i<qtd; i++){
            sucessor = atual.clone();
            int aux = sucessor[i];
            sucessor[i] = sucessor[indice];
            sucessor[indice] = aux;
            float Vs = Avalia(sucessor, qtd, Custo_O, Custo_T);
            
            if(Vs < Vm){
                melhor = sucessor.clone();
                Vm = Vs;
            }
            
        }
        return Vm;
    }
    
    public static int[] Subida_Encosta(int[] sol_ini, float Vi, int qtd, float[][] Custo_O, float[][] Custo_T){
        int [] atual = sol_ini.clone();
        float Va = Vi;
       ArrayList<Integer> cid = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            cid.add(i);
        }
        Collections.shuffle(cid);
        
        while(true){
            int ind = cid.remove(cid.size() - 1);
            
            int[] novo = Sucessores_Sol_Melhor(atual, Va, qtd, Custo_O, Custo_T, ind);
            
            float Vn = Sucessores_Vm(atual, Va, qtd, Custo_O, Custo_T, ind);
            
            if(Vn >= Va){
                return atual;
            }
            
            atual = novo.clone();
            Va = Vn;
            
            cid.clear();
            
            for(int i=0; i<qtd; i++){
                cid.add(i);
            }
            Collections.shuffle(cid);
        }
    }
    
     public static float Subida_Encosta_Vm(int[] sol_ini, float Vi, int qtd, float[][] Custo_O, float[][] Custo_T){
        int [] atual = sol_ini.clone();
        float Va = Vi;
       ArrayList<Integer> cid = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            cid.add(i);
        }
        Collections.shuffle(cid);
        
        while(true){
            int ind = cid.remove(cid.size() - 1);
            
            int[] novo = Sucessores_Sol_Melhor(atual, Va, qtd, Custo_O, Custo_T, ind);
            
            float Vn = Sucessores_Vm(atual, Va, qtd, Custo_O, Custo_T, ind);
            
            if(Vn >= Va){
                return Va;
            }
            
            atual = novo.clone();
            Va = Vn;
            
            cid.clear();
            
            for(int i=0; i<qtd; i++){
                cid.add(i);
            }
            Collections.shuffle(cid);
        }
    }
    

     public static int[] Subida_Encosta_Alt(int[] sol_ini, float Vi, int qtd, float[][] Custo_O, float[][] Custo_T, int t_max){
        int [] atual = sol_ini.clone();
        float Va = Vi;
        int t = 0;
       ArrayList<Integer> cid = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            cid.add(i);
        }
        Collections.shuffle(cid);
        
        while(true){
            int ind = cid.remove(cid.size() - 1);
            
            int[] novo = Sucessores_Sol_Melhor(atual, Va, qtd, Custo_O, Custo_T, ind);
            
            float Vn = Sucessores_Vm(atual, Va, qtd, Custo_O, Custo_T, ind);
            
            if(Vn >= Va){
                if(t>=t_max){
                 return atual;   
                }
                else{
                    t += 1;
                }
            }
            
            else{
                
            atual = novo.clone();
            Va = Vn;
            t = 0;
            cid.clear();
            
            for(int i=0; i<qtd; i++){
                cid.add(i);
            }
            
            Collections.shuffle(cid);
            
            }
        }
        
        
    }
     
     public static float Subida_Encosta_Alt_Vm(int[] sol_ini, float Vi, int qtd, float[][] Custo_O, float[][] Custo_T, int t_max){
        int [] atual = sol_ini.clone();
        float Va = Vi;
        int t = 0;
       ArrayList<Integer> cid = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            cid.add(i);
        }
        Collections.shuffle(cid);
        
        while(true){
            int ind = cid.remove(cid.size() - 1);
            
            int[] novo = Sucessores_Sol_Melhor(atual, Va, qtd, Custo_O, Custo_T, ind);
            
            float Vn = Sucessores_Vm(atual, Va, qtd, Custo_O, Custo_T, ind);
            
            if(Vn >= Va){
                if(t>=t_max){
                 return Va;   
                }
                else{
                    t += 1;
                }
            }
            
            else{
                
            atual = novo.clone();
            Va = Vn;
            t = 0;
            cid.clear();
            
            for(int i=0; i<qtd; i++){
                cid.add(i);
            }
            
            Collections.shuffle(cid);
            
            }
        }
    }
     
     public static int[] Tempera_Simulada_Resp(float temp_ini, float temp_fin, float fat_red, int[] Si, float Vi, int qtd, float[][] Custo_O, float[][] Custo_T, int ind){
         int[] atual = Si.clone();
         int[] resp = Si.clone();
         float Va = Vi; 
         float Vr = Va;
         
         float temp = temp_ini;
         
         while(temp>=temp_fin){
             
             int[] novo = Sucessores_Sol_Melhor(atual, Va, qtd, Custo_O, Custo_T, ind);
             float Vn = Sucessores_Vm(atual, Va, qtd, Custo_O, Custo_T, ind);
             float De = Va - Vn;
             
             if(De < 0){
                 atual = novo.clone();
                 resp = novo.clone();
                 Vr = Va = Vn;
             }
             
             else{
             Random rd = new Random();
             double ale = rd.nextDouble();
             double aux = Math.exp(-De / temp);
             
             if(aux > ale){
                 atual = novo.clone();
                 Va = Vn;
             }
         }
         temp = temp * fat_red;
     }
         return resp;
}
     
     public static float Tempera_Simulada_Vr(float temp_ini, float temp_fin, float fat_red, int[] Si, float Vi, int qtd, float[][] Custo_O, float[][] Custo_T, int ind){
         int[] atual = Si.clone();
         int[] resp = Si.clone();
         
         float Va = Vi; 
         float Vr = Va;
         
         float temp = temp_ini;
         
         while(temp>=temp_fin){
             
             int[] novo = Sucessores_Sol_Melhor(atual, Va, qtd, Custo_O, Custo_T, ind);
             float Vn = Sucessores_Vm(atual, Va, qtd, Custo_O, Custo_T, ind);
             float De = Va - Vn;
             
             if(De < 0){
                 atual = novo.clone();
                 resp = novo.clone();
                 Vr = Va = Vn;
             }
             
             else{
             Random rd = new Random();
             double ale = Math.random();
             double aux = Math.exp(-De / temp);
             
             if(aux > ale){
                 atual = novo.clone();
                 Va = Vn;
             }
         }
         temp = temp * fat_red;
     }
         return Vr;
}
}
    
  
