package GUI;

import aplicacao.ACMETech;
import entidades.Comprador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaAlteraComprador {
    private JTextField textField1;
    private JButton confirmarButton;
    private JButton CancelarButton;
    private JPanel principal;
    private JPanel campoCod;
    private JPanel instrucao;
    private JPanel botoes;
    private JanelaPrincipal janelaPrincipal;
    private ACMETech acmeTech;

    public JanelaAlteraComprador(JanelaPrincipal janelaPrincipal, ACMETech acmeTech) {
        super();
        this.janelaPrincipal = janelaPrincipal;
        this.acmeTech = acmeTech;
        tratamentoEventos();
    }

    private void tratamentoEventos() {
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codTexto = textField1.getText().trim();

                    if (codTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "ERRO: Digite o código do comprador!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    long cod = Long.parseLong(codTexto);
                    Comprador comprador = acmeTech.getCentralCompradores().verificaCod(cod);

                    if (comprador == null) {
                        JOptionPane.showMessageDialog(null,
                                "ERRO: Comprador não encontrado!\nCódigo: " + cod,
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        textField1.setText("");
                    } else {
                        // Abrir dialog de alteração com os dados do comprador
                        PainelAlteraCompradorAux painelComp = new PainelAlteraCompradorAux(acmeTech, comprador);
                        painelComp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        painelComp.pack();
                        painelComp.setLocationRelativeTo(null);
                        painelComp.setVisible(true);

                        textField1.setText("");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "ERRO: O código deve ser numérico!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    textField1.setText("");
                }
            }
        });

        CancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaPrincipal.mudaTela(0);
            }
        });
    }

    public JPanel getPrincipal() {
        return principal;
    }
}