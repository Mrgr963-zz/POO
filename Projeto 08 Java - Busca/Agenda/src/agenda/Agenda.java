package agenda;
import java.util.*;

class Fone {
    private String label;
    private String number;
    
    public Fone (String label, String number) {
        this.label = label;
        this.number = number;
    }
    
    Fone (String serial) {
        String array[] = serial.split(":");
        label = array[0];
        number = array[1];
    }
    
    static boolean validate(String number) {
        String nums = "0123456789().-";
        for (int i = 0; i < number.length(); i++) {
            if (!nums.contains("" + number.charAt(i))) {
                System.out.println("ERROR: Número inválido!");
                return false;
            }
        }
        return true;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public String getNumber() {
        return this.number;
    }
    
    public String toString() {
        return label + ":" + number;
    }
}

class Contact {
    private String name;
    private ArrayList<Fone> fones;    
    
    public Contact (String name) {
        this.name = name;
        fones = new ArrayList<Fone>();
    }
    
    
    public void addFone(String label, String number) {
        if (!Fone.validate(number)) {
            System.out.println("ERROR: Esse número é inválido!");
        } else {
            fones.add(new Fone(label, number));
        }
    }
    
    
    public void rmFone(int index) {
        if (index < 0 || index > fones.size()) {
            System.out.println("ERROR: Index Inválido!");
        } else {
            fones.remove(index);
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String toString(){
        String aux = "";
        
        for (int i = 0; i < fones.size(); i++) {
            Fone fone = fones.get(i);
            aux += "[" + i + ":" + fone + "]";
        }
        return getName() + " " + aux;
        
    }  
}


public class Agenda {
    ArrayList<Contact> contatos;
    
    public Agenda () {
        contatos = new ArrayList<Contact>();
    }
    
    private int findContact(String name) {
        for (int i = 0; i < contatos.size(); i++) {
            Contact contato = contatos.get(i);
            
            if (contato != null && contato.getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
        
    Contact getContact (String name) {
        if (findContact(name) == -1) {
            System.out.println ("Novo contato criado!");
            return null;
        } else {
            return contatos.get(findContact(name));
        }
    }
    
    public void addContact (String name, Fone fone) {
        Contact contato = this.getContact(name);
        
        if (contato == null) {
            contato = new Contact(name);
            this.contatos.add(contato);
        }
        contato.addFone(fone.getLabel(), fone.getNumber());
    }
    
    static class Comparador implements Comparator <Contact> {
    public int compare (Contact x, Contact y) {
        return x.getName().compareTo(y.getName());
    }
}
    
    public void addContact (String name, List<Fone> fones) {
        Contact contato = this.getContact(name);
        
        if (contato == null) {
            contato = new Contact (name);
            this.contatos.add(contato);
        }
        for (Fone fone : fones) {
            contato.addFone(fone.getLabel(), fone.getNumber());
        }
    }
    
    public boolean rmContact(String name) {
        int contato = this.findContact(name);
        
        if (contato != -1) {
            this.contatos.remove(contato);
            System.out.println("Contato removido!");
            return true;
        } else {
            System.out.println("ERROR: Contato com esse nome não existe!");
            return false;
        }
    }
    
    ArrayList<Contact> search(String pattern) {
        ArrayList<Contact> aux = new ArrayList<Contact>();
        
        for (Contact contato: contatos){
            if (contato.toString().contains(pattern)) {
                aux.add(contato);
            } 
        }
        for (Contact contato : aux) {
            System.out.println(contato);
        }
        return aux;
    }
    
    ArrayList<Contact> getContacts() {
        for (int i = 0; i < contatos.size(); i++) {
            System.out.println(contatos.get(i));
        }
        return contatos;
    }
    
    public static void main(String[] args) {
        Agenda a1 = new Agenda();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            
            if (ui[0].equals("end")) {
                break;
            }else if (ui[0].equals("show")) {
                a1.getContacts();
            } else if (ui[0].equals("add")) {
                for (int i = 2; i < ui.length; i++) {
                    a1.addContact(ui[1], new Fone(ui[i]));
                }            
            } else if (ui[0].equals("remove")) {
                a1.rmContact((ui[1]));
            } else if (ui[0].equals("search")) {
                a1.search(ui[1]);
            } else {
                System.out.println("ERROR: COMANDO INVÁLIDO!");
            }
        }
    }
}
