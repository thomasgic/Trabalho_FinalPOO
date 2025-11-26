package entidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class LeituraArquivosIniciais {

    // Ler PARTICIPANTES (fornecedores E compradores)
    public void lerParticipantes(String nomeArquivo,
                                        CentralFornecimento centralFornecimento,
                                        CentralCompradores centralCompradores) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            br.readLine(); // Pular cabeçalho

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 5) {
                    long cod = Long.parseLong(dados[0].trim());
                    String nome = dados[1].trim();
                    int tipo = Integer.parseInt(dados[2].trim());
                    String fundacaoPais = dados[3].trim();
                    String areaEmail = dados[4].trim();

                    if (tipo == 1) { // Fornecedor
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Date fundacao = formato.parse(fundacaoPais);
                        Area area = Area.valueOf(areaEmail.toUpperCase());

                        Fornecedor f = new Fornecedor(nome, cod, area, fundacao);
                        centralFornecimento.CadastraFornecedor(f);
                    } else if (tipo == 2) { // Comprador
                        String pais = fundacaoPais;
                        String email = areaEmail;

                        Comprador c = new Comprador(cod, nome, pais, email);
                        centralCompradores.cadastraComprador(c);
                    }
                }
            }
            System.out.println("Participantes carregados com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao ler participantes: " + e.getMessage());
        }
    }

    // Ler TECNOLOGIAS
    public void lerTecnologias(String nomeArquivo,
                                      CatalogoTecnologias catalogoTecnologias,
                                      CentralFornecimento centralFornecimento) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            br.readLine(); // Pular cabeçalho

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 7) {
                    long id = Long.parseLong(dados[0].trim());
                    String modelo = dados[1].trim();
                    String descricao = dados[2].trim();
                    double valorBase = Double.parseDouble(dados[3].trim());
                    double peso = Double.parseDouble(dados[4].trim());
                    double temperatura = Double.parseDouble(dados[5].trim());
                    long codFornecedor = Long.parseLong(dados[6].trim());

                    Tecnologia t = new Tecnologia(id, modelo, descricao, valorBase, peso, temperatura);
                    Fornecedor f = centralFornecimento.verificaCod(codFornecedor);
                    if (f != null) {
                        t.defineFornecedor(f);
                    }
                    catalogoTecnologias.cadastrarTecnologia(t);
                }
            }
            System.out.println("Tecnologias carregadas com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao ler tecnologias: " + e.getMessage());
        }
    }

    // Ler VENDAS em uma FILA
    public Queue<Venda> lerVendasEmFila(String nomeArquivo,
                                               CentralCompradores centralCompradores,
                                               CatalogoTecnologias catalogoTecnologias) {
        Queue<Venda> filaVendas = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            br.readLine(); // Pular cabeçalho

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 4) {
                    long numeroVenda = Long.parseLong(dados[0].trim());
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataVenda = formato.parse(dados[1].trim());
                    long codComprador = Long.parseLong(dados[2].trim());
                    long idTecnologia = Long.parseLong(dados[3].trim());

                    Comprador comprador = centralCompradores.verificaCod(codComprador);
                    Tecnologia tecnologia = catalogoTecnologias.verificaId(idTecnologia);

                    if (comprador != null && tecnologia != null) {
                        Venda venda = new Venda(numeroVenda, dataVenda, comprador, tecnologia);
                        filaVendas.add(venda);
                    }
                }
            }
            System.out.println("Vendas lidas em fila com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao ler vendas: " + e.getMessage());
        }

        return filaVendas;
    }
}