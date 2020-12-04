package calango.versus;
import java.util.*;
public class CalangoVersus {
    public class Heroi extends Dado {
        String nome;
        String classe;
        String spell;
        String arma;
        
        int lvl;
        int xp;
        int xp_cap;
        int mana;
        int mana_max;
        int hp;
        int hp_max;
        int energia;
        int energia_max;
        
        boolean vivo;
        boolean armado;
        
        Heroi(String nome, String classe, String arma , String spell,int mana_max, int energia_max) {
            this.nome = nome;
            this.classe = classe;
            this.arma = arma;
            this.spell = spell;
            
            this.lvl = 1;
            this.xp = 0;
            this.xp_cap = (lvl*10);
            this.mana = mana_max;
            this.mana_max = mana_max;
            this.hp_max = lvl*5 ;
            this.hp = hp_max;
            this.energia = energia_max;
            this.energia_max = energia_max;
            
            this.vivo = true;
            this.armado = true;
        }
        void Ataque(String atk) {
            if (this.arma.equals(atk)) { //verifica se conhece o atk.
                if (energia >= 0) { //verifica se tem energia.
                    if (armado) { //verifica se está armado.
                        if (vivo) { //verifica se está vivo.
                            int chance = dado(10,1); //cria uma rolagem de chance aleatória.
                            if (chance == 1) { //se a rolagem der 1...
                                hp = hp - 2;
                                energia -= 1;
                                armado = false;
                                xp = xp + 2;
                                System.out.println("Meu destino foi terrivel! Os monstros me emboscaram e perdi minha arma!");
                            } else if ( chance > 1 && chance < 9) { //Se a rolagem der de 1~9...
                                hp = hp - 1;
                                energia -= 1;
                                xp += 1;
                                System.out.println("Eu ataquei os inimigos!");
                            } else if (chance == 10) { //Se a rolagem der de 10...
                                energia -= 1;
                                xp = xp + 2;
                                System.out.println("A-HA! Embosquei meus inimigos com sucesso!");
                            }
                        } else {
                            System.out.println("Estou morto, minha aventura acabou;...");
                        }
                    } else {
                        System.out.println("Não posso atacar sem uma arma!");
                    }
                } else {
                    System.out.println("Energia insuficiente, preciso descansar!");
                }
            } else {
                System.out.println("Não possuo essa arma!");
            }
            if (hp <= 0) {//Verifica se o personagem morreu.
                vivo = false;
                hp = 0;
                System.out.println("Morri em combate! Minha história acaba aqui...");
            }
            if (xp >= xp_cap){//Verifica se o personagem upou de lvl.
                lvl = lvl + 1;
                xp = xp - xp_cap;
                xp_cap = lvl*10;
                hp_max = hp_max + 5;
                energia_max += 2;
                System.out.println("Sinto-me mais forte agora!");
            }
        }
        
        void Descansar(int tempo) {
            for (int i = 1; i <= tempo; i++ ){//Faz com que a rolagem se repita a cada 1 unidade tempo.
                int chance = dado (20,1);//Cria uma rolagem aleatória.
                
                if (vivo) {//Verifica se o personagem morreu
                    if (armado) {//Verifica se o personagem está armado
                        if (chance > 1) {
                            energia += 1;
                            if (i == 1) {
                                System.out.println("Consegui descansar por " + i + " hora!");
                            } else {
                            System.out.println("Consegui descansar por " + i + " horas!");
                            if (energia >= energia_max) {
                                hp += 1;
                                mana +=1;
                                System.out.println("Recuperei vida e mana!");
                                    }
                            }
                        }else {
                            if (armado = false) {
                                hp = hp - 4;
                                xp += 1;
                                System.out.println("Eu fui emboscado enquanto desarmado...Lutar sem armas não é uma boa ideia...");
                            } else {
                                hp = hp - 2;
                                xp += 1;
                                System.out.println("Fui emboscado enquanto dormia...");
                            }
                        }
                    }
                } else {
                    System.out.println("Estou morto, minha aventura acabou...");
                }
            }
            if (hp <= 0) {//Verifica se o personagem morreu.
                vivo = false;
                hp = 0;
                System.out.println("Morri em combate! Minha história acaba aqui...");
            }
            if (hp > hp_max){//Verifica se o personagem chegou ao cap de HP.
                hp = hp_max;
            }
            if (energia > energia_max){//Verifica se o personagem chegou ao cap de energia.
                energia = energia_max;
            }
            if (mana > mana_max){//Verifica se o personagem chegou ao cap de mana.
                mana = mana_max;
            }
            if (xp >= xp_cap){//Verifica se o personagem upou de lvl.
                lvl = lvl + 1;
                xp = xp - xp_cap;
                xp_cap = lvl*10;
                energia_max += 2;
                System.out.println("Sinto-me mais forte agora!");
            }
        }
        
        void Consertar() {
            if (vivo) {
                if (armado){//Verifica se o personagem está armado.
                    System.out.println("Minha arma não precisa de conserto!");
                } else {
                    if (energia >= 2) {
                        armado = true;
                        energia = energia - 2;
                        System.out.println("Consertei minha arma!");
                    } else {
                        System.out.println("Energia insuficiente...");
                    }
                }
            } else {
                System.out.println("Estou morto, minha história acabou...");
            }
        }
        
