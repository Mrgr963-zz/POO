package agenda;
import java.util.*;

class Fone {
    String id;
    String number;
    
    public Fone (String id, String number) {
        this.id = id;
        this.number = number;
    }

    public String toString() {
        return id + ":" + number ;
    }
}

class Contato {
    String name;
    ArrayList<Fone> fones;
    
    public Contato(String name) {
        this.name = name;
        this.fones = fones;
    }
    
    boolean validate(String number) {
        boolean res = true;
        String nums = "1234567890().";
        int aux = 0;

        for (int i = 0 ; i < number.length(); i++) {
            if (res == false) {
                System.out.println("Número inválido");
            } else {
                for (int j = 0 ; j < nums.length(); j++) {
                    if (number.charAt(i) != nums.charAt(j)) {
                        aux += 1;
                    } if (aux == nums.length()) {
                        res = false;
                    }
                }
            }
        }
        return res;
    }

    public String toString() {
        String aux = "[";
        for (int i = 0; i < fones.size(); i++) {
            Fone num = fones.get(i);
            aux = i + ":" + num + "]";
        }
        return this.name + aux;
    }
}

class orgnz implements Comparator<Contato>{
    public int compare(Contato x, Contato y) {
        return x.name.compareTo(y.name);
    }
}

public class Agenda {
    ArrayList<Contato> contatos;
    
    public Agenda() {
        this.contatos = new ArrayList<Contato>();
    }

    boolean checkContato(String nome){
        String aux;
        
        for(int i = 0; i < contatos.size(); i++){
            aux = contatos.get(i).name;
            if(nome.equals(aux)){
                return true;
            }
        }
        return false;
    }

    int getContatoPosition(String nome) {
        String aux;

        for (int i = 0; i < contatos.size(); i++) {
            aux = contatos.get(i).name;
            if (nome.equals(aux)) {
                return i;
            }
        }
        return -1;
    }

    Contato getContato(String nome) {
        String aux;

        for (int i = 0; i < contatos.size(); i++) {
            aux = contatos.get(i).name;
            if (nome.equals(aux)) {
                return contatos.get(i);
            }
        }
        return null;
    }
        
    void addContato(String nome) {
        if (checkContato(nome) == true) {
            System.out.println("ERROR: O contato já existe!");
        } else {
            contatos.add(new Contato(nome));
        }
    }

    void addNumber(String nome, String desc, String num) {
        if (checkContato(nome) == true) {
            getContato(nome).fones.add(new Fone(num, desc));
            return;
        }
        System.out.println("ERROR: Contato não encontrado!");
    }
    
    
    void rmvContato(String nome) {
        contatos.remove(getContatoPosition(nome));
    }

    void rmvNum(String nome, String id) {
        if (checkContato(nome) == true) {
            getContato(nome).fones.remove(Integer.parseInt(id));
            return;
        }
        System.out.println("ERROR: Contato não encontrado!");
    }

    void sort() {
        Collections.sort(contatos, new orgnz());
        for (Contato contato : contatos) {
            System.out.println("- " + contato);
        }
    }

    public void search(String pattern) {
        ArrayList<Contato> aux = new ArrayList<Contato>();

        for (Contato contato : contatos) {
            if (contato.name.contains(pattern)) {
                aux.add(contato);
            }
            ArrayList<Fone> fones = contato.fones;
            for (Fone fone : fones) {
                if (fone.number.contains(pattern)) {
                    aux.add(contato);
                }
            }
        }
        for (Contato contato : aux) {
            System.out.println("- " + contato);
        }
    }

    boolean validate(String number) {
        boolean res = true;
        String nums = "1234567890().";
        int aux = 0;

        for (int i = 0 ; i < number.length(); i++) {
            if (res == false) {
                System.out.println("Número inválido");
            } else {
                for (int j = 0 ; j < nums.length(); j++) {
                    if (number.charAt(i) != nums.charAt(j)) {
                        aux += 1;
                    } if (aux == nums.length()) {
                        res = false;
                    }
                }
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda a1 = new Agenda();

        while (true) {
        String line = scanner.nextLine();
        String ui[] = line.split(" ");
            if (ui[0].equals("break")) {
                break;
            } else if (ui[0].equals("addC")) {
                a1.addContato(ui[1]);
            } else if (ui[0].equals("addN")) {
                a1.addNumber(ui[1], ui[2], ui[3]);
            } else if (ui[0].equals("show")) {
                a1.sort();
            } else if (ui[0].equals("search")) {
                a1.search(ui[1]);
            } else if (ui[0].equals("rmvC")) {
                a1.rmvContato(ui[1]);
            } else if (ui[0].equals("rmvN")) {
                a1.rmvNum(ui[1], ui[2]);
            } else {
                System.out.println("ERROR: Comando Inválido!");
            }
        }
    }
}
