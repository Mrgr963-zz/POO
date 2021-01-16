package contatos;
import java.util.*;
public class Contatos {
    
    public enum Labels {
        casa, fixo, oi;
    }
    
    public class Contato{
        private String name;
        private ArrayList<Fone> fones = new ArrayList<Fone>();
        
        public Contato(String name, Fone fones){
            this.name = name;
        }
        
        void init (String nome) {
            this.name = nome;
        }
        
        void addNumber (enum Labels, int number) {
            Contato.fones.add(Labens, number);
        }
        
        
        void remove(int index) {
            
        }
        
        
        
        
        
        
    }
    
    
    public class Fone{
        private enum label;
        private String number;
        
        public Fone(enum label, String number) {
            this.label = label;
            this.number = number;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {

    }
    
}
