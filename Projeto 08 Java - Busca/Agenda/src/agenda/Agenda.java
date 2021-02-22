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

class Comparador implements Comparator <Contato> {
    public int compare (Contato x, Contato y) {
        return x.getName().compareTo(y.getName());
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
    
    private int getContact (String nome) {
        for (int i = 0; i < contacts.size(); i++) {
            Contato contato = contacts.get(i);
            if (contato != null && contato.getName().equals(nome)) {
                return i;
            }
        } return -1;
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
    
   public void addContact(String name, Fone fone) {
        Contato contato = this.getContato(name);
        if (contato == null) {
            contato = new Contato(name);
            this.contacts.add(contato);
        }
        contato.addFone(fone.getLabel(), fone.getNumber());
   }
   
   public void addContact (String name, List<Fone> fones) {
       Contato contato = this.getContato(name);
       if (contato == null) {
           contato = new Contato(name);
           this.contacts.add(contato);
       } for (Fone fone : fones) {
           contato.addFone(fone.getLabel(), fone.getNumber());
       }
   }
   
   public boolean rmContact(String name) {
       int aux = getContact(name);
       if (aux != -1) {
           this.contacts.remove(aux);
           return true;
       } System.out.println("ERROR: Esse contato não existe!");
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
        Scanner scanner = new Scanner (System.in);
        
        while (true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("show")) {
                System.out.println(a1);
            } else if (ui[0].equals("add")) {
                List<Fone> fones = new ArrayList<Fone>();
                
                for (int i = 2; i < ui.length; i++) {
                    fones.add(new Fone(ui[i]));
                }
                a1.addContact((ui[1]), fones);
                Collections.sort(a1.getContatos(), new Comparador());
            } else if (ui[0].equals("rmvContact")) {
                a1.rmContact(ui[1]);
            } else if (ui[0].equals("findContact")) {
                System.out.println(a1.findContact(ui[1]));
            }
        }
    }
}