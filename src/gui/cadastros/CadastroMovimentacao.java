package gui.cadastros;

import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import gui.Navegacao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.Movimentacao;
import modelo.Produto;
import utils.Sessao;

public class CadastroMovimentacao extends javax.swing.JFrame {

    private Map<Integer, String> mapProdutos = new HashMap<>();

    public CadastroMovimentacao() {
        initComponents();
        LoadProdutos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnRegistrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLimpar = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        fieldQtde = new javax.swing.JFormattedTextField();
        cbProdutos = new javax.swing.JComboBox<>();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnRegistrar.setText("Registrar Movimentação");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo de movimentação:");

        jLabel2.setText("Produto:");

        jLabel3.setText("Quantidade:");

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Entrada");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Saida");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Registrar Movimentação");

        fieldQtde.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        fieldQtde.setMinimumSize(new java.awt.Dimension(286, 25));
        fieldQtde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldQtdeActionPerformed(evt);
            }
        });

        cbProdutos.setMinimumSize(new java.awt.Dimension(286, 25));

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLimpar)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(97, 97, 97)
                                .addComponent(btnVoltar))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jRadioButton2))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fieldQtde, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btnVoltar))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fieldQtde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnLimpar))
                .addGap(28, 28, 28))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LoadProdutos() {
        cbProdutos.removeAllItems();
        mapProdutos.clear();

        cbProdutos.addItem("Selecione");

        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> produtos = dao.listar();

        for (Produto p : produtos) {
            mapProdutos.put(p.getIdProduto(), p.getNome());
            cbProdutos.addItem(p.getNome());

        }
    }


    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        if (!(jRadioButton1.isSelected()) && !(jRadioButton2.isSelected())) {
            JOptionPane.showMessageDialog(rootPane, "Por favor escolha um tipo");
            return;
        }
        if (fieldQtde.getText().isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Os campos não podem estar vazios");
            return;
        }
        if (cbProdutos.getSelectedItem() == "Selecione" || cbProdutos.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione um produto válido");
            return;
        }

        try {
            String tipo = (jRadioButton1.isSelected()) ? "ENTRADA" : "SAIDA";

            String produtoSel = (String) cbProdutos.getSelectedItem();

            int idProduto = -1;
            for (Map.Entry<Integer, String> entry : mapProdutos.entrySet()) {
                if (entry.getValue().equals(produtoSel)) {
                    idProduto = entry.getKey();
                    break;
                }
            }
            if (idProduto == -1) {
                JOptionPane.showMessageDialog(null, "Erro ao identificar produto.");
                return;
            }


            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setTipo(tipo);
            movimentacao.setQuantidade(Integer.parseInt(fieldQtde.getText()));
            movimentacao.setUsuarioResponsavel(Sessao.getEmail());
            movimentacao.setIdProduto(idProduto);
            
            MovimentacaoDAO dao = new MovimentacaoDAO();
            dao.inserir(movimentacao);
            
            btnLimparActionPerformed(evt);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro em inserir na base de dados:\n" + e, "Erro", JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
        cbProdutos.setSelectedItem("Selecione");
        fieldQtde.setText("");
    }//GEN-LAST:event_btnLimparActionPerformed

    private void fieldQtdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldQtdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldQtdeActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        Navegacao n = new Navegacao();
        n.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbProdutos;
    private javax.swing.JFormattedTextField fieldQtde;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration//GEN-END:variables
}
