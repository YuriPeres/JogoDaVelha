package jogodavelha.controller;

import jogodavelha.model.Jogador;
import jogodavelha.model.Tabuleiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ControleDoJogo {

    private Tabuleiro tabuleiro;
    private Jogador jogadorX;
    private Jogador jogadorO;
    private boolean primeiraPartida;
    private boolean fimDePartida;
    private int turnos;


    public ControleDoJogo() {
        tabuleiro = new Tabuleiro();
        jogadorX = new Jogador("X");
        jogadorO = new Jogador("○");
        primeiraPartida = true;
        fimDePartida = false;
        turnos = 0;
    }

    public String mostraTabuleiro() {
        String tabuleiro = "";
        tabuleiro += jogadorX.getNome()+"{ X }: "+jogadorX.getPontos();
        tabuleiro += " | ";
        tabuleiro += jogadorO.getNome()+"{ ○ }: "+jogadorO.getPontos();
        tabuleiro += "\n---------------------------------\n";
        tabuleiro += this.tabuleiro.toString();
        tabuleiro += "\n-=-=-=-=-=-==-=-=-=-==-=--==-=--=-";
        return tabuleiro;
    }

    public void atribuirNomes(String jogadorX, String jogadorO) {
        this.jogadorX.setNome(jogadorX);
        this.jogadorO.setNome(jogadorO);

    }

    public void marcarNoCampo(String posicao) {
        if (this.jogadorX.isMinhaVez()) {
            int posicaoInt = Integer.parseInt(posicao) - 1;
            this.tabuleiro.getCampos().set(posicaoInt, this.jogadorX.getSimbolo());
        } else if (this.jogadorO.isMinhaVez()) {
            int posicaoInt = Integer.parseInt(posicao) - 1;
            this.tabuleiro.getCampos().set(posicaoInt, this.jogadorO.getSimbolo());
        } else {
            System.out.println("marcarNoCampo() -> Não deveria cair aqui");
        }
        //turnos++;

    }

    public String quemVaiJogar() {
        if (this.primeiraPartida) {
            Random aleatorio = new Random();
            int sorteio = aleatorio.nextInt(2) + 1;
            if (sorteio == 1) { //1 vai ser para X
                this.jogadorX.setMinhaVez(true);
                this.jogadorO.setMinhaVez(false);
                this.primeiraPartida = false;
                return this.jogadorX.getNome();
            } else if (sorteio == 2) { //2 vai ser para O
                this.jogadorO.setMinhaVez(true);
                this.jogadorX.setMinhaVez(false);
                this.primeiraPartida = false;
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
        return "quemVaiJogar() -> não deveria cair aqui";
    }

    public String verificaSeGanhou() {
        int somadorDiagonal = 4;
        String quemGanhou;
        ArrayList<String> localMarcado = tabuleiro.getCampos();

        if (turnos < 4) {
            //Só pula, não tem como ninguém ter ganho
        } else if (turnos < 9) {
            //Verifica ganhar na horizontal
            for (int i = 0; i < 9; i += 3) {
                if (localMarcado.get(i).contains(localMarcado.get(i + 1)) && localMarcado.get(i).contains(localMarcado.get(i + 2))) {
                    //System.out.println("Jogador ganhou por linha horizontal!");
                    quemGanhou = verificaQuemGanhou(localMarcado.get(i));
                    return quemGanhou;
                }
            }
            //Verifica ganhar vertical
            for (int j = 0; j < 3; j++) {
                if (localMarcado.get(j).contains(localMarcado.get(j + 3)) && localMarcado.get(j).contains(localMarcado.get(j + 6))) {
                    //System.out.println("Jogador ganhou por linha vertical!");
                    quemGanhou = verificaQuemGanhou(localMarcado.get(j));
                    return quemGanhou;
                }
            }
            //Verifica ganhar pelas diagonais
            for (int k = 0; k <= 2; k += 2) {
                if (localMarcado.get(k).contains(localMarcado.get(k + somadorDiagonal)) && localMarcado.get(k).contains(localMarcado.
                        get(k + (somadorDiagonal * 2)))) {
                    //System.out.println("Jogador ganhou por linha diagonal!");
                    quemGanhou = verificaQuemGanhou(localMarcado.get(k));
                    return quemGanhou;
                }
                somadorDiagonal = somadorDiagonal / 2;
            }
        }
        else {
            return "empate";
        }


        return "continua"; //Caso o jogo siga
    }

    public String verificaQuemGanhou(String simbolo) {
        /*
        Vai acrescentar ponto para jogador
        vai acumulando se ganhou seguida, se perder reseta
         */
        if (simbolo.contains(jogadorX.getSimbolo())) {
            jogadorX.setPontos(jogadorX.getPontos() + 1);
            jogadorX.setPontosSeguidos(jogadorX.getPontosSeguidos() + 1);
            jogadorO.setPontosSeguidos(0);
            return jogadorX.getNome();
        } else if (simbolo.contains((jogadorO.getSimbolo()))) {
            jogadorO.setPontos(jogadorO.getPontos() + 1);
            jogadorO.setPontosSeguidos(jogadorO.getPontosSeguidos() + 1);
            jogadorX.setPontosSeguidos(0);
            return jogadorO.getNome();
        }
        return "verificaQuemGanhou() -> Deu erro aqui.";
    }

    public String fimDePartida(String quemGanhou) {
        if(quemGanhou.contains("continua")){
            //ninguém ganhou
            return "continua";
        }
        else if(quemGanhou.contains(jogadorX.getNome())){
            this.fimDePartida = true;
            if(jogadorX.getPontosSeguidos()==3) {
                jogadorX.setPontosSeguidos(0);
                return "Parabéns "+jogadorX.getNome()+" você ganhou 3 partidas seguidas!";
            }
            return "Parabéns "+jogadorX.getNome()+" você ganhou!";
        }
        else if(quemGanhou.contains(jogadorO.getNome())){
            this.fimDePartida = true;
            if(jogadorO.getPontosSeguidos()==3) {
                jogadorO.setPontosSeguidos(0);
                return "Parabéns "+jogadorO.getNome()+" você ganhou 3 partidas seguidas!";
            }
            return "Parabéns "+jogadorO.getNome()+" você ganhou!";
        }
        else if(quemGanhou.contains("empate")){
            this.fimDePartida = true;
            return jogadorX.getNome()+" empatou com "+jogadorO.getNome()+"!";
        }
        return "fimDePartida() -> Deu algum erro aqui => "+quemGanhou;
    }



    public boolean jogarOutraPartida(String jogaOutra) {
        if(jogaOutra.equals("n")) {
            System.exit(0);
            return true;
        }
        else if(jogaOutra.equals("s")) {
            this.turnos = 0;
            this.fimDePartida = false;
            ArrayList<String> limparCampos = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","9"));
            this.tabuleiro.setCampos(limparCampos);
            return false;
        }
        return false;
    }

    public boolean isFimDePartida() {
        return fimDePartida;
    }

    public void passarDosTurnos(){
        turnos++;
    }
}
