package GUI;

import aplicacao.ACMETech;
import entidades.Comprador;
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
        super();
        setContentPane(contentPane);
        setModal(true);
        this.acmeTech = acmeTech;
        tratamentoDeEventos();
    }

    public void tratamentoDeEventos(){
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = campoDigitaNome.getText();
                    String email = campoDigitaEmail.getText();
                    String codTexto = campoDigitaCod.getText();
                    String pais = campoDigitaPais.getText(); // Agora é país mesmo

                    if (nome.isEmpty() || email.isEmpty() || codTexto.isEmpty() || pais.isEmpty()) {
                        areaMensagens.setText("ERRO: Preencha todos os campos!");
                    } else {
                        long codLong = Long.parseLong(codTexto);

                        if (acmeTech.getCentralCompradores().verificaCod(codLong) != null) {
                            areaMensagens.setText("ERRO: código já existente!");
                            campoDigitaCod.setText("");
                        } else {

                            if (!email.contains("@")) {
                                areaMensagens.setText("ERRO: Email inválido!");
                                return;
                            }

                            Comprador comprador = new Comprador(codLong, nome, pais, email);
                            if (acmeTech.getCentralCompradores().cadastraComprador(comprador)) {
                                areaMensagens.setText("Comprador cadastrado com sucesso!");
                                // Limpar campos
                                campoDigitaNome.setText("");
                                campoDigitaEmail.setText("");
                                campoDigitaCod.setText("");
                                campoDigitaPais.setText("");
                            }
                        }
                    }
                } catch (NumberFormatException ex) {
                    areaMensagens.setText("Erro: código apenas com valores numéricos!");
                } catch (Exception ex) {
                    areaMensagens.setText("Erro: algo deu errado! " + ex.getMessage());
                }
            }
        });
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
