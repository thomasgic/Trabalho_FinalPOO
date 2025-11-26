package entidades;

import java.io.Serializable;

public abstract class Participante implements Serializable {
    private long cod;
    private String nome;

    public Participante (long cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public Participante () {
        this.cod = 0;
        this.nome = "";
    }

    public long getCod () {
        return cod;
    }

    public void setCod (long cod) {
        this.cod = cod;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public abstract String geraDescricao();
}
