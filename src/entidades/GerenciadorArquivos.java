package entidades;

import java.io.*;
import java.util.ArrayList;

public class GerenciadorArquivos {
    private final String ARQUIVO_FORNECEDORES = "fornecedores.dat";
    private final String ARQUIVO_TECNOLOGIAS = "tecnologias.dat";
    private final String ARQUIVO_COMPRADORES = "compradores.dat";
    private final String ARQUIVO_VENDAS = "vendas.dat";

    // Salvar Fornecedores
    public boolean salvarFornecedores(ArrayList<Fornecedor> fornecedores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_FORNECEDORES))) {
            oos.writeObject(fornecedores);
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar fornecedores: " + e.getMessage());
            return false;
        }
    }

    // Carregar Fornecedores
    @SuppressWarnings("unchecked")
    public ArrayList<Fornecedor> carregarFornecedores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_FORNECEDORES))) {
            return (ArrayList<Fornecedor>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de fornecedores não encontrado. Será criado ao salvar.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar fornecedores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Salvar Tecnologias
    public boolean salvarTecnologias(ArrayList<Tecnologia> tecnologias) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_TECNOLOGIAS))) {
            oos.writeObject(tecnologias);
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar tecnologias: " + e.getMessage());
            return false;
        }
    }

    // Carregar Tecnologias
    @SuppressWarnings("unchecked")
    public ArrayList<Tecnologia> carregarTecnologias() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_TECNOLOGIAS))) {
            return (ArrayList<Tecnologia>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de tecnologias não encontrado. Será criado ao salvar.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar tecnologias: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Salvar Compradores
    public boolean salvarCompradores(ArrayList<Comprador> compradores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_COMPRADORES))) {
            oos.writeObject(compradores);
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar compradores: " + e.getMessage());
            return false;
        }
    }

    // Carregar Compradores
    @SuppressWarnings("unchecked")
    public ArrayList<Comprador> carregarCompradores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_COMPRADORES))) {
            return (ArrayList<Comprador>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de compradores não encontrado. Será criado ao salvar.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar compradores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Salvar Vendas
    public boolean salvarVendas(ArrayList<Venda> vendas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_VENDAS))) {
            oos.writeObject(vendas);
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar vendas: " + e.getMessage());
            return false;
        }
    }

    // Carregar Vendas
    @SuppressWarnings("unchecked")
    public ArrayList<Venda> carregarVendas() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_VENDAS))) {
            return (ArrayList<Venda>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de vendas não encontrado. Será criado ao salvar.");
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar vendas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Salvar TUDO
    public boolean salvarTodosDados(CentralFornecimento centralFornecimento,
                                           CatalogoTecnologias catalogoTecnologias,
                                           CentralCompradores centralCompradores,
                                           CentralVendas centralVendas) {
        boolean sucesso = true;
        sucesso &= salvarFornecedores(centralFornecimento.mostraFornecedores());
        sucesso &= salvarTecnologias(catalogoTecnologias.mostrarTecnologias());
        sucesso &= salvarCompradores(centralCompradores.mostrarCompradores());
        sucesso &= salvarVendas(centralVendas.mostraVendas());
        return sucesso;
    }
}