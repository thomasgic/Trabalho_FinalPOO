package aplicacao;

import GUI.JanelaPrincipal;
import entidades.*;

import java.util.Queue;

public class ACMETech {
    private JanelaPrincipal janelaPrincipal;
    private CentralFornecimento centralFornecimento;
    private CentralCompradores centralCompradores;
    private CatalogoTecnologias catalogoTecnologias;
    private CentralVendas centralVendas;
    private GerenciadorArquivos gerenciadorArquivos;

    public ACMETech(){
        centralFornecimento = new CentralFornecimento();
        centralCompradores = new CentralCompradores();
        catalogoTecnologias = new CatalogoTecnologias();
        centralVendas = new CentralVendas();
        gerenciadorArquivos = new GerenciadorArquivos();
    }

    // ADICIONAR ESTE MÉTODO
    public void inicializar() {
        // Ler participantes (fornecedores e compradores)
        LeituraArquivosIniciais.lerParticipantes("PARTICIPANTESENTRADA.CSV",
                centralFornecimento,
                centralCompradores);

        // Ler tecnologias
        LeituraArquivosIniciais.lerTecnologias("TECNOLOGIASENTRADA.CSV",
                catalogoTecnologias,
                centralFornecimento);

        // Ler vendas em fila
        Queue<Venda> filaVendas = LeituraArquivosIniciais.lerVendasEmFila("VENDASENTRADA.CSV",
                centralCompradores,
                catalogoTecnologias);

        // Processar vendas da fila
        while (!filaVendas.isEmpty()) {
            Venda venda = filaVendas.poll();
            centralVendas.cadastraVenda(venda);
        }

        System.out.println("Inicialização concluída!");
    }

    public void executar(){
        janelaPrincipal = new JanelaPrincipal(this);
    }

    public CentralFornecimento getCentralFornecimento() {
        return centralFornecimento;
    }

    public CentralCompradores getCentralCompradores() {
        return centralCompradores;
    }

    public CatalogoTecnologias getCatalogoTecnologias() {
        return catalogoTecnologias;
    }

    public CentralVendas getCentralVendas() {
        return centralVendas;
    }

    public boolean salvarDados(String nomeArquivo) {
        return gerenciadorArquivos.salvarSistema(this, nomeArquivo);

    }

    public boolean carregarDados(String nomeArquivo) {
        return gerenciadorArquivos.carregarSistema(this, nomeArquivo);
    }
}