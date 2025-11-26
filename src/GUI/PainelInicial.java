package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelInicial {
    private JLabel instrucaoLabel;
    private JButton removeVendaButton;
    private JButton finalizarButton;
    private JButton consultaButton;
    private JButton cadastroButton;
    private JButton relatorioButton;
    private JButton alterarDadosButton;
    private JButton carregaDadosButton;
    private JButton salvaButton;
    private JPanel painelInicial;
    private JanelaPrincipal janelaPrincipal;

    public PainelInicial(JanelaPrincipal janelaPrincipal){
    super();
    this.janelaPrincipal = janelaPrincipal;
    tratamentoDeEventos();
    }


    public JPanel getPainelInicial(){
        return painelInicial;
    }

    public void tratamentoDeEventos(){
        cadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                janelaPrincipal.mudaTela(1);
            }
        });

        relatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                janelaPrincipal.mudaTela(2);
            }
        });

        consultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                janelaPrincipal.mudaTela(3);
            }
        });

        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                janelaPrincipal.dispose();
            }
        });

        alterarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                janelaPrincipal.mudaTela(4);
            }
        });

        removeVendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                janelaPrincipal.mudaTela(5);
            }
        });

        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (janelaPrincipal.getAcmeTech().salvarDados()) {
                    JOptionPane.showMessageDialog(null,
                            "Dados salvos com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Erro ao salvar dados!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);

            }}
        });

        carregaDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                if (janelaPrincipal.getAcmeTech().carregarDados()) {
                    JOptionPane.showMessageDialog(null,
                            "Dados carregados com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Erro ao carregar dados!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
