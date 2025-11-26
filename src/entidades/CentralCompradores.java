package entidades;
import java.util.ArrayList;
import java.util.Collections;
public class CentralCompradores {
    private ArrayList<Comprador> compradores;

    public CentralCompradores() {
        compradores = new ArrayList<>();
    }

    public boolean cadastraComprador(Comprador comprador) {
        compradores.add(comprador);
        Collections.sort(compradores);
        return true;
    }

    public Comprador verificaCod(Long cod) {
        for(Comprador c : compradores){
            if(c.getCod() == cod){
                return c;
            }
        }
        return null;
    }
    public ArrayList<Comprador> mostrarCompradores() {
        return compradores;

    }

    public void setCompradores(ArrayList<Comprador> compradores) {
        this.compradores = compradores;
        Collections.sort(compradores);
    }
}
