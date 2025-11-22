package aplicacao;

import GUI.JanelaPrincipal;
import entidades.CentralFornecimento;

//Classe de aplicação

public class ACMETech {
    private JanelaPrincipal janelaPrincipal;
    private CentralFornecimento centralFornecimento;

    public ACMETech(){
        centralFornecimento = new CentralFornecimento();
    }

    public void executar(){
        janelaPrincipal = new JanelaPrincipal(this);
    }
}
