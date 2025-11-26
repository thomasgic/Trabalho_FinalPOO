package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fornecedor extends Participante implements Comparable<Fornecedor>, Serializable {
    private Area area;
    private Date fundacao;


    public Fornecedor (String nome, long cod, Area area, Date fundacao) {
        super(cod, nome);
        this.area = area;
        this.fundacao = fundacao;
    }

    public Fornecedor () {
        super();
        this.area = null;
        this.fundacao = null;
    }

    public void setNome(String nome){
        super.setNome(nome);
    }

    public void setCod(long cod){
        super.setCod(cod);
    }

    public Area getArea () {
        return area;
    }

    public void setArea (Area area) {
        this.area = area;
    }

    public Date getFundacao () {
        return fundacao;
    }

    public void setFundacao (Date fundacao) {
        this.fundacao = fundacao;
    }

    @Override
    public String geraDescricao () {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String dataFundacao = formatoData.format(fundacao);
        return "nome: " + getNome() +
               ";código: " + getCod() +
               ";área: " + area +
               ";data de fundação: " + dataFundacao;

    }

    @Override
    public int compareTo(Fornecedor outroFornecedor) {
        // Retorna negativo se for menor, zero se igual, positivo se maior
        return Long.compare(getCod(), outroFornecedor.getCod());
    }
}

