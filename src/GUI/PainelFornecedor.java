package GUI;
import aplicacao.ACMETech;
import entidades.Area;
import entidades.Fornecedor;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class PainelFornecedor extends JDialog {

    private JPanel principal;
    private JPanel CampoDigitDados;
    private JPanel painelBotoes;
    private JPanel painelMostraDados;
    private JLabel instrucaoCod;
    private JLabel instrucaoArea;
    private JLabel instrucaoData;
    private JLabel instrucaoNome;
    private JTextField campoDigitaCod;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JButton limparButton;
    private JButton mostrarButton;
    private JTextArea areaMensagens;
    private JTextField campoDigitaNome;
    private JTextField campoDigitaArea;
    private JTextField campoDigitaFund;

    private ACMETech acmeTech;

    public PainelFornecedor (ACMETech acmeTech) {
        setContentPane(principal);
        setModal(true);
        this.acmeTech = acmeTech;
        tratamentoEventos();
    }

    public void tratamentoEventos() {

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                try {

                    String nome = campoDigitaNome.getText();
                    String fundacao = campoDigitaFund.getText();
                    String area = campoDigitaArea.getText().toUpperCase();
                    String codTexto = campoDigitaCod.getText();

                    if (nome.isEmpty() || fundacao.isEmpty() || area.isEmpty() || codTexto.isEmpty())
                        areaMensagens.setText("ERRO: Preencha todos os campos!");
                    else {

                        long codLong = Long.parseLong(codTexto);
                        if (acmeTech.getCentralFornecimento().verificaCod(codLong) != null) {
                            areaMensagens.setText("ERRO: código já existente!");
                            campoDigitaCod.setText("");
                        } else {
                            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                            Date fundacaoFormatado = formatoData.parse(fundacao);

                            Area areaFormatado = Area.valueOf(area);

                            Fornecedor fornecedor = new Fornecedor(nome, codLong, areaFormatado, fundacaoFormatado);
                            if (acmeTech.getCentralFornecimento().CadastraFornecedor(fornecedor)) {
                                areaMensagens.setText("");
                                areaMensagens.append("Fornecedor cadastrado com sucesso!");
                                campoDigitaNome.setText("");
                                campoDigitaArea.setText("");
                                campoDigitaFund.setText("");
                                campoDigitaCod.setText("");
                            }
                        }
                    }
                }

                catch(ParseException f){
                        areaMensagens.setText("Erro: Formato de data inválido!" + "\nFormato aceito: dd/mm/aaaa");
                    }
                catch(NumberFormatException f){
                        areaMensagens.setText("Erro: código apenas com valores numéricos!");
                    }

                catch (IllegalArgumentException f){
                    areaMensagens.setText("Erro: Área inválida" + "\n Áreas válidas:" +  "\nTI" + "\nANDROIDES" + "\nEMERGENTE" + "\nALIMENTOS");
                }

                catch(Exception f){
                        areaMensagens.setText("Erro: algo deu errado!");
                    }
                }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                areaMensagens.setText("");
                campoDigitaNome.setText("");
                campoDigitaArea.setText("");
                campoDigitaFund.setText("");
                campoDigitaCod.setText("");
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
