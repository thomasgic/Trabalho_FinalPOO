package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CentralVendas implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Venda> vendas;

    public CentralVendas() {
        vendas = new ArrayList<>();
    }

    public boolean cadastraVenda(Venda venda) {
        
        int qtdVendasComprador = 0;
        for (Venda v : vendas) {
            if (v.getComprador().getCod() == venda.getComprador().getCod()) {
                qtdVendasComprador++;
            }
        }


        venda.calculaValorFinal(qtdVendasComprador);


        venda.getTecnologia().setVendida(true);

        vendas.add(venda);
        Collections.sort(vendas);
        return true;
    }

    public Venda verificaNumero(long numero) {
        for (Venda v : vendas) {
            if (v.getNumeroVenda() == numero) {
                return v;
            }
        }
        return null;
    }

    public boolean removeVenda(long numero) {
        Venda venda = verificaNumero(numero);
        if (venda != null) {
            // Desmarcar tecnologia como vendida
            venda.getTecnologia().setVendida(false);
            vendas.remove(venda);
            return true;
        }
        return false;
    }

    public ArrayList<Venda> mostraVendas() {
        Collections.sort(vendas);
        return vendas;
    }

    public Venda vendaMaiorValor() {
        if (vendas.isEmpty()) return null;

        Venda maior = vendas.get(0);
        for (Venda v : vendas) {
            if (v.getValorFinal() > maior.getValorFinal()) {
                maior = v;
            }
        }
        return maior;
    }

    public Comprador compradorComMaisVendas() {
        if (vendas.isEmpty()) return null;

        Map<Long, Integer> contagem = new HashMap<>();

        for (Venda v : vendas) {
            long codComprador = v.getComprador().getCod();
            contagem.put(codComprador, contagem.getOrDefault(codComprador, 0) + 1);
        }

        long codMaior = -1;
        int maxVendas = 0;

        for (Map.Entry<Long, Integer> entry : contagem.entrySet()) {
            if (entry.getValue() > maxVendas) {
                maxVendas = entry.getValue();
                codMaior = entry.getKey();
            }
        }

        for (Venda v : vendas) {
            if (v.getComprador().getCod() == codMaior) {
                return v.getComprador();
            }
        }

        return null;
    }

    public void setVendas(ArrayList<Venda> vendas) {
        this.vendas = vendas;
        Collections.sort(this.vendas);
    }
}