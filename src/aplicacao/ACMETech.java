package aplicacao;

import GUI.JanelaPrincipal;
import entidades.CentralFornecimento;

public class ACMETech {
    private JanelaPrincipal janelaPrincipal;
    private CentralFornecimento centralFornecimento;

    public ACMETech(){
        centralFornecimento = new CentralFornecimento();
    }

    public void executar(){
        janelaPrincipal = new JanelaPrincipal(centralFornecimento);
    }
}
