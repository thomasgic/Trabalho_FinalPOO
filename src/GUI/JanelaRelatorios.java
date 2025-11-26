package GUI;

import aplicacao.ACMETech;
import entidades.Comprador;
import entidades.Fornecedor;
import entidades.Tecnologia;
import entidades.Venda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JanelaRelatorios {
    private JButton tecnologiasButton;
    private JButton fornecedoresButton;
    private JButton compradoresButton;
    private JButton vendasButton;
    private JTextArea resultadoRelatorioTA;
    private JLabel relatoriosLabel;
    private JPanel principal;
    private JPanel painelBotoes;
    private JPanel painelMostraRelatorios;
    private JButton voltarButton;
    private JanelaPrincipal janelaPrincipal;
    private ACMETech acmeTech;

    public JanelaRelatorios(JanelaPrincipal janelaPrincipal, ACMETech acmeTech) {
        super();
        this.acmeTech = acmeTech;
        this.janelaPrincipal = janelaPrincipal;
        tratamentoEventos();
    }

    private void tratamentoEventos() {
        fornecedoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoRelatorioTA.setText("");
                ArrayList<Fornecedor> fornecedores = acmeTech.getCentralFornecimento().mostraFornecedores();

                if (fornecedores.isEmpty()) {
                    resultadoRelatorioTA.setText("Não existem fornecedores cadastrados.");
                } else {
                    resultadoRelatorioTA.append("========== RELATÓRIO DE FORNECEDORES ==========\n\n");
                    for (Fornecedor f : fornecedores) {
                        resultadoRelatorioTA.append(f.geraDescricao() + "\n");
                        resultadoRelatorioTA.append("─────────────────────────────────────────────\n");
                    }
                    resultadoRelatorioTA.append("\nTotal de fornecedores: " + fornecedores.size());
                }
            }
        });

        tecnologiasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoRelatorioTA.setText("");
                ArrayList<Tecnologia> tecnologias = acmeTech.getCatalogoTecnologias().mostrarTecnologias();

                if (tecnologias.isEmpty()) {
                    resultadoRelatorioTA.setText("Não existem tecnologias cadastradas.");
                } else {
                    resultadoRelatorioTA.append("========== RELATÓRIO DE TECNOLOGIAS ==========\n\n");
                    for (Tecnologia t : tecnologias) {
                        resultadoRelatorioTA.append("ID: " + t.getId() + "\n");
                        resultadoRelatorioTA.append("Modelo: " + t.getModelo() + "\n");
                        resultadoRelatorioTA.append("Descrição: " + t.getDescricao() + "\n");
                        resultadoRelatorioTA.append("Fornecedor: " +
                                (t.getFornecedor() != null ? t.getFornecedor().getNome() : "Não atribuído") + "\n");
                        resultadoRelatorioTA.append("Valor Base: R$ " + String.format("%.2f", t.getValorBase()) + "\n");
                        resultadoRelatorioTA.append("Peso: " + t.getPeso() + " kg\n");
                        resultadoRelatorioTA.append("Temperatura: " + t.getTemperatura() + "°C\n");
                        resultadoRelatorioTA.append("─────────────────────────────────────────────\n");
                    }
                    resultadoRelatorioTA.append("\nTotal de tecnologias: " + tecnologias.size());
                }
            }
        });

        compradoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoRelatorioTA.setText("");
                ArrayList<Comprador> compradores = acmeTech.getCentralCompradores().mostrarCompradores();

                if (compradores.isEmpty()) {
                    resultadoRelatorioTA.setText("Não existem compradores cadastrados.");
                } else {
                    resultadoRelatorioTA.append("========== RELATÓRIO DE COMPRADORES ==========\n\n");
                    for (Comprador c : compradores) {
                        resultadoRelatorioTA.append(c.geraDescricao() + "\n");
                        resultadoRelatorioTA.append("─────────────────────────────────────────────\n");
                    }
                    resultadoRelatorioTA.append("\nTotal de compradores: " + compradores.size());
                }
            }
        });

        vendasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoRelatorioTA.setText("");
                ArrayList<Venda> vendas = acmeTech.getCentralVendas().mostraVendas();

                if (vendas.isEmpty()) {
                    resultadoRelatorioTA.setText("Não existem vendas cadastradas.");
                } else {
                    resultadoRelatorioTA.append("========== RELATÓRIO DE VENDAS ==========\n\n");
                    double totalVendas = 0;
                    for (Venda v : vendas) {
                        resultadoRelatorioTA.append(v.geraDescricaoVenda() + "\n");
                        resultadoRelatorioTA.append("─────────────────────────────────────────────\n");
                        totalVendas += v.getValorFinal();
                    }
                    resultadoRelatorioTA.append("\nTotal de vendas: " + vendas.size());
                    resultadoRelatorioTA.append("\nValor total: R$ " + String.format("%.2f", totalVendas));
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
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