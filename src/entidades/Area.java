package entidades;

public enum Area {
    TI("TI"), ANDROIDES("Androides"), EMERGENTE("Emergente"), ALIMENTOS("Alimentos");

    private String nome;

    private Area (String nome){
            this.nome = nome;
    }

    @Override
    public String toString () {
        return  nome;
    }
}
