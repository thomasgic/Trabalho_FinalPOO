package entidades;

import java.io.Serializable;
import java.util.Objects;

public class Comprador extends Participante implements Comparable<Comprador>, Serializable {
    private String cpf;
    private String email;

    public Comprador(long cod, String nome, String cpf, String email) {
        super(cod, nome);
        this.cpf = cpf;
        this.email = email;
    }

    public Comprador() {
        super();
        this.cpf = "";
        this.email = "";
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String geraDescricao() {
        return "nome: " + getNome() +
                ";código: " + getCod() +
                ";CPF: " + cpf +
                ";email: " + email;
    }

    @Override
    public int compareTo(Comprador outroComprador) {
        return Long.compare(this.getCod(), outroComprador.getCod());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comprador comprador = (Comprador) o;
        return getCod() == comprador.getCod();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCod());
    }

    @Override
    public String toString() {
        return String.format("Cód: %d | Nome: %s | CPF: %s | Email: %s",
                getCod(), getNome(), cpf, email);
    }
}