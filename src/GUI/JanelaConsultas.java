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
import java.util.HashMap;
import java.util.Map;

public class JanelaConsultas {
    private JPanel principal;
    private JButton maiorValorTecButton;
    private JButton compradorMaisVendasButton;
    private JButton fornecedorMaisTecButton;
    private JButton vendaMaiorValorButton;
    private JTextArea resultadoConsultasTA;
    private JLabel consultasLabel;
    private JButton voltarButton;
    private ACMETech acmeTech;
    private JanelaPrincipal janelaPrincipal;

    public JanelaConsultas(JanelaPrincipal janelaPrincipal, ACMETech acmeTech) {
        super();
        this.acmeTech = acmeTech;
        this.janelaPrincipal = janelaPrincipal;
        tratamentoEventos();
    }

    private void tratamentoEventos() {
        maiorValorTecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoConsultasTA.setText("");
                Tecnologia tech = acmeTech.getCatalogoTecnologias().tecnologiaMaiorValor();

                if (tech == null) {
                    resultadoConsultasTA.setText("Não existem tecnologias cadastradas.");
                } else {
                    resultadoConsultasTA.append("========== TECNOLOGIA DE MAIOR VALOR ==========\n\n");
                    resultadoConsultasTA.append("ID: " + tech.getId() + "\n");
                    resultadoConsultasTA.append("Modelo: " + tech.getModelo() + "\n");
                    resultadoConsultasTA.append("Descrição: " + tech.getDescricao() + "\n");
                    resultadoConsultasTA.append("Fornecedor: " +
                            (tech.getFornecedor() != null ? tech.getFornecedor().getNome() : "Não atribuído") + "\n");
                    resultadoConsultasTA.append("VALOR: R$ " + String.format("%.2f", tech.getValorBase()) + "\n");
                    resultadoConsultasTA.append("Peso: " + tech.getPeso() + " kg\n");
                    resultadoConsultasTA.append("Temperatura: " + tech.getTemperatura() + "°C\n");
                }
            }
        });

        vendaMaiorValorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoConsultasTA.setText("");
                Venda venda = acmeTech.getCentralVendas().vendaMaiorValor();

                if (venda == null) {
                    resultadoConsultasTA.setText("Não existem vendas cadastradas.");
                } else {
                    resultadoConsultasTA.append("========== VENDA DE MAIOR VALOR ==========\n\n");
                    resultadoConsultasTA.append(venda.geraDescricaoVenda() + "\n");
                    resultadoConsultasTA.append("\n==== DETALHES DA TECNOLOGIA ====\n");
                    resultadoConsultasTA.append("Modelo: " + venda.getTecnologia().getModelo() + "\n");
                    resultadoConsultasTA.append("Fornecedor: " + venda.getTecnologia().getFornecedor().getNome() + "\n");
                }
            }
        });

        compradorMaisVendasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoConsultasTA.setText("");
                Comprador comprador = acmeTech.getCentralVendas().compradorComMaisVendas();

                if (comprador == null) {
                    resultadoConsultasTA.setText("Não existem vendas cadastradas.");
                } else {
                    // Contar quantas vendas esse comprador tem
                    int totalVendas = 0;
                    double valorTotal = 0;
                    ArrayList<Venda> vendas = acmeTech.getCentralVendas().mostraVendas();

                    for (Venda v : vendas) {
                        if (v.getComprador().getCod() == comprador.getCod()) {
                            totalVendas++;
                            valorTotal += v.getValorFinal();
                        }
                    }

                    resultadoConsultasTA.append("========== COMPRADOR COM MAIS VENDAS ==========\n\n");
                    resultadoConsultasTA.append(comprador.geraDescricao() + "\n");
                    resultadoConsultasTA.append("\nTotal de vendas: " + totalVendas + "\n");
                    resultadoConsultasTA.append("Valor total gasto: R$ " + String.format("%.2f", valorTotal) + "\n");
                }
            }
        });

        fornecedorMaisTecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoConsultasTA.setText("");
                ArrayList<Tecnologia> tecnologias = acmeTech.getCatalogoTecnologias().mostrarTecnologias();

                if (tecnologias.isEmpty()) {
                    resultadoConsultasTA.setText("Não existem tecnologias cadastradas.");
                    return;
                }

                // Contar tecnologias por fornecedor
                Map<Long, Integer> contagemPorFornecedor = new HashMap<>();
                Map<Long, Fornecedor> fornecedores = new HashMap<>();

                for (Tecnologia t : tecnologias) {
                    if (t.getFornecedor() != null) {
                        long codFornecedor = t.getFornecedor().getCod();
                        contagemPorFornecedor.put(codFornecedor,
                                contagemPorFornecedor.getOrDefault(codFornecedor, 0) + 1);
                        fornecedores.put(codFornecedor, t.getFornecedor());
                    }
                }

                if (contagemPorFornecedor.isEmpty()) {
                    resultadoConsultasTA.setText("Nenhuma tecnologia está associada a um fornecedor.");
                    return;
                }

                // Encontrar o fornecedor com mais tecnologias
                long codMaior = -1;
                int maxTecnologias = 0;

                for (Map.Entry<Long, Integer> entry : contagemPorFornecedor.entrySet()) {
                    if (entry.getValue() > maxTecnologias) {
                        maxTecnologias = entry.getValue();
                        codMaior = entry.getKey();
                    }
                }

                Fornecedor fornecedor = fornecedores.get(codMaior);

                resultadoConsultasTA.append("========== FORNECEDOR COM MAIS TECNOLOGIAS ==========\n\n");
                resultadoConsultasTA.append(fornecedor.geraDescricao() + "\n");
                resultadoConsultasTA.append("\nTotal de tecnologias: " + maxTecnologias + "\n");
                resultadoConsultasTA.append("\n==== TECNOLOGIAS DESTE FORNECEDOR ====\n");

                for (Tecnologia t : tecnologias) {
                    if (t.getFornecedor() != null && t.getFornecedor().getCod() == codMaior) {
                        resultadoConsultasTA.append("• " + t.getModelo() +
                                " (ID: " + t.getId() + ") - R$ " +
                                String.format("%.2f", t.getValorBase()) + "\n");
                    }
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultadoConsultasTA.setText("");
                janelaPrincipal.mudaTela(0);
            }
        });
    }

    public JPanel getPrincipal() {
        return principal;
    }
}