package jogodavelha.view;

import jogodavelha.controller.ControleDoJogo;

import java.util.Scanner;

public class Tela {

    private ControleDoJogo controleDoJogo;

    public Tela() {
        controleDoJogo = new ControleDoJogo();

        menuApresentacao();
        controleDoJogo.marcarNoCampo(escolherLocalParaMarcar());
        controleDoJogo.amostraTabuleiro();
    }


    public void menuApresentacao() {
        Scanner leitor = new Scanner(System.in);
        String[] jogadores = new String[2];

        System.out.print(" *Bem vindo ao Jogo da Velha!*\n" +
                           "*~-~-~-~-~-~--~-~-~--~-~-~-~-~*\n\n"+
                           "Escolha no nome do Jogador que ficará com o símbolo:\n" +
                           "X -> ");
        jogadores[0] = leitor.nextLine();
        System.out.print("○ -> ");
        jogadores[1] = leitor.nextLine();
        System.out.println("----------------------------------------------------\n");
        controleDoJogo.atribuirNomes(jogadores[0],jogadores[1]);
    }

    public String escolherLocalParaMarcar(){
        Scanner leitor = new Scanner(System.in);
        String posicaoMarcada;
        String jogador = controleDoJogo.quemVaiJogar();
        System.out.println("Vez de -> "+jogador);
        System.out.println("Escolha o local do Tabuleiro que deseja marcar:\n");
        controleDoJogo.amostraTabuleiro();
        posicaoMarcada = leitor.nextLine();
        System.out.println("\nJogada feita!");

        return posicaoMarcada;
    }

    public String anuncioFimPartida(String ganhador){
        Scanner leitor = new Scanner(System.in);
        String resposta;
        System.out.println(ganhador);
        System.out.print("\nDeseja começar outra partida?\n-");
        resposta = leitor.nextLine();
        return resposta;
    }




}
