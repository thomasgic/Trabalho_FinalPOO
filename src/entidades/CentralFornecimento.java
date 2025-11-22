package entidades;

import java.util.ArrayList;
import java.util.Collections;

public class CentralFornecimento {
    private ArrayList<Fornecedor> fornecedores;

    public CentralFornecimento(){
        fornecedores = new ArrayList<>();
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

    public ArrayList<Fornecedor> mostraFornecedores(){
        Collections.sort(fornecedores);
        return fornecedores;
        }
    }
