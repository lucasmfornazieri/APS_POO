public class Main {
    public static void main(String[] args) {
        Mapa mapinha = new Mapa();
        Homem homenzinho = new Homem();

        mapinha.mostrarMapa();

        mapinha.acharCaminhoAutomaticamente(homenzinho, homenzinho.getPosicaoX(), homenzinho.getPosicaoY());
    }
}
