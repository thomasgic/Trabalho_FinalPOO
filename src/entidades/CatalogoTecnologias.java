package entidades;

import java.util.ArrayList;
import java.util.Collections;

public class CatalogoTecnologias {
    private ArrayList<Tecnologia> tecnologias;

    public CatalogoTecnologias() {
          tecnologias = new ArrayList<>();
    }
    public boolean cadastrarTecnologia(Tecnologia tecnologia) {
        tecnologias.add(tecnologia);
        return true;
    }
    public Tecnologia verificaId(Long id) {
        for (Tecnologia tecnologia : tecnologias) {
            if (tecnologia.getId() == id) {
                return tecnologia;
            }
        }
        return null;
    }
    public ArrayList<Tecnologia> mostrarTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(ArrayList<Tecnologia> tecnologias) {
        this.tecnologias = tecnologias;
    }

    public ArrayList<Tecnologia> mostrarTecnologiasPorFornecedores(Long codFornecedor) {
        ArrayList<Tecnologia> resultado = new ArrayList<>();
        for (Tecnologia t : tecnologias) {
            if(t.getFornecedor() != null && t.getFornecedor().getCod() == codFornecedor) {
                resultado.add(t);
            }
        }
        return resultado;
    }
    public Tecnologia tecnologiaMaiorValor(){
        if(tecnologias.isEmpty()){
            return null;
        }
        Tecnologia maior = tecnologias.get(0);
        for(Tecnologia t : tecnologias){
            if(t.getValorBase() > maior.getValorBase()){
                maior = t;
            }
        }
        return maior;
    }
}
