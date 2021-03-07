package contatostar;
import java.util.*;

class Fone{
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
                throw new RuntimeException("\"ERROR: Número inválido!\"");
            }
        }
        return true;
    }
    
    String getLabel() {
        return label;
    }
    
    String getNumber() {
        return number;
    }
}
    
class Contact {
    private String name;
    private ArrayList<Fone> fones;
        
    public Contact(String name) {
        this.name = name;
        fones = new ArrayList<Fone>();
    }
        
    void addFone (String label, String number) {
        if (!Fone.validate(number)) {
            throw new RuntimeException("\"ERROR: Número inválido!\"");
        } else {
            fones.add(new Fone(label, number));
        }
    }
        
    void rmFone(int index) {
        if (index < 0 || index > fones.size()) {
            throw new RuntimeException("\"ERROR: Index inválido!\"");
        } else {
            fones.remove(index);
        }
    }
        
    ArrayList<Fone> getFones() {
        return fones;
    }
    
    String getName() {
        return name;
    }
        
    public String toString() {
        return name + " " + fones;
    }
}

class ContactPlus extends Contact{
    boolean starred;
    
    public ContactPlus(String name) {
        super(name);
    }
    
    public void setBookmark(boolean value) {
        if (this.starred == value) {
            throw new RuntimeException("\"ERROR: Contato já possui esse valor!\"");
        } else {
            this.starred = value;
        }
    }
    
    public boolean getBookmark() {
        return starred;
    }
}

class Agenda {
    Map<String, Contact> contacts;
    
    public Agenda() {
        contacts = new TreeMap<>();
    }
    
    public void addContact(String name, List <Fone> fones){
        if (!contacts.containsKey(name)) {
            contacts.put(name, new Contact(name));
        }
        
        Contact contato = contacts.get(name);
        for (Fone fone : fones) {
            contato.addFone(fone.getLabel(), fone.getNumber());
        }
    }
    
    public boolean rmContact(String name) {
        Contact contato =  getContact(name);
        
        if (!contacts.containsKey(name)) {
            throw new RuntimeException("\"ERROR: Contato inexistente!\"");
        } else {
            contacts.remove(name);
            return true;
        }
    }
    
    ArrayList<Contact> search(String pattern) {
        ArrayList<Contact> aux = new ArrayList<Contact>();
        
        for (Contact contato : contacts.values()){
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
        return new ArrayList<Contact>(contacts.values());  
    }
    
    Contact getContact (String name) {
        Contact contato = contacts.get(name);
        
        if (contato == null) {
            throw new RuntimeException("\"ERROR: Contato inexistente!\"");
        }
        return contato;
    }
    
    public String toString() {
        String aux = "";
        
        for (Contact contato : contacts.values()) {
                aux += "@ " + contato;
            }
        return aux;
    }
}

class AgendaPlus extends Agenda {
    Map<String, ContactPlus> contacts;
    Map<String, ContactPlus> bookmarks;
    
    public AgendaPlus () {
        contacts = new TreeMap<>();
        bookmarks = new TreeMap<>();
    }
    
    @Override
    public boolean rmContact(String name) {
        ContactPlus contato =  (ContactPlus) getContact(name);
        
        contacts.remove(name);
        return true;
    }
    
    public void bookmark(String id) {
        ContactPlus contato = (ContactPlus) contacts.get(id);
        
        if(!contato.getBookmark()){
            contato.setBookmark(true);
            bookmarks.put(id, contato);
        } else {
            throw new RuntimeException("\"ERROR: Contato já favoritado!\"");
        }
    }
    
    public void unbookmark(String id) {
        ContactPlus contato = (ContactPlus) contacts.get(id);
        
        if (contato.getBookmark()) {
            bookmarks.remove(id, contato);
            contato.setBookmark(false);
        } else {
            throw new RuntimeException("\"ERROR: Contato já desfavoritado!\"");
        }
    }
    
    ArrayList<Contact> getBookmarks() {
        return new ArrayList<Contact>(bookmarks.values());
    }
    
    public String toString() {
        String aux = "";
        
        for (ContactPlus contato : contacts.values()) {
            if (!contato.getBookmark()) {
                aux += "- " + contato;
            } else {
                aux += "@ " + contato;
            }
        }
        return aux;
    }
}


public class ContatoStar {
    public static void main(String[] args) {
        AgendaPlus a1 = new AgendaPlus();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            try {
                String line = scanner.nextLine();
                String ui[] = line.split(" ");
                
                if (ui[0].equals("stop")) {
                    break;
                } else if (ui[0].equals("show")) {
                    System.out.println(a1);
                } else if (ui[0].equals("add")) {
                    List <Fone> fones = new ArrayList <>();
                    
                     for (int i = 2; i < ui.length; i++) {
                        fones.add(new Fone(ui[i]));
                     } a1.addContact((ui[1]), fones);
                } else if (ui[0].equals("remove")) {
                    a1.rmContact((ui[1]));
                } else if (ui[0].equals("search")) {
                    a1.search(ui[1]);
                } else if (ui[0].equals("star")) {
                    a1.bookmark(ui[1]);
                } else if (ui[0].equals("unstar")) {
                    a1.unbookmark(ui[1]);
                }else if (ui[0].equals("starred")) {
                    System.out.println(a1.getBookmarks());
                }else {
                    System.out.println("\"ERROR: Comando Incorreto!\"");
                }
            }
            catch(RuntimeException error) {
                System.out.println(error.getMessage());
            }
        } 
    }
}
