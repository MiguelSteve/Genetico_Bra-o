/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import funcoes.Calculos;
import java.util.Random;

/**
 *
 * @author Migue
 */
public class Menu extends javax.swing.JFrame {

    Calculos calculo = new Calculos();
    
    Random random = new Random();
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Campo_Tam_Prob = new javax.swing.JTextField();
        btn_GerarProblema = new javax.swing.JButton();
        btn_Solucao_Incial = new javax.swing.JButton();
        btn_Avalia_Problema = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Campo_Resultado = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Campo_Tam_Prob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Campo_Tam_ProbActionPerformed(evt);
            }
        });

        btn_GerarProblema.setText("Gerar Problema");
        btn_GerarProblema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GerarProblemaActionPerformed(evt);
            }
        });

        btn_Solucao_Incial.setText("Solucao Incial");
        btn_Solucao_Incial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Solucao_IncialActionPerformed(evt);
            }
        });

        btn_Avalia_Problema.setText("Avalia Problema");

        jLabel1.setText("Tamanho do problema");

        Campo_Resultado.setColumns(20);
        Campo_Resultado.setRows(5);
        jScrollPane1.setViewportView(Campo_Resultado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_GerarProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo_Tam_Prob, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Solucao_Incial, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Avalia_Problema))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Campo_Tam_Prob, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_GerarProblema)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Solucao_Incial)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Avalia_Problema))
                    .addComponent(jScrollPane1))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Campo_Tam_ProbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo_Tam_ProbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Campo_Tam_ProbActionPerformed

    private void btn_GerarProblemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GerarProblemaActionPerformed
        // TODO add your handling code here:
        
        //definindo valores da funcao GerarProblema
        int qtd = Integer.parseInt(Campo_Tam_Prob.getText());
        calculo.setTamanhoProblema(qtd);
        float minO = 5;
        float maxO = 10;
        float minT = 1;
        float maxT = 10;

        
        
        calculo.setMaxO(maxO);
        calculo.setMinO(minO);
        
        calculo.setMaxT(maxT);
        calculo.setMinT(minT);
        
        //Armazenando o resultado da funcao na matriz representando o angulo
        float[][][] angulo = Calculos.gerarProblema(qtd, minO, maxO, minT, maxT, random);
                        Campo_Resultado.setText("");

        //Imprimindo o resultado da matriz
        for(int i=0; i<angulo.length; i++){
            for(int j=0; j<angulo[i].length; j++){
                for(int h=0; h<angulo[i][j].length; h++){
                Campo_Resultado.append(String.format("%.2f\n", angulo[i][j][h]));
                System.out.println("" + String.format("%.2f", angulo[i][j][h]));
                }
            }
        }

    }//GEN-LAST:event_btn_GerarProblemaActionPerformed

    private void btn_Solucao_IncialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Solucao_IncialActionPerformed
        // TODO add your handling code here:
        int qtd = Integer.parseInt(Campo_Tam_Prob.getText());
        calculo.setTamanhoProblema(qtd);
        
        float[] solucao = calculo.SolucaoIncial(qtd);
        
        Campo_Resultado.setText("");

        for(int i=0; i<solucao.length; i++){
            Campo_Resultado.removeAll();
            Campo_Resultado.append(String.format("%.2f\n", solucao[i]));
        System.out.println(solucao[i]);
        }
    }//GEN-LAST:event_btn_Solucao_IncialActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Campo_Resultado;
    private javax.swing.JTextField Campo_Tam_Prob;
    private javax.swing.JButton btn_Avalia_Problema;
    private javax.swing.JButton btn_GerarProblema;
    private javax.swing.JButton btn_Solucao_Incial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
