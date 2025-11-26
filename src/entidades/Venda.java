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
        this.numeroVenda = numeroVenda;
        this.dataVenda = dataVenda;
        this.comprador = comprador;
        this.tecnologia = tecnologia;
        this.valorFinal = 0; // Será calculado depois
    }

    public Venda() {
        this.numeroVenda = 0;
        this.dataVenda = null;
        this.comprador = null;
        this.tecnologia = null;
        this.valorFinal = 0.0;
    }

    public double calculaValorFinal() {
        double valorBase = tecnologia.getValorBase();
        double valorComAcrescimo = 0.0;
        if (tecnologia.getFornecedor() != null) {
            Area area = tecnologia.getFornecedor().getArea();

            switch (area) {
                case TI:
                    valorComAcrescimo = valorBase * 1.20;
                    break;
                case ANDROIDES:
                    valorComAcrescimo = valorBase * 1.15;
                    break;
                case EMERGENTE:
                    valorComAcrescimo = valorBase * 1.25;
                    break;
                case ALIMENTOS:
                    valorComAcrescimo = valorBase * 1.10;
                    break;
                default:
                    valorComAcrescimo = valorBase;
            }
        } else {
            valorComAcrescimo = valorBase;
        }
        int qtdVendasAnteriores = comprador.getQtdVendas();

        double percentualDesconto = qtdVendasAnteriores * 0.01;
        if (percentualDesconto > 0.10) {
            percentualDesconto = 0.10;
        }
        double valorDoDesconto = valorComAcrescimo * percentualDesconto;
        this.valorFinal = valorComAcrescimo - valorDoDesconto;
        return this.valorFinal;
    }

    public long getNumeroVenda() {
        return numeroVenda;
    }

    public void setNumeroVenda(long numeroVenda) {
        this.numeroVenda = numeroVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Tecnologia getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(Tecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public String geraDescricaoVenda() {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String data = formatoData.format(dataVenda);
        return numeroVenda + ";" + data + ";" +
                comprador.getCod() + ";" +
                tecnologia.getId() + ";" +
                String.format("%.2f", valorFinal);
    }

    @Override
    public int compareTo(Venda outraVenda) {
        // ORDEM DECRESCENTE (maior número primeiro)
        return Long.compare(outraVenda.numeroVenda, this.numeroVenda);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String data = formatoData.format(dataVenda);
        return "Número: " + numeroVenda +
                "; Data: " + data +
                "; Comprador: " + comprador.getNome() +
                "; Tecnologia: " + tecnologia.getModelo() +
                "; Valor: R$ " + String.format("%.2f", valorFinal);
    }
}