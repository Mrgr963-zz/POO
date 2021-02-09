package agenda;
import java.util.*;

class Fone{
    private String label;
    private String number;
    
    public Fone (String label, String number) {
        this.label = label;
        this.number = number;
    }
    
    Fone(String serial) {
        String array[]= serial.split(":");
        label = array[0];
        number = array[1];
    }
    
    static public boolean validate(String number) {
        String nums = "0123456789().-";
        for (int i = 0; i < number.length(); i++) {
            if (!nums.contains("" + number.charAt(i))) {
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
        return label + number;
    }
}

class Contato {
    private String name;
    private ArrayList<Fone> fones;
    
    public Contato (String name) {
        this.name = name;
        fones = new ArrayList<Fone>();
    }
    
    public void addFone(String label, String num) {
        if (!Fone.validate(num)) {
            System.out.println("ERROR: Este número não é válido!");
        } else {
            fones.add(new Fone(label, num));
        }
    }
    
    public void addFone (Fone fone) {
        this.addFone(fone.getLabel(), fone.getNumber());
    }
    
    public void rmFone(int index) {
        fones.remove(fones.get(index));
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return name;
    }
}

class Agenda {
    private ArrayList<Contato> contacts;
    
    public Agenda() {
    contacts = new ArrayList<Contato>();
    }
    
    Contato getContato(String name) {
        for (Contato contato : contacts) {
            if (contato.getName().equals(name)) {
                return contato;
            } else {
                System.out.println("ERROR: Esse contato não existe");
            }
        }
        return null;
    }
    
    ArrayList<Contato> findContact(String pattern) {
        ArrayList<Contato> aux = new ArrayList<Contato>();
        
        for (Contato contato: contacts){
            if (contato.toString().contains(pattern)) {
                aux.add(contato);
                return aux;
            }
            
        }
        return null;
    }
    
   public void addContact(String name, ArrayList<Fone> fone) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                for (Fone fones : fone) {
                    contacts.get(i).addFone(fones);
                }
            } else {
                contacts.add(new Contato(name));
            }
        }
   }
   
   public boolean rmContact(String name) {
       for (Contato contato : contacts) {
           if (contato.getName().equals(name)) {
               contacts.remove(contato);
               return true;
           }
       }
       return false;
   }
    
   public ArrayList<Contato> getContatos() {
       ArrayList<Contato> aux = new ArrayList<Contato>();
       for (int i = 0; i < contacts.size(); i++) {
           aux.add(contacts.get(i));
       }
       return aux;
   }
    
   public String toString() {
       String aux;
       
       aux = "[";
       aux += " " + contacts + " " ;
       aux += "]";
       
       return aux;
   }
    
    public static void main(String[] args) {
        Agenda a1 = new Agenda();
        
        a1.addContact("eva", new ArrayList<Fone>(new Fone("oi","555")));      
    }
}