package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda implements Comparable<Venda>, Serializable {
    private static final long serialVersionUID = 1L;
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

    // MÉTODO PRINCIPAL - CALCULA VALOR FINAL
    public double calculaValorFinal(int qtdVendasComprador) {
        if (tecnologia == null || tecnologia.getFornecedor() == null) {
            this.valorFinal = 0;
            return 0;
        }

        double valorBase = tecnologia.getValorBase();
        Area area = tecnologia.getFornecedor().getArea();

        // Acréscimo baseado na área do fornecedor
        double acrescimo = 0;
        switch (area) {
            case TI:
                acrescimo = 0.20; // 20%
                break;
            case ANDROIDES:
                acrescimo = 0.15; // 15%
                break;
            case EMERGENTE:
                acrescimo = 0.25; // 25%
                break;
            case ALIMENTOS:
                acrescimo = 0.10; // 10%
                break;
        }

        double valorComAcrescimo = valorBase * (1 + acrescimo);

        // Desconto cumulativo (1% por venda, máximo 10%)
        double descontoPorcentagem = Math.min(qtdVendasComprador * 0.01, 0.10);
        double valorComDesconto = valorComAcrescimo * (1 - descontoPorcentagem);

        this.valorFinal = valorComDesconto;
        return valorFinal;
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

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
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