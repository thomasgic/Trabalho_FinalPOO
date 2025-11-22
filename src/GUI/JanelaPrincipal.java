package GUI;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
PainelFornecedor painelFornecedor;

    public JanelaPrincipal(){
        super();
        painelFornecedor = new PainelFornecedor();
        JPanel PainelPrincipal = new JPanel();
        BorderLayout layoutPrincipal = new BorderLayout();
        setLayout(layoutPrincipal);
        this.setSize(800,350);
        this.setTitle("Cadastro Fornecedor");
        this.add(painelFornecedor.getPainel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);


    }
}
