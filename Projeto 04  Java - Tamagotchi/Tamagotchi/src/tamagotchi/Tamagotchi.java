package tamagotchi;
import java.util.*;
public class Tamagotchi {
    
    public class Bixinho{
     private int energy;
     private int energyMax;
     private int sacer;
     private int sacerMax;
     private int clean;
     private int cleanMax;
     private int dimas;
     private int idade;
     private int idadeMax;
     private boolean vivo;
     
     public Bixinho (int energyMax, int sacerMax,int cleanMax, int idadeMax) {
        this.energy = energyMax;
        this.energyMax = energyMax;
        this.sacer = sacerMax;
        this.sacerMax = sacerMax;
        this.clean = cleanMax;
        this.cleanMax = cleanMax;
        this.dimas = 0;
        this.idade = 0;
        this.idadeMax = idadeMax;
        this.vivo = true;
    }
     
     void setEnergy(int valor) {
         this.energy = valor;
         if (energy <= 0) {
             this.energy = 0;
             this.vivo = false;
          System.out.println("Seu Tamagotchi morreu de cansaço!");   
         }
         if (energy > energyMax) {
             energy = energyMax;
         }
         if (idade >= idadeMax) {
             this.idade = idadeMax;
             this.vivo = false;
             System.out.println("Seu Tamagotchi morreu de velhice!");
         }
     }
     
     void setSacer(int valor){
         this.sacer = valor;
         if (sacer <= 0) {
             this.sacer = 0;
             this.vivo = false;
             System.out.println("Seu Tamagotchi morreu de fome!");
         }
         if (sacer > sacerMax) {
             sacer = sacerMax;
         }
         if (idade >= idadeMax) {
             this.idade = idadeMax;
             this.vivo = false;
             System.out.println("Seu Tamagotchi morreu de velhice!");
         }
     }
     
     void setClean(int valor) {
         this.clean = valor;
         if (clean <= 0) {
             this.clean = 0;
             this.vivo = false;
             System.out.println("Seu Tamagotchi morreu de doenças!");
         }
         if (clean > cleanMax) {
             clean = cleanMax;
         }
         if (idade >= idadeMax) {
             this.idade = idadeMax;
             this.vivo = false;
             System.out.println("Seu Tamagotchi morreu de velhice!");
         }
     }
     
     boolean CheckLife() {
         if (!vivo) {
             System.out.println("Seu Tamagotchi está morto, você não pode interagir com ele!");
             return false;
         }
         return true;
     }
     
     void brincar() {
         if (CheckLife()) {
             setEnergy(energy - 2);
             setSacer(sacer - 1);
             setClean(clean - 3);
             this.idade += 1;
             this.dimas += 1;
             if (CheckLife()) {
                 System.out.println("Você brincou com seu Tamagotchi!");
             }
         }
     }
     
     void comer() {
         if (CheckLife()) {
             setSacer(sacer + 4);
             setEnergy(energy - 1);
             setClean(clean - 2);
             this.idade += 1;
             if(CheckLife()) {
                 System.out.println("Você alimentou seu Tamagotchi!");
             }
         }
     }
     
     void dormir() {
         if (CheckLife()) {
             if (energy > (energyMax - 5)) {
                 System.out.println("Seu Tamagotchi não está com sono!");
             } else for (int i = 1; i < (energyMax - energy) + 1; i++) {
                 if (CheckLife()) {
                    setEnergy(energy + 1);
                    this.idade += 1;
                    if (CheckLife()) {
                        System.out.println("Seu Tamagotchi dormiu por " + i + " horas!");
                    } else {
                        return;
                    }
                 } else {
                     return;
                 }
             }
         }
     }
     
     void limpar() {
         if (CheckLife()) {
             setClean(clean = cleanMax);
             setEnergy(energy - 3);
             setSacer(sacer - 1);
             this.idade += 2;
             if (CheckLife()) {
                 System.out.println("Você deu banho no seu Tamagotchi!");
             }
        }
    }
     public String toString() {
        return "ENERGIA: " + energy + "|" + energyMax + " SACIEDADE: " + sacer + "|" + sacerMax + " LIMPEZA: " + clean + "|" + cleanMax + " IDADE: " + idade + "|" + idadeMax + " DIAMANTES: " + dimas  ;
     }
}
    
    
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bixinho b1 = new Tamagotchi().new Bixinho(0, 0, 0, 0);
        
        while(true) {
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            
            if(ui[0].equals("$stop")) {
                break;
            } else if (ui[0].equals("$init")) {
                b1 = new Tamagotchi().new Bixinho(Integer.parseInt(ui[1]),Integer.parseInt(ui[2]),Integer.parseInt(ui[3]),Integer.parseInt(ui[4]));
            } else if (ui[0].equals("$show")) {
                System.out.println(b1);
            } else if (ui[0].equals("$play")) {
                b1.brincar();
            }else if (ui[0].equals("$eat")) {
                b1.comer();
            }else if (ui[0].equals("$sleep")) {
                b1.dormir();
            } else if (ui[0].equals("$clean")) {
                b1.limpar();
            } else {
                System.out.println("Comando inválido!");
            }
        }
    }   
}
