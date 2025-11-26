package aplicacao;

import GUI.JanelaPrincipal;
import entidades.*;

//Classe de aplicação

public class ACMETech {
    private JanelaPrincipal janelaPrincipal;
    private CentralFornecimento centralFornecimento;
    private CentralCompradores centralCompradores;
    private CentralVendas centralVendas;
    private CatalogoTecnologias catalogoTecnologias;

    public ACMETech(){
        centralVendas = new CentralVendas();
        centralFornecimento = new CentralFornecimento();
        centralCompradores = new CentralCompradores();
        catalogoTecnologias = new CatalogoTecnologias();
    }

    public void executar(){
        janelaPrincipal = new JanelaPrincipal(this);
    }

    public CentralFornecimento getCentralFornecimento () {
        return centralFornecimento;
    }
    public CentralCompradores getCentralCompradores () {return centralCompradores;}
    public CentralVendas getCentralVendas () {return centralVendas;}
    public CatalogoTecnologias getCatalogoTecnologias() {return catalogoTecnologias;}


    // ADICIONAR ESTES MÉTODOS:
    public boolean salvarDados() {
        return GerenciadorArquivos.salvarTodosDados(
                centralFornecimento,
                catalogoTecnologias,
                centralCompradores,
                centralVendas
        );
    }

    public boolean carregarDados() {
        try {
            centralFornecimento.setFornecedores(GerenciadorArquivos.carregarFornecedores());
            catalogoTecnologias.setTecnologias(GerenciadorArquivos.carregarTecnologias());
            centralCompradores.setCompradores(GerenciadorArquivos.carregarCompradores());
            centralVendas.setVendas(GerenciadorArquivos.carregarVendas());
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados: " + e.getMessage());
            return false;
        }
    }
}

