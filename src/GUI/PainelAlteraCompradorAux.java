package GUI;

import aplicacao.ACMETech;
import entidades.Comprador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelAlteraCompradorAux extends JDialog {
    private JPanel principal;
    private JPanel instrucoes;
    private JLabel instrucaoLabel;
    private JPanel dados;
    private JPanel botoes;
    private JTextField textField1; // Nome
    private JTextField textField2; // CPF
    private JTextField textField3; // Email
    private JButton confirmaButton;
    private JButton limpaButton;
    private JTextArea MensagensTA;
    private JPanel mensagens;
    private JLabel mensagensLabel;
    private JLabel nomeLabel;
    private JLabel paisLabel;
    private JLabel emailLabel;
    private ACMETech acmeTech;
    private Comprador comprador;

    public PainelAlteraCompradorAux(ACMETech acmeTech, Comprador comprador) {
        super();
        this.acmeTech = acmeTech;
        this.comprador = comprador;
        setContentPane(principal);
        setModal(true);

        // Preencher campos com dados atuais
        preencherCampos();
        tratamentoEventos();
    }

    private void preencherCampos() {
        textField1.setText(comprador.getNome());
        textField2.setText(comprador.getPais());
        textField3.setText(comprador.getEmail());
        MensagensTA.setText("Dados atuais do comprador:\n" + comprador.geraDescricao());
        instrucaoLabel.setText("insira os novos dados do comprador (" + comprador.getNome() + ")");
    }

    private void tratamentoEventos() {
        confirmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = textField1.getText().trim();
                    String pais = textField2.getText().trim();
                    String email = textField3.getText().trim();

                    if (nome.isEmpty() || pais.isEmpty() || email.isEmpty()) {
                        MensagensTA.setText("ERRO: Todos os campos devem ser preenchidos!");
                        return;
                    }

                    // Validar email
                    if (!email.contains("@") || !email.toLowerCase().contains(".com")) {
                        MensagensTA.setText("ERRO: Email inválido!\nDeve conter @ e .com");
                        return;
                    }

                    // Atualizar dados do comprador
                    comprador.setNome(nome);
                    comprador.setPais(pais);
                    comprador.setEmail(email);

                    MensagensTA.setText("Comprador alterado com sucesso!\n\n" +
                            "Novos dados:\n" + comprador.geraDescricao());

                    // Confirmar e fechar após 2 segundos
                    Timer timer = new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();

                } catch (Exception ex) {
                    MensagensTA.setText("ERRO: Algo deu errado!\n" + ex.getMessage());
                }
            }
        });

        limpaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preencherCampos();
            }
        });
    }
}