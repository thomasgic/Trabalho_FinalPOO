package GUI;

import aplicacao.ACMETech;
import entidades.Comprador;
import entidades.Tecnologia;
import entidades.Venda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PainelVenda extends JDialog {
    private JPanel principal;
    private JPanel painelDados;
    private JTextField numeroVendaTF;
    private JTextField dataVendaTF;
    private JComboBox<String> CompradoresCB;
    private JComboBox<String> tecnologiaCB;
    private JPanel painelInfos;
    private JButton confirmarButton;
    private JButton mostrarButton;
    private JButton limparButton;
    private JButton cancelarButton;
    private JTextArea mensagensTA;
    private JPanel painelMensagens;
    private JPanel painelBotoes;
    private JLabel compradoresLabel;
    private JLabel tecnologiaLabel;
    private JLabel numeroVendaLabel;
    private JLabel dataVendaLabel;
    private JLabel mensagensLabel;
    private ACMETech acmeTech;

    public PainelVenda(ACMETech acmeTech) {
        super();
        this.acmeTech = acmeTech;
        setContentPane(principal);
        setModal(true);
        carregarComboBoxes();
        tratamentoEventos();
    }

    private void carregarComboBoxes() {
        CompradoresCB.removeAllItems();
        tecnologiaCB.removeAllItems();

        // Carregar compradores
        ArrayList<Comprador> compradores = acmeTech.getCentralCompradores().mostrarCompradores();
        for (Comprador c : compradores) {
            CompradoresCB.addItem(c.getCod() + " - " + c.getNome());
        }

        // Carregar APENAS tecnologias disponíveis (não vendidas)
        ArrayList<Tecnologia> tecnologias = acmeTech.getCatalogoTecnologias().mostrarTecnologias();
        for (Tecnologia t : tecnologias) {
            if (!t.isVendida()) { // ADICIONAR ESTA VERIFICAÇÃO
                tecnologiaCB.addItem(t.getId() + " - " + t.getModelo());
            }
        }
    }

    private void tratamentoEventos() {
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String numeroTexto = numeroVendaTF.getText();
                    String dataTexto = dataVendaTF.getText();

                    if (numeroTexto.isEmpty() || dataTexto.isEmpty()) {
                        mensagensTA.setText("ERRO: Preencha todos os campos!");
                        return;
                    }

                    if (CompradoresCB.getSelectedIndex() == -1 || tecnologiaCB.getSelectedIndex() == -1) {
                        mensagensTA.setText("ERRO: Selecione um comprador e uma tecnologia!");
                        return;
                    }

                    long numeroVenda = Long.parseLong(numeroTexto);

                    if (acmeTech.getCentralVendas().verificaNumero(numeroVenda) != null) {
                        mensagensTA.setText("ERRO: Número de venda já existente!");
                        numeroVendaTF.setText("");
                        return;
                    }

                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    Date data = formato.parse(dataTexto);

                    // Extrair código do comprador
                    String compradorSelecionado = (String) CompradoresCB.getSelectedItem();
                    long codComprador = Long.parseLong(compradorSelecionado.split(" - ")[0]);
                    Comprador comprador = acmeTech.getCentralCompradores().verificaCod(codComprador);

                    // Extrair ID da tecnologia
                    String tecnologiaSelecionada = (String) tecnologiaCB.getSelectedItem();
                    long idTecnologia = Long.parseLong(tecnologiaSelecionada.split(" - ")[0]);
                    Tecnologia tecnologia = acmeTech.getCatalogoTecnologias().verificaId(idTecnologia);

                    if (comprador == null || tecnologia == null) {
                        mensagensTA.setText("ERRO: Dados inválidos!");
                        return;
                    }

                    // VERIFICAR SE A TECNOLOGIA JÁ FOI VENDIDA
                    if (tecnologia.isVendida()) {
                        mensagensTA.setText("ERRO: Esta tecnologia já foi vendida!");
                        return;
                    }

                    Venda venda = new Venda(numeroVenda, data, comprador, tecnologia);

                    if (acmeTech.getCentralVendas().cadastraVenda(venda)) {
                        mensagensTA.setText("Venda cadastrada com sucesso!\n" +
                                venda.toString() +
                                "\nValor calculado: R$ " + String.format("%.2f", venda.getValorFinal()));
                        limparCampos();
                        carregarComboBoxes(); // Atualizar ComboBox (tecnologias vendidas não devem aparecer)
                    }

                } catch (ParseException ex) {
                    mensagensTA.setText("Erro: Formato de data inválido!\nFormato aceito: dd/MM/yyyy");
                } catch (NumberFormatException ex) {
                    mensagensTA.setText("Erro: Número de venda deve ser numérico!");
                } catch (Exception ex) {
                    mensagensTA.setText("Erro: algo deu errado! " + ex.getMessage());
                }
            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensagensTA.setText("");
                ArrayList<Venda> vendas = acmeTech.getCentralVendas().mostraVendas();
                if (vendas.isEmpty()) {
                    mensagensTA.setText("Não existem vendas cadastradas");
                } else {
                    mensagensTA.append("=== VENDAS CADASTRADAS ===\n");
                    for (Venda v : vendas) {
                        mensagensTA.append(v.geraDescricaoVenda() + "\n");
                    }
                }
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
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
        dataVendaTF.setText("");
        numeroVendaTF.setText("");
        mensagensTA.setText("Campos limpos.");
        CompradoresCB.setSelectedIndex(-1);
        tecnologiaCB.setSelectedIndex(-1);
    }
}