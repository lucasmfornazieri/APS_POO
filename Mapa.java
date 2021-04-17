public class Mapa {

    //Atributos
    private final char PAREDE = 'P';
    private final char VAZIO = '.';
    private final char SAIDA = 'S';
    private final char ENTRADA = 'E';
    private final char HOMEM_VAI = '+';
    
    private char[][] cenario;
    private int tamanhoMatriz = 0;

    // private int posXAux;
    // private int posYAux;

    //Construtor
    public Mapa() {
        this.cenario = new char[][]{
            {VAZIO,   VAZIO,  PAREDE, VAZIO,  SAIDA},
            {VAZIO,  PAREDE,   VAZIO, VAZIO,   VAZIO},
            {VAZIO,   VAZIO,  VAZIO, VAZIO,   VAZIO},
            {VAZIO,  PAREDE,   PAREDE, VAZIO,  PAREDE},
            {ENTRADA,  PAREDE,   VAZIO, VAZIO,  VAZIO}     
        };
        this.tamanhoMatriz = 5;
        // this.posXAux = 4;
        // this.posYAux = 0;
    }

    //Métodos
    public void mostrarMapa() {
        for(int i = 0; i < this.tamanhoMatriz; i++){
            for(int j = 0; j < this.tamanhoMatriz; j++){
                System.out.print(this.cenario[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("******************");
        try {
            Thread.sleep(500);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
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
                // mostrarMapa();
                return true;
            } else if(acharCaminhoAutomaticamente(homenzinho, posX, posY + 1)){
                // mostrarMapa();
                return true;
            } else if(acharCaminhoAutomaticamente(homenzinho, posX + 1, posY)){
                // mostrarMapa();
                return true;
            } else if(acharCaminhoAutomaticamente(homenzinho, posX, posY - 1)){
                // mostrarMapa();
                return true;
            } else{
                this.cenario[posX][posY] = VAZIO;
                return false;
            }
        }
    }
}
