import java.util.Random;

public class Mapa {

    //Atributos
    private final char PAREDE = 'P';
    private final char VAZIO = '.';
    private final char SAIDA = 'S';
    private final char ENTRADA = 'E';
    private final char HOMEM_VAI = '+';
    
    private char[][] cenario;
    private int tamanhoMatriz = 0;

    //Construtor
    public Mapa(Homem homenzinho, int tamanho) {
        this.setTamanhoMatriz(tamanho);
        this.cenario = new char[tamanhoMatriz][tamanhoMatriz];
        this.criarLabirinto();
        this.criarEntradaHomenzinho(homenzinho);
    }

    //Métodos
    public void setTamanhoMatriz(int tamanhoMatriz) {
        this.tamanhoMatriz = tamanhoMatriz;
    }

    public void mostrarMapa() {
        for(int i = 0; i < this.tamanhoMatriz; i++){
            for(int j = 0; j < this.tamanhoMatriz; j++){
                System.out.print(this.cenario[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("******************");
        try {
            Thread.sleep(100);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void criarLabirinto() {
        Random ran = new Random();
        int numeroParedes = 20;
        int numeroSaida = 1;
        int paredeOuVazio = 0;
        for (int i = 0; i < tamanhoMatriz; i++) {
            for (int j = 0; j < tamanhoMatriz; j++) {
                paredeOuVazio = ran.nextInt(8);

                if(paredeOuVazio == 1 && numeroParedes > 0) {
                    this.cenario[i][j] = PAREDE;
                    numeroParedes--;
                }
                else if (paredeOuVazio == 5 && numeroSaida != 0) {
                    this.cenario[i][j] = SAIDA;
                    numeroSaida--;
                }
                else {
                    this.cenario[i][j] = VAZIO;
                }
            }
        }
    }

    public void criarEntradaHomenzinho(Homem homenzinho) {
        boolean validacaoNaMatriz = true;
        Random ran = new Random();
        while(validacaoNaMatriz) {
            int rand1 = ran.nextInt(this.tamanhoMatriz);
            int rand2 = ran.nextInt(this.tamanhoMatriz);

            if(this.cenario[rand1][rand2] != PAREDE && this.cenario[rand1][rand2] != SAIDA) {
                this.cenario[rand1][rand2] = ENTRADA;
                homenzinho.setPosicaoX(rand1);
                homenzinho.setPosicaoY(rand2);
                validacaoNaMatriz = false;
            }
        }
    }

    public boolean acharCaminhoAutomaticamente(Homem homenzinho, int posX, int posY) {
        mostrarMapa();
        if(posX < 0 || posX >= this.tamanhoMatriz || posY < 0 || posY >= this.tamanhoMatriz){
            return false;
        } else if(this.cenario[posX][posY] == SAIDA){
            System.out.println("Você encontrou a saída!");
            return true;
        } else if(this.cenario[posX][posY]  == PAREDE || this.cenario[posX][posY] == HOMEM_VAI){
            return false;
        } else {
            this.cenario[posX][posY] = HOMEM_VAI;
            if(acharCaminhoAutomaticamente(homenzinho, posX - 1, posY)){
                return true;
            } else if(acharCaminhoAutomaticamente(homenzinho, posX, posY + 1)){
                return true;
            } else if(acharCaminhoAutomaticamente(homenzinho, posX + 1, posY)){
                return true;
            } else if(acharCaminhoAutomaticamente(homenzinho, posX, posY - 1)){
                return true;
            } else{
                this.cenario[posX][posY] = VAZIO;
                return false;
            }
        }
    }
}
