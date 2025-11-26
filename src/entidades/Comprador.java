package entidades;

import java.io.Serializable;
import java.util.Objects;

public class Comprador extends Participante implements Comparable<Comprador>, Serializable {
    private static final long serialVersionUID = 1L;
    private String pais;
    private String email;

    public Comprador(long cod, String nome, String pais, String email) {
        super(cod, nome);
        this.pais = pais;
        this.email = email;
    }

    public Comprador() {
        super();
        this.pais = "";
        this.email = "";
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String geraDescricao() {
        return getNome() + ";" + getCod() + ";" + pais + ";" + email;
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
        return String.format("Cód: %d | Nome: %s | País: %s | Email: %s",
                getCod(), getNome(), pais, email);
    }
}