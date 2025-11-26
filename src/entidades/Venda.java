package entidades;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda implements Comparable<Venda>, Serializable {
    private long numeroVenda;
    private Date dataVenda;
    private Comprador comprador;
    private Tecnologia tecnologia;
    private double valorFinal;

    public Venda(long numeroVenda, Date dataVenda, Comprador comprador, Tecnologia tecnologia) {
        this.comprador = comprador;
        this.tecnologia = tecnologia;
        this.numeroVenda = numeroVenda;
        this.dataVenda = dataVenda;
        this.valorFinal = tecnologia.getValorBase();
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    public void setNumeroVenda(long numeroVenda) {
        this.numeroVenda = numeroVenda;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public void setTecnologia(Tecnologia tecnologia) {
        this.tecnologia = tecnologia;
        this.valorFinal = tecnologia.getValorBase();
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public long getNumeroVenda() {
        return numeroVenda;
    }
    public String geraDescricaoVenda() {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String data = formatoData.format(dataVenda);
        return "Numero: " + numeroVenda +
                ";" + "Data: " + data +
                ";" + "Comprador: " +  comprador.getNome() +
                ";" + "Tecnologia: " + tecnologia.getModelo() +
                ";" + "Valor: R$" + String.format("%.2f", valorFinal);
    }
    @Override
    public String toString() {
        return geraDescricaoVenda();
    }
    @Override
    public int compareTo(Venda outraVenda){
        return Long.compare(numeroVenda, outraVenda.numeroVenda);
    }
}
