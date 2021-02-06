package excessao;
import java.util.*;
class Caneta {
    
    //CÓDIGO CANETA
    String modelo;
    String cor;
    double ponta;
    int carga;
    boolean tampada;
    
    public Caneta(String model, String cor,double ponta,int carga) {
        this.modelo = model;
        this.cor = cor;
        this.ponta = ponta;
        this.carga = carga;
    }
    
    void status() {
        if (modelo == null || modelo.equals("")) {
            throw new RuntimeException("ERROR: Tipo de modelo incompatível!");
        } else {
            System.out.println("O modelo desta caneta é: " + this.modelo);
        }
        if (cor == null || cor.equals("")) {
            throw new RuntimeException("ERROR: Cor da caneta incompatível!");
        } else {
        System.out.println("Esta caneta é: " + this.cor);
        }
        System.out.println("A ponta desta caneta é: " + this.ponta);
        System.out.println("A caneta possui " + this.carga + "% de carga! ");
        System.out.println("Está tampada?: " + this.tampada);
    }
    
    void carregar(int carga) {
        this.carga = carga;
        System.out.println("Você substitui a carga para " + carga);
    }
    
    void rabiscar() {
        if (this.tampada == true){
            throw new RuntimeException("ERROR: A caneta está tampada!");
        } else {
            System.out.println("Estou Rabiscando! :D");
        }
    }
    
    void tampar() {
         if (this.tampada == true) {
             throw new RuntimeException("ERROR: A caneta já está tampada!");
         } else {
             this.tampada = true;
             System.out.println("A caneta foi tampada!");
         }
    }
    
    void destampar() {
        if (this.tampada == false) {
            throw new RuntimeException("ERROR: A caneta já está destampada!");
        } else {
            this.tampada = false;
            System.out.println("A caneta foi destampada!");
        }
    }
}

public class Excessao {
    public static void main(String[] args) {
        Caneta c1 = new Caneta(null,null, 0, 0);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            try {
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            
            if (ui[0].equals("stop")) {
                break;
            } else if (ui[0].equals("new")) {
                c1 = new Caneta(ui[1], ui[2],Float.parseFloat(ui[3]), Integer.parseInt(ui[4]));
            } else if (ui[0].equals("show")) {
                c1.status();
            } else if (ui[0].equals("rabiscar")) {
                c1.rabiscar();
            } else if (ui[0].equals("carregar")) {
                c1.carregar(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("tampar")) {
                c1.tampar();
            } else if (ui[0].equals("destampar")) {
                c1.destampar();
            } else {
                System.out.println("ERROR: Comando Inválido!");
            }
            }
            catch (RuntimeException x) {
                System.out.println(x.getMessage());
            }
        }
    }
}