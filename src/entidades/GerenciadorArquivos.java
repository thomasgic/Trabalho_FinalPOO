package entidades;

import aplicacao.ACMETech;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class GerenciadorArquivos {
    private final String CSV_PARTICIPANTES = "PARTICIPANTESENTRADA.CSV";
    private final String CSV_TECNOLOGIAS = "TECNOLOGIASENTRADA.CSV";
    private final String CSV_VENDAS = "VENDASENTRADA.CSV";

    public GerenciadorArquivos() {
    }

    // ========================================================================
    // MÉTODOS DE LEITURA (Usados tanto na Inicialização quanto no Carregar)
    // ========================================================================

    public void lerParticipantes(String nomeArquivo, CentralFornecimento cadForn, CentralCompradores cadComp) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            System.err.println("Arquivo " + nomeArquivo + " não encontrado.");
            return;
        }

        try (Scanner scanner = new Scanner(arquivo, StandardCharsets.UTF_8)) {
            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if (linha.isEmpty()) continue;

                try {
                    String[] dados = linha.split(";");

                    long cod = Long.parseLong(dados[0]);
                    String nome = dados[1];
                    int tipo = Integer.parseInt(dados[2]);
                    if (tipo == 1) { // Fornecedor [cite: 142-143]
                        String dataStr = dados[3];
                        String areaStr = dados[4];
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date fundacao = sdf.parse(dataStr);
                        Area area = Area.valueOf(areaStr.toUpperCase());
                        if (cadForn.verificaCod(cod) == null) {
                            cadForn.CadastraFornecedor(new Fornecedor(nome, cod, area, fundacao));
                        }

                    } else if (tipo == 2) { // Comprador [cite: 142-143]
                        String pais = dados[3];
                        String email = dados[4];

                        if (cadComp.verificaCod(cod) == null) {
                            cadComp.cadastraComprador(new Comprador(cod, nome, pais, email));
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao ler linha participante: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro IO Participantes: " + e.getMessage());
        }
    }

    public void lerTecnologias(String nomeArquivo, CatalogoTecnologias cadTec, CentralFornecimento cadForn) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) return;

        try (Scanner scanner = new Scanner(arquivo, StandardCharsets.UTF_8)) {
            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if (linha.isEmpty()) continue;

                try {
                    String[] dados = linha.split(";");
                    long id = Long.parseLong(dados[0]);

                    if (cadTec.verificaId(id) == null) { // Evita duplicados
                        String modelo = dados[1];
                        String descricao = dados[2];
                        double valor = Double.parseDouble(dados[3]);
                        double peso = Double.parseDouble(dados[4]);
                        double temp = Double.parseDouble(dados[5]);
                        long codForn = Long.parseLong(dados[6]);

                        Tecnologia t = new Tecnologia(id, modelo, descricao, valor, peso, temp);
                        Fornecedor f = cadForn.verificaCod(codForn);
                        if (f != null) t.defineFornecedor(f);

                        cadTec.cadastrarTecnologia(t);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao ler tecnologia: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro IO Tecnologias: " + e.getMessage());
        }
    }

    public Queue<Venda> lerVendas(String nomeArquivo, CentralCompradores cadComp, CatalogoTecnologias cadTec) {
        Queue<Venda> fila = new LinkedList<>();
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) return fila;

        try (Scanner scanner = new Scanner(arquivo, StandardCharsets.UTF_8)) {
            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if (linha.isEmpty()) continue;

                try {
                    String[] dados = linha.split(";");
                    long num = Long.parseLong(dados[0]);
                    String dataStr = dados[1];
                    long codComp = Long.parseLong(dados[2]);
                    long idTec = Long.parseLong(dados[3]);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date data = sdf.parse(dataStr);

                    Comprador c = cadComp.verificaCod(codComp);
                    Tecnologia t = cadTec.verificaId(idTec);

                    if (c != null && t != null) {
                        fila.add(new Venda(num, data, c, t));
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao ler venda: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro IO Vendas: " + e.getMessage());
        }
        return fila;
    }

    // ========================================================================
    // MÉTODOS DE SALVAR (Escrevem CSV igual ao formato do professor)
    // ========================================================================

    public boolean salvarSistema(ACMETech sistema, String nomeBase) {
        // Gera 3 arquivos separados usando o nome que o usuário digitou como prefixo
        // Ex: "TESTE-PARTICIPANTES.CSV"
        try {
            salvarParticipantesCSV(sistema, nomeBase + "-PARTICIPANTES.csv");
            salvarTecnologiasCSV(sistema, nomeBase + "-TECNOLOGIAS.csv");
            salvarVendasCSV(sistema, nomeBase + "-VENDAS.csv");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivos CSV: " + e.getMessage());
            return false;
        }
    }

    private void salvarParticipantesCSV(ACMETech sistema, String nomeArquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo), StandardCharsets.UTF_8))) {
            writer.println("cod;nome;tipo;dadoExtra1;dadoExtra2");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Fornecedor f : sistema.getCentralFornecimento().mostraFornecedores()) {
                writer.printf("%d;%s;1;%s;%s%n",
                        f.getCod(),
                        f.getNome(),
                        sdf.format(f.getFundacao()),
                        f.getArea().toString());
            }

            // 2. Escreve Compradores (Tipo 2)
            for (Comprador c : sistema.getCentralCompradores().mostrarCompradores()) {
                writer.printf("%d;%s;2;%s;%s%n",
                        c.getCod(),
                        c.getNome(),
                        c.getPais(),
                        c.getEmail());
            }
        }
    }

    private void salvarTecnologiasCSV(ACMETech sistema, String nomeArquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo), StandardCharsets.UTF_8))) {
            writer.println("id;modelo;descricao;valorBase;peso;temperatura;codFornecedor");

            for (Tecnologia t : sistema.getCatalogoTecnologias().mostrarTecnologias()) {
                long codForn = (t.getFornecedor() != null) ? t.getFornecedor().getCod() : -1;
                writer.printf("%d;%s;%s;%.2f;%.2f;%.2f;%d%n",
                        t.getId(),
                        t.getModelo(),
                        t.getDescricao(),
                        t.getValorBase(),
                        t.getPeso(),
                        t.getTemperatura(),
                        codForn);
            }
        }
    }

    private void salvarVendasCSV(ACMETech sistema, String nomeArquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo), StandardCharsets.UTF_8))) {
            writer.println("num;data;codComprador;idTecnologia");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Venda v : sistema.getCentralVendas().mostraVendas()) {
                writer.printf("%d;%s;%d;%d%n",
                        v.getNumeroVenda(),
                        sdf.format(v.getDataVenda()),
                        v.getComprador().getCod(),
                        v.getTecnologia().getId());
            }
        }
    }

    public boolean carregarSistema(ACMETech sistema, String nomeBase) {
        String arqPart = nomeBase + "-PARTICIPANTES.csv";
        String arqTec = nomeBase + "-TECNOLOGIAS.csv";
        String arqVendas = nomeBase + "-VENDAS.csv";

        File f = new File(arqPart);
        if (!f.exists()) {
            System.err.println("Arquivos de backup não encontrados para o nome: " + nomeBase);
            return false;
        }

        lerParticipantes(arqPart, sistema.getCentralFornecimento(), sistema.getCentralCompradores());
        lerTecnologias(arqTec, sistema.getCatalogoTecnologias(), sistema.getCentralFornecimento());
        Queue<Venda> fila = lerVendas(arqVendas, sistema.getCentralCompradores(), sistema.getCatalogoTecnologias());

        if (!fila.isEmpty()) {
            for (Venda v : fila) {
                if (sistema.getCentralVendas().verificaNumero(v.getNumeroVenda()) == null) {
                    v.calculaValorFinal();
                    sistema.getCentralVendas().cadastraVenda(v);
                    v.getComprador().incrementaVenda();
                }
            }
        }
        return true;
    }
}