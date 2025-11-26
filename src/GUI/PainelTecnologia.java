package GUI;

import aplicacao.ACMETech;
import entidades.Fornecedor;
import entidades.Tecnologia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public PainelTecnologia(ACMETech acmeTech) {
        super();
        setContentPane(principal);
        setModal(true);
        this.acmeTech = acmeTech;
        tratamentoDeEventos();
    }

    public void tratamentoDeEventos() {
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idTexto = campoDigitaID.getText();
                    String modelo = campoDigitaModelo.getText();
                    String descricao = campoDigitaDescricao.getText();
                    String valorTexto = campoDigitaValorBase.getText();
                    String pesoTexto = campoDigitaPeso.getText();
                    String tempTexto = CampoDigitaTemp.getText();

                    if (idTexto.isEmpty() || modelo.isEmpty() || descricao.isEmpty() ||
                            valorTexto.isEmpty() || pesoTexto.isEmpty() || tempTexto.isEmpty()) {
                        areaMensagens.setText("ERRO: Preencha todos os campos!");
                    } else {
                        long id = Long.parseLong(idTexto);

                        if (acmeTech.getCatalogoTecnologias().verificaId(id) != null) {
                            areaMensagens.setText("ERRO: ID já existente!");
                            campoDigitaID.setText("");
                        } else {
                            double valor = Double.parseDouble(valorTexto);
                            double peso = Double.parseDouble(pesoTexto);
                            double temp = Double.parseDouble(tempTexto);

                            // Solicitar código do fornecedor
                            String codFornecedorTexto = JOptionPane.showInputDialog(
                                    PainelTecnologia.this,
                                    "Digite o código do fornecedor desta tecnologia:",
                                    "Associar Fornecedor",
                                    JOptionPane.QUESTION_MESSAGE
                            );

                            if (codFornecedorTexto != null && !codFornecedorTexto.isEmpty()) {
                                long codFornecedor = Long.parseLong(codFornecedorTexto);
                                Fornecedor fornecedor = acmeTech.getCentralFornecimento().verificaCod(codFornecedor);

                                if (fornecedor == null) {
                                    areaMensagens.setText("ERRO: Fornecedor não encontrado!");
                                } else {
                                    Tecnologia tecnologia = new Tecnologia(id, modelo, descricao, valor, peso, temp);
                                    tecnologia.setFornecedor(fornecedor);

                                    if (acmeTech.getCatalogoTecnologias().cadastrarTecnologia(tecnologia)) {
                                        areaMensagens.setText("Tecnologia cadastrada com sucesso!\n" +
                                                "Fornecedor: " + fornecedor.getNome());
                                        limparCampos();
                                    }
                                }
                            }
                        }
                    }
                } catch (NumberFormatException ex) {
                    areaMensagens.setText("Erro: Valores numéricos inválidos!");
                } catch (Exception ex) {
                    areaMensagens.setText("Erro: algo deu errado! " + ex.getMessage());
                }
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaMensagens.setText("");
                ArrayList<Fornecedor> fornecedores = acmeTech.getCentralFornecimento().mostraFornecedores();
                if (fornecedores.isEmpty()) {
                    areaMensagens.setText("Não existem fornecedores cadastrados");
                } else {
                    areaMensagens.append("=== FORNECEDORES CADASTRADOS ===\n");
                    for (Fornecedor f : fornecedores) {
                        areaMensagens.append(f.geraDescricao() + "\n");
                    }
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void limparCampos() {
        areaMensagens.setText("");
        campoDigitaID.setText("");
        campoDigitaDescricao.setText("");
        campoDigitaModelo.setText("");
        campoDigitaPeso.setText("");
        campoDigitaValorBase.setText("");
        CampoDigitaTemp.setText("");
    }

    public JPanel getPainel() {
        return principal;
    }
}