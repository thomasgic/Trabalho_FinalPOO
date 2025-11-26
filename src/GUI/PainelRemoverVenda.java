package GUI;

import aplicacao.ACMETech;
import entidades.Venda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelRemoverVenda extends JDialog {
    private JPanel principal;
    private JPanel instrucao;
    private JTextField digitaNumeroTF;
    private JLabel instrucaoLabel;
    private JTextArea textArea1;
    private JPanel mensagens;
    private JButton confirmarButton;
    private JButton voltarButton;
    private ACMETech acmeTech;

    public PainelRemoverVenda(ACMETech acmeTech) {
        setContentPane(principal);
        setModal(true);
        this.acmeTech = acmeTech;
        tratamentoEventos();

    }

    private void tratamentoEventos() {
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String numeroTexto = digitaNumeroTF.getText().trim();

                    if (numeroTexto.isEmpty()) {
                        textArea1.setText("ERRO: Digite o número da venda!");
                        return;
                    }

                    long numeroVenda = Long.parseLong(numeroTexto);

                    // Verificar se a venda existe
                    Venda venda = acmeTech.getCentralVendas().verificaNumero(numeroVenda);

                    if (venda == null) {
                        textArea1.setText("ERRO: Venda não encontrada!\n" +
                                "Número: " + numeroVenda);
                    } else {
                        // Mostrar dados da venda e confirmar remoção
                        int opcao = JOptionPane.showConfirmDialog(
                                PainelRemoverVenda.this,
                                "Deseja realmente remover esta venda?\n\n" + venda.geraDescricaoVenda(),
                                "Confirmar Remoção",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE
                        );

                        if (opcao == JOptionPane.YES_OPTION) {
                            if (acmeTech.getCentralVendas().removeVenda(numeroVenda)) {
                                textArea1.setText("Venda removida com sucesso!\n\n" +
                                        "Dados da venda removida:\n" +
                                        venda.geraDescricaoVenda());
                                digitaNumeroTF.setText("");
                            } else {
                                textArea1.setText("ERRO: Não foi possível remover a venda!");
                            }
                        } else {
                            textArea1.setText("Operação cancelada pelo usuário.");
                        }
                    }

                } catch (NumberFormatException ex) {
                    textArea1.setText("ERRO: O número da venda deve ser numérico!");
                    digitaNumeroTF.setText("");
                } catch (Exception ex) {
                    textArea1.setText("ERRO: Algo deu errado!\n" + ex.getMessage());
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose();
            }
        });
    }
}