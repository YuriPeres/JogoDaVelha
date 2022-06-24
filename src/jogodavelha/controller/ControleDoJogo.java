package jogodavelha.controller;

import jogodavelha.model.Jogador;
import jogodavelha.model.Tabuleiro;

import java.util.Random;

public class ControleDoJogo {

    private Tabuleiro tabuleiro = new Tabuleiro();
    private Jogador jogadorX = new Jogador("X");
    private Jogador jogadorO = new Jogador("O");

    private boolean primeiraPartida = true;

    public void amostraTabuleiro() {
        Tabuleiro tabuleiro = new Tabuleiro();
        System.out.println(tabuleiro.toString());
    }

    public void atribuirNomes(String jogadorX, String jogadorO) {
        this.jogadorX.setNome(jogadorX);
        this.jogadorO.setNome(jogadorO);
        this.primeiraPartida = false;
    }

    public void marcarNoCampo(String posicao, Jogador jogadorDaVez) {
        int posicaoInt = Integer.parseInt(posicao);
        this.tabuleiro.getCampos().set(posicaoInt, jogadorDaVez.getSimbolo());

        //verificaSeGanhou(simbolo);
    }

    public String quemVaiJogar() {
        if (this.primeiraPartida) {
            Random aleatorio = new Random();
            int sorteio = aleatorio.nextInt(2)+1;
            if (sorteio == 1) { //1 vai ser para X
                this.jogadorX.setMinhaVez(true);
                this.jogadorO.setMinhaVez(false);
                return this.jogadorX.getNome();
            } else if (sorteio == 2) { //2 vai ser para O
                this.jogadorO.setMinhaVez(true);
                this.jogadorX.setMinhaVez(false);
                return this.jogadorO.getNome();
            }
        } else {
            if (jogadorO.isMinhaVez()) { //Se o último a jogar foi O, será X o próximo.
                this.jogadorX.setMinhaVez(true);
                this.jogadorO.setMinhaVez(false);
                return this.jogadorX.getNome();
            } else if (jogadorX.isMinhaVez()) { //Se o último a jogar foi X, será O o próximo.
                this.jogadorO.setMinhaVez(true);
                this.jogadorX.setMinhaVez(false);
                return this.jogadorO.getNome();
            }
        }
        return null;
    }




}
