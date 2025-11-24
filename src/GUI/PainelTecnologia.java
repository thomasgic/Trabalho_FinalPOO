package GUI;

import aplicacao.ACMETech;
import entidades.Fornecedor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PainelTecnologia extends JDialog {
    private JPanel principal;
    private JButton mostrarButton;
    private JButton limparButton;
    private JButton cancelarButton;
    private JTextField campoDigitaModelo;
    private JTextField campoDigitaID;
    private JTextField campoDigitaDescricao;
    private JTextField campoDigitaValorBase;
    private JTextField campoDigitaPeso;
    private JTextArea areaMensagens;
    private JPanel CampoDigitDados;
    private JLabel instrucaoID;
    private JPanel painelBotoes;
    private JButton confirmarButton;
    private JLabel instrucaoModelo;
    private JLabel instrucaoDescricao;
    private JLabel instrucaoValorBase;
    private JLabel instrucaoPeso;
    private JLabel InstrucaoTemp;
    private JTextField CampoDigitaTemp;
    private ACMETech acmeTech;


    public PainelTecnologia (ACMETech acmeTech) {
        setContentPane(principal);
        setModal(true);
        this.acmeTech = acmeTech;
        tratamentoDeEventos();
    }

    public void tratamentoDeEventos(){
    limparButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e) {
            areaMensagens.setText("");
            campoDigitaID.setText("");
            campoDigitaDescricao.setText("");
            campoDigitaModelo.setText("");
            campoDigitaPeso.setText("");
            campoDigitaValorBase.setText("");
        }
    });

        mostrarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e) {
            areaMensagens.setText("");
            ArrayList<Fornecedor> fornecedores = acmeTech.getCentralFornecimento().mostraFornecedores();
            if (fornecedores.isEmpty()) {
                areaMensagens.setText("não existem fornecedores cadastrados");
            } else {
                for (Fornecedor f : fornecedores) {
                    areaMensagens.append(f.geraDescricao() + "\n");
                }
            }
        }
    });

        cancelarButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e) {
            dispose();
        }
    });

}
public JPanel getPainel(){
    return principal;
}
}
