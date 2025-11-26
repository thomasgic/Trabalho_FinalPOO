package entidades;

import java.io.Serializable;

public class Tecnologia implements Serializable {
    private long id;
    private Fornecedor fornecedor;
    private String Modelo;
    private String descricao;
    private double valorBase;
    private double peso;
    private double temperatura;
    private boolean vendida;

    public Tecnologia (long id, String modelo, String descricao, double valorBase, double peso, double temperatura) {
        this.id = id;
        Modelo = modelo;
        this.descricao = descricao;
        this.valorBase = valorBase;
        this.peso = peso;
        this.temperatura = temperatura;
        this.vendida = false;
    }

    public long getId () {
        return id;
    }
    public boolean isVendida() {
        return vendida;
    }

    public void setVendida(boolean vendida) {
        this.vendida = vendida;
    }
    public void setId (long id) {
        this.id = id;
    }

    public Fornecedor getFornecedor () {
        return fornecedor;
    }

    public void setFornecedor (Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getModelo () {
        return Modelo;
    }

    public void setModelo (String modelo) {
        Modelo = modelo;
    }

    public String getDescricao () {
        return descricao;
    }

    public void setDescricao (String descricao) {
        this.descricao = descricao;
    }

    public double getTemperatura () {
        return temperatura;
    }

    public void setTemperatura (double temperatura) {
        this.temperatura = temperatura;
    }

    public double getPeso () {
        return peso;
    }

    public void setPeso (double peso) {
        this.peso = peso;
    }

    public double getValorBase () {
        return valorBase;
    }

    public void setValorBase (double valorBase) {
        this.valorBase = valorBase;
    }

    public void defineFornecedor(Fornecedor f){
        this.fornecedor = f;
    }

    @Override
    public String toString () {
        return "id: " + id +
                ";fornecedor: " + fornecedor +
                ";Modelo: " + Modelo +
                ";descricao: " + descricao +
                ";valorBase: " + valorBase +
                ";peso: " + peso +
                ";temperatura: " + temperatura;
    }
}
