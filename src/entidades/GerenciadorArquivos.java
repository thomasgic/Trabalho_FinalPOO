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

    public boolean lerParticipantes(String nomeArquivo, CentralFornecimento centralFornecimento, CentralCompradores centralCompradores) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) return false;
        try (Scanner scanner = new Scanner(arquivo, StandardCharsets.UTF_8)) {
            if (!scanner.hasNextLine()) return false;
            String cabecalho = scanner.nextLine().trim();
            if (!cabecalho.contains("tipo") && !cabecalho.contains("dadoExtra")) {
                return false;
            }

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if (linha.isEmpty()) continue;

                try {
                    String[] dados = linha.split(";");
                    long cod = Long.parseLong(dados[0]);
                    String nome = dados[1];
                    int tipo = Integer.parseInt(dados[2]);

                    if (tipo == 1) {
                        String dataString = dados[3];
                        String areaString = dados[4];
                        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
                        Date fundacao = dataFormatada.parse(dataString);
                        Area area = Area.valueOf(areaString.toUpperCase());

                        if (centralFornecimento.verificaCod(cod) == null) {
                            centralFornecimento.CadastraFornecedor(new Fornecedor(nome, cod, area, fundacao));
                        }
                    } else if (tipo == 2) {
                        String pais = dados[3];
                        String email = dados[4];

                        if (centralCompradores.verificaCod(cod) == null) {
                            centralCompradores.cadastraComprador(new Comprador(cod, nome, pais, email));
                        }
                    }
                } catch (Exception e) {
                    System.err.println("erro inesperado");
                }
            }
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public boolean lerTecnologias(String nomeArquivo, CatalogoTecnologias catalogoTecnologias, CentralFornecimento centralFornecimento) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) return false;

        try (Scanner scanner = new Scanner(arquivo, StandardCharsets.UTF_8)) {
            if (!scanner.hasNextLine()) return false;

            String cabecalho = scanner.nextLine().trim();
            if (!cabecalho.contains("peso") && !cabecalho.contains("temperatura") && !cabecalho.contains("valorBase")) return false;

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if (linha.isEmpty()) continue;

                try {
                    String[] dados = linha.split(";");
                    long id = Long.parseLong(dados[0]);

                    if (catalogoTecnologias.verificaId(id) == null) {
                        String modelo = dados[1];
                        String descricao = dados[2];
                        double valor = Double.parseDouble(dados[3]);
                        double peso = Double.parseDouble(dados[4]);
                        double temp = Double.parseDouble(dados[5]);
                        long codForn = Long.parseLong(dados[6]);

                        Tecnologia t = new Tecnologia(id, modelo, descricao, valor, peso, temp);
                        Fornecedor f = centralFornecimento.verificaCod(codForn);
                        if (f != null) t.defineFornecedor(f);

                        catalogoTecnologias.cadastrarTecnologia(t);
                    }
                } catch (Exception e) {
                    System.err.println("erro inesperado");
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Queue<Venda> lerVendas(String nomeArquivo, CentralCompradores centralCompradores, CatalogoTecnologias catalogoTecnologias) {
        Queue<Venda> fila = new LinkedList<>();
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) return fila;

        try (Scanner scanner = new Scanner(arquivo, StandardCharsets.UTF_8)) {
            if (!scanner.hasNextLine()) return fila;

            String cabecalho = scanner.nextLine().trim();
            if (!cabecalho.contains("codComprador") && !cabecalho.contains("idTecnologia")) return fila;

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                if (linha.isEmpty()) continue;

                try {
                    String[] dados = linha.split(";");
                    long num = Long.parseLong(dados[0]);
                    String dataString = dados[1];
                    long codComprador = Long.parseLong(dados[2]);
                    long idTecnologia = Long.parseLong(dados[3]);

                    SimpleDateFormat dataAjustada = new SimpleDateFormat("dd/MM/yyyy");
                    Date data = dataAjustada.parse(dataString);

                    Comprador comprador = centralCompradores.verificaCod(codComprador);
                    Tecnologia tecnologia = catalogoTecnologias.verificaId(idTecnologia);

                    if (comprador != null && tecnologia != null) {
                        fila.add(new Venda(num, data, comprador, tecnologia));
                    }
                } catch (Exception e) {
                    System.err.println("erro inesperado");
                }
            }
        } catch (IOException e) {
        }
        return fila;
    }

    public boolean salvarSistema(ACMETech acmeTech, String nomeBase) {
        try {
            salvarParticipantesCSV(acmeTech, nomeBase + "-PARTICIPANTES.csv");
            salvarTecnologiasCSV(acmeTech, nomeBase + "-TECNOLOGIAS.csv");
            salvarVendasCSV(acmeTech, nomeBase + "-VENDAS.csv");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivos CSV: " + e.getMessage());
            return false;
        }
    }

    private void salvarParticipantesCSV(ACMETech acmeTech, String nomeArquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo), StandardCharsets.UTF_8))) {
            writer.println("cod;nome;tipo;dadoExtra1;dadoExtra2");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (Fornecedor f : acmeTech.getCentralFornecimento().mostraFornecedores()) {
                writer.printf("%d;%s;1;%s;%s%n",
                        f.getCod(),
                        f.getNome(),
                        sdf.format(f.getFundacao()),
                        f.getArea().toString());
            }

            for (Comprador c : acmeTech.getCentralCompradores().mostrarCompradores()) {
                writer.printf("%d;%s;2;%s;%s%n",
                        c.getCod(),
                        c.getNome(),
                        c.getPais(),
                        c.getEmail());
            }
        }
    }

    private void salvarTecnologiasCSV(ACMETech acmeTech, String nomeArquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo), StandardCharsets.UTF_8))) {
            writer.println("id;modelo;descricao;valorBase;peso;temperatura;codFornecedor");

            for (Tecnologia t : acmeTech.getCatalogoTecnologias().mostrarTecnologias()) {
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

    private void salvarVendasCSV(ACMETech acmeTech, String nomeArquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(nomeArquivo), StandardCharsets.UTF_8))) {
            writer.println("num;data;codComprador;idTecnologia");
            SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

            for (Venda v : acmeTech.getCentralVendas().mostraVendas()) {
                writer.printf("%d;%s;%d;%d%n",
                        v.getNumeroVenda(),
                        dataFormatada.format(v.getDataVenda()),
                        v.getComprador().getCod(),
                        v.getTecnologia().getId());
            }
        }
    }

    public boolean carregarSistema(ACMETech acmeTech, String nomeBase) {
        boolean encontrouAlgumArquivo = false;
        String nomeArquivo = nomeBase + ".csv";
        File arquivo = new File(nomeArquivo);
        if (arquivo.exists()) {
            if (lerParticipantes(nomeArquivo, acmeTech.getCentralFornecimento(), acmeTech.getCentralCompradores())) {
                encontrouAlgumArquivo = true;
            }
            if (lerTecnologias(nomeArquivo, acmeTech.getCatalogoTecnologias(), acmeTech.getCentralFornecimento())) {
                encontrouAlgumArquivo = true;
            }
            Queue<Venda> fila = lerVendas(nomeArquivo, acmeTech.getCentralCompradores(), acmeTech.getCatalogoTecnologias());

            if (!fila.isEmpty()) {
                for (Venda v : fila) {
                    if (acmeTech.getCentralVendas().verificaNumero(v.getNumeroVenda()) == null) {
                        v.calculaValorFinal(v.getComprador().getQtdVendas());
                        acmeTech.getCentralVendas().cadastraVenda(v);
                        if (v.getComprador() != null) {
                            v.getComprador().incrementaVenda();
                        }
                    }
                }
                encontrouAlgumArquivo = true;
            }
        } else {
            System.err.println("Arquivo não encontrado: " + nomeArquivo);
        }

        return encontrouAlgumArquivo;
    }
}