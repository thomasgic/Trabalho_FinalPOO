package GUI;

import aplicacao.ACMETech;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelSalvarArquivos extends JDialog {
    private JPanel principal;
    private JPanel instrucao;
    private JLabel instrucaoLabel;
    private JPanel digitacao;
    private JTextField nomeArquivoTF;
    private JPanel botoes;
    private JButton ConfirmaButton;
    private JButton FecharButton;
    private JTextArea mensagemTA;
    private JLabel mensagemLabel;
    private JPanel areaMensagem;
    private JPanel mostraMensagem;
    private JanelaPrincipal janelaPrincipal;

    public PainelSalvarArquivos(JanelaPrincipal janelaPrincipal) {
        super(janelaPrincipal);
        this.janelaPrincipal = janelaPrincipal;
        setContentPane(principal);
        setModal(true);
        setTitle("Salvar Dados");
        tratamentoEventos();
    }

    private void tratamentoEventos() {
        ConfirmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensagemTA.setText("");

                String nomeArquivo = nomeArquivoTF.getText().trim();

                // 2. Validação básica
                if (nomeArquivo.isEmpty()) {
                    mensagemTA.setText("Erro: O nome do arquivo não pode ser vazio.");
                    return;
                }

                // 3. Tenta salvar
                ACMETech acmeTech = janelaPrincipal.getAcmeTech();
                boolean sucesso = acmeTech.salvarDados(nomeArquivo);

                // 4. Mostra o resultado na TextArea
                if (sucesso) {
                    mensagemTA.setText("SUCESSO!\nOs dados foram salvos no arquivo:\n" + nomeArquivo);
                    nomeArquivoTF.setText("");
                } else {
                    mensagemTA.setText("ERRO!\nNão foi possível salvar os dados.\nTente outro nome.");
                }
            }
        });
        FecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public JPanel getPrincipal() {
        return principal;
    }
}