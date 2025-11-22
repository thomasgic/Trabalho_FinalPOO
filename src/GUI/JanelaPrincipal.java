package GUI;

import entidades.CentralFornecimento;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
private PainelFornecedor painelFornecedor;
private PainelInicial painelInicial;
private CentralFornecimento centralFornecimento;

    public JanelaPrincipal(CentralFornecimento cf){
        super();
        painelFornecedor = new PainelFornecedor();
        painelInicial = new PainelInicial();
        this.centralFornecimento = cf;
        JPanel PainelPrincipal = new JPanel();
        BorderLayout layoutPrincipal = new BorderLayout();
        setLayout(layoutPrincipal);
        this.setSize(800,500);
        this.setTitle("Tela de opções");
        //this.add(painelFornecedor.getPainel());
        this.add(painelInicial.getPainelInicial());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);


    }
}
