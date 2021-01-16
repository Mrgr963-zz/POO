import java.util.Scanner;
import java.util.ArrayList;

class Fone{
    public String label, number;
    public Fone(String label, String number){
        this.label = label;
        this.number = number;
    }
    public String toString(){
        return label + ":" + number;
    }
}

public class Contato{
    private String name;
    private ArrayList<Fone> fones;//null

    public Contato(String name){
        this.name = name;
        this.fones = new ArrayList<>();
    }

    public void addFone(String label, String number){
        fones.add(new Fone(label, number));
    }

    public String toString(){
        String saida = "";
        saida = name;
        int i = 0;
        for(Fone fone : this.fones){
            saida += " " + i + ":" + fone;
            i += 1;
        }
        return saida;
    }

    Fone getFone(int index){
        //teste se indice eh valido
        //se >= 0 e < size

        if (fones.numero.get(index).length == 11) {
            System.out.println(fones.numero.get(index));
        } else {
            System.out.println("Telefone inválido");
        }
    }

    
    //1 consertar o toString para imprimir os indices dos telefones
    //fazer a obtencao por indice   Fone getFone(int index)
    //fazer a obtencao por label    Fone getFone(String label)
    //fazer a remoção por índice    boolean rmFone(int index)
    //fazer a remocao por numero    boolean rmFone(String number)
    //fazer a validacao do numero   

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Contato contato = new Contato("Sabrina Raulino");
        while(true){
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            if(ui[0].equals("end")){
                break;
            }else if(ui[0].equals("init")){
                contato = new Contato(ui[1]);
            }else if(ui[0].equals("show")){ // Sabrina [0:oi:123 1:tim:1233 2:vivo:911]
                System.out.println(contato);
            }else if(ui[0].equals("addFone")){//label number
                contato.addFone(ui[1], ui[2]);
            }else{
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}



