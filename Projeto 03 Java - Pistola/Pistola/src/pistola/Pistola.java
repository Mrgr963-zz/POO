package pistola;
import java.util.*;
class Cartucho {
    int munição;
    int munição_max;
    float calibre;
    boolean usando;
    
    Cartucho (int munição, int munição_max, float calibre) {
        this.munição = munição;
        this.calibre = calibre;
        this.munição_max = munição_max;
        this.usando = false;
    }
    
    public String toString() {
        return "Munição: " + munição + "|" + munição_max + " | " + " Calibre da Munição: " + calibre;
    }
    
    void CarregarBalas(int balas, float calibre) {
        if (this.usando == true) {
            System.out.println("Remova o cartucho da arma para colocar nova munição!");
        } else if (this.calibre != calibre) {
            System.out.println("A calibragem da munição não é suportada pelo cartucho!");
        } else if ((balas + munição) > munição_max) {
            System.out.println("O cartucho não cabe tanta munição!");
        } else {
            munição = munição + balas;
            System.out.println("Você colocou " + balas + " balas no cartucho!");
        }
    }
}

public class Pistola {
    float calibre;
    Cartucho magazine;
    
    Pistola (float calibre) {
        this.calibre = calibre;
        this.magazine = null;
    }
    
    public String toString() {
        return "Calibre da Arma: " + calibre + " Cartucho: " + magazine;
    }
    
    void RecarregarArma(Cartucho other) {
        if (magazine != null) {
            System.out.println("Você já tem um cartucho carregado!");
        } else {
            if (this.calibre == other.calibre) {
                this.magazine = other;
                magazine.usando = true;
                System.out.println("Você recarregou a arma com o cartucho!");
            } else {
                System.out.println("Os calibres são diferentes! Operação impossivel!");
            }
        }
    }
    
    void Descarregar() {
        Cartucho aux = magazine;
        if (magazine != null) {
            magazine.usando = false;
            magazine = null;
            System.out.println("Você remove o cartucho da arma!");
        } else {
            System.out.println("Sua arma já está descarregada!");
        }
    }
    
    void Atirar(int tiros) {
        if (magazine != null) {
            if (tiros > magazine.munição) {
                System.out.println("Você não tem munição suficiente pra isso!");
            } else {
                for (int i = 0; i < tiros; i++) {
                    System.out.println("POW!");
                }
                magazine.munição -= tiros;
                System.out.println("Você atirou " + tiros + " vezes!");
            }
        } else {
            System.out.println("Você não possui um cartucho carregado!");
        }
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cartucho c1 = new Cartucho (0,0,0);
        Pistola arma = new Pistola(0);
        
        while (true) {
            String line = scanner.nextLine();
            String[] ui = line.split(" ");      
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("createCartucho")) {
                c1 = new Cartucho(Integer.parseInt(ui[1]),Integer.parseInt(ui[2]), Float.parseFloat(ui[3]) );
            } else if (ui[0].equals("createArma")) {
                arma = new Pistola(Float.parseFloat(ui[1]));
            } else if (ui[0].equals("statusCartucho")) {
                System.out.println(c1);
            } else if (ui[0].equals("statusArma")){
                System.out.println(arma);
            } else if (ui[0].equals("recarregarCartucho")) {
                c1.CarregarBalas(Integer.parseInt(ui[1]), Float.parseFloat(ui[2]));
            } else if (ui[0].equals("recarregarArma")) {
                arma.RecarregarArma(c1);
            } else if (ui[0].equals("descarregar")) {
                arma.Descarregar();
            } else if (ui[0].equals("atirar")) {
                arma.Atirar(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("help")) {
                System.out.println("COMANDOS DISPONIVEIS:");
                System.out.println("");
                System.out.println("createCartucho: CRIA UM CARTUCHO DE UMA DETERMINADA MUNIÇÃO, MUNIÇÃO MÁXIMA E CALIBRAGEM, EXEMPLO: createCartucho 5 5 .45.");
                System.out.println("createArma: CRIA UMA ARMA DE UMA DETERMINADA CALIBRAGEM, EXEMPLO: createArma .45.");
                System.out.println("statusArma: MOSTRA AS INFORMAÇÕES DA ARMA ATUAL.");
                System.out.println("statusCartucho: MOSTA AS INFORMAÇÕES DO CARTUCHO ATUAL.");
                System.out.println("recarregarCartucho: CARREGA O CARTUCHO COM UMA QUANTIDADE DE MUNIÇÃO!");
                System.out.println("recarregarArma: CARREGA A ARMA COM O CARTUCHO!");
                System.out.println("descarregar: REMOVE O CARTUCHO DA ARMA.");
                System.out.println("atirar: ATIRA UMA QUANTIDADE DE MUNIÇÃO, EXEMPLO: atirar 5");
            } else {
                System.out.println("Comando Invalido!");
            }
        }
        scanner.close();
    }
}
