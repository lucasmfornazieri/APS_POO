public class Main {
    public static void main(String[] args) {
        Homem homenzinho = new Homem();
        Mapa mapinha = new Mapa(homenzinho, 20);
        mapinha.mostrarMapa();

        mapinha.acharCaminhoAutomaticamente(homenzinho, homenzinho.getPosicaoX(), homenzinho.getPosicaoY());

    }
}
