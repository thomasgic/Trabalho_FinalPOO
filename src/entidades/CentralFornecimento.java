package entidades;

import java.util.ArrayList;
import java.util.Collections;

public class CentralFornecimento {
    private ArrayList<Fornecedor> fornecedores;
    private CentralCompradores centralCompradores;

    public CentralFornecimento(){
        fornecedores = new ArrayList<>();

        centralCompradores = new CentralCompradores();
    }

    public boolean CadastraFornecedor(Fornecedor fornecedor){
        fornecedores.add(fornecedor);
        Collections.sort(fornecedores);
        return true;
    }

    public Fornecedor verificaCod(long cod){
        Fornecedor fornecedorCod = null;
        for(Fornecedor f: fornecedores){
            if (f.getCod() == cod){
                fornecedorCod = f;
            }
        }
        return fornecedorCod;
    }

    public void setFornecedores(ArrayList<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
        Collections.sort(fornecedores);
    }

    public ArrayList<Fornecedor> mostraFornecedores(){
        Collections.sort(fornecedores);
        return fornecedores;
        }
    }