        void Atacar_magia(String spell, int power) {
            int chance = dado(10,1);//Cria uma rolagem de chance aleatória.
            if (this.spell.equals(spell)) {
                if (vivo) {//Verifica se o personagem está morto.
                    if (mana >= power) {//Verifica se o personagem tem mana.
                        if (chance == 1) {//Se a chance der 1...
                            hp = hp - 2;
                            mana = mana - power;
                            xp += 1;
                            energia -= 1;
                            System.out.println("Meu destino foi terrivel! Fui emboscado pelos monstros!");
                        }
                        if (chance > 1 && chance < 10) {//Se a chance der entre 2~9...
                            hp = hp - 1;
                            mana = mana - power;
                            xp += 1;
                            System.out.println("Eu conjurei " + spell + "e acertei o inimigo!");
                        }
                        if (chance == 10) {//Se a chance der 10...
                            mana = mana - power;
                            xp = xp + 2;
                            System.out.println("Que sorte! Eu conjurei " + spell + " e eliminei todos os inimigos!");
                        } 
                    } else {
                        System.out.println("Mana insuficiente!");
                    }
                } else {
                    System.out.println("Estou morto. Minha aventura acaba aqui...");
                }
            } else {
                System.out.println("Não conheço essa magia...");
            }
            if (hp <= 0){//Verifica se o personagem morreu.
                vivo = false;
                hp = 0;
                System.out.println("Morri em combate! Minha história acaba aqui...");
            }
            if (hp >= hp_max){//Verifica se chegou ao cap de vida.
                hp = hp_max;
            }
            if (mana >= mana_max){//Verifica se chegou ao cap de mana.
                mana = mana_max;
            }
            if (xp >= xp_cap){//Verifica se o personagem upou de lvl.
                lvl = lvl + 1;
                xp = xp - xp_cap;
                xp_cap = lvl*10;
                energia_max += 2;
                System.out.println("Sinto-me mais forte agora!");
            }
        }
         
        public String toString(){//Transforma o objeto.
        return "Nome: " + nome +" | "+ "Classe: " + classe +" | "+ "Arma: " + arma +" | "+ "Magia: " + spell +" | "+ "Nivel: " + lvl +" | "+ "XP: " + xp + "/" + xp_cap +" | "+ "HP: " + hp + "/" + hp_max +" | "+ "MP: " + mana + "/" + mana_max +" | "+ "EP: " + energia + "/" + energia_max +" | "+ "Armado: " + armado;
        }
        
        boolean equals (Heroi other) {//Comparador.
            return nome.equals(other.nome);
        }
    }
    class Dado {//Geração de números aleatórios entre X e Y.
    public int dado(int max, int min) {
        Random aleatorio = new Random();
        int d = aleatorio.nextInt((max - min) + 1) + min;
        return d;
    }
}
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//Inicialização de scanner.
        Heroi h1 = new CalangoVersus().new Heroi("","","","",0,0);
        while (true) {
            
            System.out.println("");
            
            String line = scanner.nextLine();
            String[] ui = line.split(" ");      
            if (ui[0].equals("end")) {//Comando END
                break;
            } else if (ui[0].equals("create")) {//Comando CREATE
                h1 = new CalangoVersus().new Heroi(ui[1],ui[2],ui[3],ui[4],Integer.parseInt(ui[5]),Integer.parseInt(ui[6]));
            } else if (ui[0].equals("status")) {//Comando STATUS
                System.out.println(h1);
            } else if (ui[0].equals("attack")) {//Comando ATK
                h1.Ataque(ui[1]);
            } else if (ui[0].equals("rest")) {//Comando REST
                h1.Descansar(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("repair")) {//Comando REPAIR
                h1.Consertar();
            } else if (ui[0].equals("cast")) {//Comando CAST
                h1.Atacar_magia(ui[1],Integer.parseInt(ui[2]));
            } else if (ui[0].equals("help")) {
                System.out.println("COMANDOS DISPONÍVEIS:");
                System.out.println("");
                System.out.println("create: CRIA UM HERÓI. EXEMPLO: create Deadpool guerreiro espada gelo 5 2");
                System.out.println("status: APRESENTA A CONDIÇÃO DO SEU HERÓI. EXEMPLO: status Deadpool");
                System.out.println("attack: FAZ O SEU HERÓI ATACAR COM INIMIGOS COM A ARMA. EXEMPLO attack espada");
                System.out.println("rest: FAZ O SEU HERÓI DESCANSAR E RECUPERAR ENERGIA. EXEMPLO rest 5");
                System.out.println("repair: REPARA A ARMA DO SEU HERÓI. EXEMPLO repair");
                System.out.println("cast: FAZ SEU HERÓI CONJURAR UMA MAGIA CONTRA INIMIGOS. EXEMPLO cast gelo 2");
            } else {
                System.out.println("Comando inválido!");
            }
        }
        scanner.close();
    }
}//FIM.

   


