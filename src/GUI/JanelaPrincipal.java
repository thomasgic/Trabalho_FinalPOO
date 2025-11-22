package GUI;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
PainelFornecedor painelFornecedor;
PainelInicial painelInicial;

    public JanelaPrincipal(){
        super();
        painelFornecedor = new PainelFornecedor();
        painelInicial = new PainelInicial();
        JPanel PainelPrincipal = new JPanel();
        BorderLayout layoutPrincipal = new BorderLayout();
        setLayout(layoutPrincipal);
        this.setSize(800,350);
        this.setTitle("Tela de opções");
        //this.add(painelFornecedor.getPainel());
        this.add(painelInicial.getPainelInicial());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);


    }
}
