package GUI;

import aplicacao.ACMETech;
import entidades.CentralFornecimento;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
private JanelaCadastros janelaCadastros;
private PainelInicial painelInicial;
private ACMETech acmeTech;
int opc;
    public JanelaPrincipal(ACMETech acmeTech){
        super();

        janelaCadastros = new JanelaCadastros(this, acmeTech);
        painelInicial = new PainelInicial(this);
        this.acmeTech = acmeTech;
        BorderLayout layoutPrincipal = new BorderLayout();
        setLayout(layoutPrincipal);
        this.setSize(800,500);
        this.setTitle("Menu principal");
        this.setLocationRelativeTo(null);
        this.add(painelInicial.getPainelInicial());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // metodo que altera as telas conforme o botão clicado(tratamento de eventos no "painelInicial").
    public void mudaTela(int opcao){
        switch(opcao){
            case 0:
                this.setContentPane(painelInicial.getPainelInicial());
                this.setTitle("Menu Principal");
                this.setSize(800, 500);
                this.setLocationRelativeTo(null);
                break;
            case 1:
                this.setContentPane(janelaCadastros.getPainel());
                this.setTitle("Tela de cadastros");
                this.setSize(800,300);
                //aqui deu um bug que a tela congelava, então achei esses métodos.
                this.revalidate();
                this.repaint();
                break;
            }
        }
    }
