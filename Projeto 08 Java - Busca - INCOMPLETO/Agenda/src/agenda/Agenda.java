//COMEÇOU: 17:20 DAY 25 -------- 19:0
// 13:30 DAY 26  --------  PAUSA - 16:33 --------- VOLTA - 

package agenda;
import java.util.*;

class Fone {
    String id;
    String number;
    
    public Fone (String id, String number) {
        this.id = id;
        this.number = number;
    }
    
    public Fone (String serial) {
        String array[];
        array = serial.split(":");
        id = array[0];
        number = array[1];
    }
    
    ////////////////////////////////////////////////////////////////////////////
    void Validate() {
        
    }
    ////////////////////////////////////////////////////////////////////////////
    
    public String toString() {
        return id + ":" + number ;
    }
}






class Contato {
    String name;
    ArrayList<Fone> fones;
    
    public Contato(String name) {
        this.name = name;
    }
    
    void addFones(String id, String number) {
        for (int i = 0; i < fones.size(); i++) {
            if (fones.get(i).number.equals(number)) {
                System.out.println("Este número ja existe! Insira um número válido!");
                return;
            }
        } 
        fones.add(new Fone(id, number));
    }
    
    void rmvFone(int index) {
        for (int i = 0; i < fones.size(); i++) {
            if (i == index) {
                fones.remove(i);
                System.out.println("Fone da posição " + index + "foi removido!");
                return;
            }
        }
        System.out.println("Fone não encontrado!");
    }
    
    String getName() {
        return name;
    }
    
    ArrayList<Fone> getFones() {
        return fones;
    }
    
    public String toString() {
        return name + ":" + fones ;
    }
}





public class Agenda {
    ArrayList<Contato> contatos;
    
    public Agenda() {
        
    }
    
    int findContato(String nome) {
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).name.equals(nome)) {
                return contatos.indexOf(i);
            }
        }
        return -1;
    }
        
    void addContato(String nome, ArrayList<Fone> fones) {
        contatos.add(new Contato(nome));
    }
    
    void rmvContato(String nome) {
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).name.equals(nome)) {
                contatos.remove(i);
                System.out.println("Contato removido!");
            }
        }
    }
    
    void search(String pattern) {
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).name.contains(pattern) || contatos.get(i).fones.get(i).number.contains(pattern)) {
                System.out.println(contatos.get(i).name);
            }
        }
    }
    
    ArrayList<Contato> getContatos() {
        return contatos;
    }
    
    String getContato(String nome) {
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).name.equals(nome)) {
                return contatos.get(i).name;
            }
        }
        return null;
    }
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Fone f1 = new Fone("","");
        Contato c1 = new Contato("");
        Agenda a1 = new Agenda();
        ArrayList<Fone> telefones = new ArrayList<Fone>();
        
        while (true) {
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("add")) {
                for (int i = 0; i < ; i++) {
                    
                }
                for (int i = 0; i < a1.contatos.size(); i++) {
                    if (a1.contatos.get(i).name.equals(ui[1])) {
                       
                    }
                }
            }
        }
    }
    
}
