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
            public void actionPerformed(ActionEvent e) {
                try{
                    String nome = campoDigitaNome.getText();
                    String email = campoDigitaEmail.getText();
                    String codTexto = campoDigitaCod.getText();
                    String cpf = campoDigitaPais.getText();

                    if(nome.isEmpty() || email.isEmpty() || codTexto.isEmpty() || cpf.isEmpty()){
                        areaMensagens.setText("ERRO: Preencha os campos vazios");
                    }else{
                        long codLong = Long.parseLong(codTexto);

                        if(acmeTech.getCentralCompradores().verificaCod(codLong) != null){
                            areaMensagens.setText("ERRO: código já existente");
                            campoDigitaCod.setText("");
                        }else{
                            String cpfDigitos = cpf.replaceAll("[^0-9]", "");
                            if(cpfDigitos.length() != 11){
                                throw new IllegalArgumentException("CPF deve ser 11 digitos");
                            }
                            if(!email.contains("@") || !email.toLowerCase().contains(".com")) {
                                throw new IllegalArgumentException("Email invalido!");
                            }

                                Comprador comprador = new Comprador(codLong, nome, cpfDigitos, email);
                                if(acmeTech.getCentralCompradores().cadastraComprador(comprador)){
                                areaMensagens.setText("Comprador cadastrado com sucesso!");

                                campoDigitaEmail.setText("");
                                campoDigitaEmail.setText("");
                                campoDigitaCod.setText("");
                                campoDigitaPais.setText("");

                            }
                        }
                    }

                }catch (NumberFormatException ex){
                    areaMensagens.setText("ERRO: código apenas com valores numéricos!");
                }catch (IllegalArgumentException ex){
                    areaMensagens.setText("ERRO: " +ex.getMessage());
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
