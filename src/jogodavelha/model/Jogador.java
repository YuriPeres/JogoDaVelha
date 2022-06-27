package jogodavelha.model;

public class Jogador {

    private String nome;
    private String simbolo;
    private int pontos = 0;
    private int pontosSeguidos = 0;
    private boolean minhaVez = false;

    public Jogador() {

    }

    public Jogador(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public boolean isMinhaVez() {
        return minhaVez;
    }

    public void setMinhaVez(boolean minhaVez) {
        this.minhaVez = minhaVez;
    }

    public int getPontosSeguidos() {
        return pontosSeguidos;
    }

    public void setPontosSeguidos(int pontosSeguidos) {
        this.pontosSeguidos = pontosSeguidos;
    }
}
