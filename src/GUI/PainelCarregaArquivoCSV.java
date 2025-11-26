package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelCarregaArquivoCSV extends JDialog {
    private JPanel principal;
    private JPanel instrucao;
    private JLabel instrucaoLabel;
    private JPanel digitacao;
    private JTextField nomeArquivoTF;
    private JPanel botoes;
    private JButton ConfirmaButton;
    private JButton FecharButton;
    private JPanel mostraMensagem;
    private JTextArea mensagemTA;
    private JPanel areaMensagem;
    private JLabel mensagemLabel;
    private JanelaPrincipal janelaPrincipal;


    public PainelCarregaArquivoCSV (JanelaPrincipal janelaPrincipal) {
        super(janelaPrincipal);
        this.janelaPrincipal = janelaPrincipal;
        setContentPane(principal);
        setModal(true);
        setTitle("Salvar Dados");
        tratamentoEventos();
    }

    private void tratamentoEventos(){
        ConfirmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                mensagemTA.setText("");
                String nomeArquivo = nomeArquivoTF.getText();
                if (nomeArquivo.isEmpty()) {
                    mensagemTA.setText("Erro: digite o nome do arquivo.");
                    return;
                }

                try {
                    boolean sucesso = janelaPrincipal.getAcmeTech().carregarDados(nomeArquivo);

                    if (sucesso) {
                        mensagemTA.setText("SUCESSO!\nOs dados foram carregados e o sistema foi atualizado.");
                        nomeArquivoTF.setText("");
                    } else {
                        mensagemTA.setText("ERRO!\nArquivo não encontrado ou corrompido.\nVerifique se o nome está correto (sem extensão).");
                    }
                } catch (Exception ex) {
                    mensagemTA.setText("ERRO INESPERADO:\n" + ex.getMessage());
                }
            }

        });

        FecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose();
            }
        });
    }
}
