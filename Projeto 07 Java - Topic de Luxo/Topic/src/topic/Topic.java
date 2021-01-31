package topic;
import java.util.*;

class Pass {
    String nome;
    int idade;
        public Pass (String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
        }
    public String toString() {
       return "nome: " + nome + " idade: " + idade ;
    }
}



class Topic {
    ArrayList<Pass> cadeiras;
    int qtdPref;
    
    public Topic (int lotacao, int qtdPref ) {
        this.qtdPref = qtdPref;
        cadeiras = new ArrayList<Pass>();
        for (int i = 0; i < lotacao; i++) {
            cadeiras.add(null);
        }
    }
    
    void show() {
        String saida = "[";
        if (qtdPref < cadeiras.size()) {
            for (int i = 0; i < cadeiras.size(); i++) {
                if (i < qtdPref) {
                    if (cadeiras.get(i) != null) {
                        saida += "(@" + cadeiras.get(i).nome + ":" + cadeiras.get(i).idade + ") ";
                    } else {
                        saida += "@ ";
                    }
                } else {
                    if (cadeiras.get(i) != null) {
                        saida += "(=" + cadeiras.get(i).nome + ":" + cadeiras.get(i).idade + ") ";
                    } else {
                        saida += "= ";
                    }
                }
            }
        }
        System.out.println(saida + " ]");
    }
    
    void subir(String nome, int idade) {
        if (idade >= 60) {
            for (int i = 0; i < qtdPref ; i ++) {
                if (cadeiras.get(i) == null) {
                    cadeiras.set(i, new Pass(nome, idade));
                    System.out.println(nome + " sentou na cadeira " + i);
                    return;
                } 
            }
            for (int j = cadeiras.size()- qtdPref; j < cadeiras.size(); j++) {
                if (cadeiras.get(j) == null) {
                    cadeiras.set(j, new Pass(nome, idade));
                    System.out.println(nome + " sentou na cadeira" + j);
                    return;
                }
            }
            System.out.println("A Topic não tem espaço!");
        } else {
            for (int c = cadeiras.size() - qtdPref; c < cadeiras.size(); c++) {
                if (cadeiras.get(c) == null) {
                    cadeiras.set(c, new Pass(nome, idade));
                    System.out.println(nome + " sentou na cadeira" + c);
                    return;
                }
            }
            for (int x = 0; x < qtdPref; x++) {
                if (cadeiras.get(x) == null) {
                    cadeiras.set(x, new Pass(nome,idade));
                    System.out.println(nome + " sentou na cadeira" + x);
                    return;
                }
            }
        }
        System.out.println("A Topic não tem espaço!");
    }
    
    void descer(String nome) {
        for (int i = 0; i < cadeiras.size(); i++) {
            if (cadeiras.get(i) != null && cadeiras.get(i).nome.equals(nome)) {
                cadeiras.set(i, null);
                System.out.println(nome + " desceu na Topic!");
                return;
            }
        }
        System.out.println("ERROR!: Pessoa não se encontra");
    }
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pass p1 = new Pass("", 0);
        Topic t1 = new Topic(0,0);
        
        while(true) {
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("init")) {
                t1 = new Topic(Integer.parseInt(ui[1]),Integer.parseInt(ui[2]));
            } else if (ui[0].equals("show")) {
                t1.show();
            } else if (ui[0].equals("in")) {
                t1.subir(ui[1], Integer.parseInt(ui[2]));
            } else if (ui[0].equals("out")) {
                t1.descer(ui[1]);
            } else {
                System.out.println("Comando Inválido!");
            }
        }
    }
}
