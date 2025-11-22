package GUI;

import aplicacao.ACMETech;
import entidades.CentralFornecimento;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
private PainelFornecedor painelFornecedor;
private PainelInicial painelInicial;
private ACMETech acmeTech;
int opc;
    public JanelaPrincipal(ACMETech acmeTech){
        super();
        painelFornecedor = new PainelFornecedor();
        painelInicial = new PainelInicial(this);
        this.acmeTech = acmeTech;
        JPanel PainelPrincipal = new JPanel();
        BorderLayout layoutPrincipal = new BorderLayout();
        setLayout(layoutPrincipal);
        this.setSize(800,500);
        this.setTitle("Tela de opções");
        this.add(painelInicial.getPainelInicial());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // metodo que altera as telas conforme o botão clicado(tratamento de eventos no "painelInicial").
    public void mudaTela(int opcao){
        switch(opcao){
            case 1:
                this.setContentPane(painelFornecedor.getPainel());
                this.setTitle("Tela de cadastros");
                //aqui deu um bug que a tela congelava, então achei esses métodos.
                this.revalidate();
                this.repaint();
            }
        }
    }
