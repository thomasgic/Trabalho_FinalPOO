package GUI;

import aplicacao.ACMETech;
import entidades.Fornecedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PainelComprador extends JDialog {
    private JPanel contentPane;
    private JPanel CampoDigitDados;
    private JPanel painelBotoes;
    private JLabel instrucaoNome;
    private JLabel instrucaoCod;
    private JLabel instrucaoEmail;
    private JLabel instrucaoPais;
    private JButton confirmarButton;
    private JButton mostrarButton;
    private JButton limparButton;
    private JButton cancelarButton;
    private JTextField campoDigitaCod;
    private JTextField campoDigitaNome;
    private JTextField campoDigitaEmail;
    private JTextField campoDigitaPais;
    private JTextArea areaMensagens;
    private ACMETech acmeTech;

    public PainelComprador (ACMETech acmeTech) {
        setContentPane(contentPane);
        setModal(true);
        this.acmeTech = acmeTech;
        tratamentoDeEventos();
    }

    public void tratamentoDeEventos(){
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                areaMensagens.setText("");
                campoDigitaCod.setText("");
                campoDigitaNome.setText("");
                campoDigitaEmail.setText("");
                campoDigitaPais.setText("");
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
}
