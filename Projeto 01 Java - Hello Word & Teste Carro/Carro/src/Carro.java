class carro {
    String cor;
    String modelo;
    
    double VelocidadeAtual;
    double VelocidadeMaxima;
    
    void liga() {
        System.out.println("O carro está ligado!");
    }
    
    void acelera(double quantidade) {
        double VelocidadeNova = this.VelocidadeAtual + quantidade;
        this.VelocidadeAtual = VelocidadeNova;
    }
    
    int pegaMarcha() {
        if (this.VelocidadeAtual < 0) {
            return -1;
        }
        if (this.VelocidadeAtual >= 0 && this.VelocidadeAtual < 40) {
            return 1;
        }
        if (this.VelocidadeAtual >= 40 && this.VelocidadeAtual < 80) {
            return 2;
        }
        return 3;
    }
}
    
class TestarCarro {
    public static void main(String[]args) {
        carro meucarro;
        meucarro = new carro();
        
        meucarro.cor = "Verde";
        meucarro.modelo = "Fusca";
        meucarro.VelocidadeAtual = 0;
        meucarro.VelocidadeMaxima = 80;
        
        meucarro. liga();
        
        meucarro.acelera(20);
        System.out.println(meucarro.VelocidadeAtual);
        
    }   
}
