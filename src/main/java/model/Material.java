package model;

public class Material {
    private int id;
    private String tipo;
    private String descricao;
    private double precoKg;

    public Material() {}

    public Material(String tipo, String descricao, double precoKg) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.precoKg = precoKg;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getPrecoKg() { return precoKg; }
    public void setPrecoKg(double precoKg) { this.precoKg = precoKg; }

    @Override
    public String toString() {
        return String.format("\n%d:\n%s\n%s\nR$ %.2f/kg\n", id, tipo, descricao, precoKg);
    }
}
