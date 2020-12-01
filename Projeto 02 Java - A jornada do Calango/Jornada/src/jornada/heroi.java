package jornada;
public class heroi extends dado{

    String nome;
    int nivel;
    int xp;
    int xp_cap;
    int hp;
    int hp_max;
    int ep;
    int ep_max;
    
    boolean armado;
    boolean vivo;

    public heroi () {
        this.nome = "Calango Guerreiro";
        this.nivel = 1;
        this.xp = 0;
        this.xp_cap = (nivel*10);
        this.hp_max = (nivel*5);
        this.hp = hp_max;
        this.ep_max = (nivel+2);
        this.ep = ep_max;
        this.armado = true;
        this.vivo = true;
    }
    
    
    public void status() {
        System.out.println("Meu nome é: " + nome);
        System.out.println("Sou um herói de nivel: " + nivel);
        System.out.println("Eu tenho: " + xp + "/" + xp_cap + " de XP" );
        System.out.println(" ");
        System.out.println("!!! STATUS !!!");
        System.out.println("HP: " + hp);
        System.out.println("ENERGIA: " + ep);
        System.out.println("Estou armado?: " + armado);
        System.out.println("Estou vivo?: " + vivo);
    }
    
    public void atacar() {
        int chance = dado(20,1);
        int cFatig = dado(10,1);
        int espchance = dado(6,1);
        
        if (hp <= 0) {
            hp = 0;
            System.out.println("Eu estou morto. Não posso mais lutar...");
        } else {
            if (ep <= 0) {
                ep = 0;
                hp = hp - 2;
                System.out.println("Estou exausto... Sofri pra escapar dos monstros...");
            } else {
                if (armado == false) {
                    hp = hp - 2;
                    xp = xp + 1;
                    System.out.println("Lutar sem uma espada é uma péssima ideia! Os monstros limparam a masmorra com a minha cara...");
                } else {
                    if (chance > 1 && chance < 7) {
                        hp = hp - 2;
                        xp = xp + 1;
                        System.out.println("Fui cercado pelos monstros! Por sorte consegui escapar...");
                        if (espchance == 1) {
                            armado = false;
                            System.out.println("Droga! Perdi minha espada nessa batalha...");
                        }
                        if (cFatig < 5) {
                            ep = ep - 1;
                            System.out.println("Estou exausto, essa luta não foi facil...");
                        }
                    }
                    if (chance > 6 && chance < 12 ) {
                        hp = hp - 1;
                        xp = xp + 1;
                        System.out.println("Eu derrotei meu inimigo em uma batalha heróica!");
                        if (espchance == 1) {
                            armado = false;
                            System.out.println("Droga! Perdi minha espada nessa batalha...");
                        }
                        if (cFatig < 5) {
                            ep = ep - 1;
                            System.out.println("Estou exausto, essa luta não foi facil...");
                        }
                    }
                    if (chance > 11 && chance < 19) {
                    xp = xp + 1;
                    System.out.println("Eu derrotei meu inimigo sem sofrer nenhum arranhão! Que sorte!");
                    if (espchance == 1) {
                        armado = false;
                        System.out.println("Droga! Perdi minha espada nessa batalha...");
                    }
                    if (cFatig < 5) {
                        ep = ep -1;
                        System.out.println("Estou exausto, essa luta não foi facil...");
                    }
                }
                if (chance == 20) {
                    xp = xp + 2;
                    armado = true;
                    System.out.println("Eu venci dois inimigos de uma só vez! E ainda recuperei uma arma!");
                    if (armado = true) {
                        System.out.println("Eu já tenho espada. Não tenho espaço pra carregar outra arma...");
                    }
                    if (cFatig < 5) {
                        ep = ep - 1;
                        System.out.println("Estou exausto, essa luta não foi facil...");
                    }
                }
                
                if (hp < 1) {
                    ep = 0;
                    vivo = false;
                    System.out.println("Eu morri após a batalha. Minhas história acaba aqui...");
                }
                
                if (xp == xp_cap) {
                    nivel = nivel + 1;
                    xp = 0;
                    hp_max = nivel * 5;
                    System.out.println("Eu upei de Nivel! Agora meu nível é: " + nivel);
                }
            }
        }
    }
}
    
    public void descansar() {
        int cEnc = dado(7,1);
        
        
        if (hp <= 0 ){
            System.out.println("Estou morto, meu descanso será eterno...");
        } else {
            if (cEnc == 1) {
                hp = hp - 1;
                xp = xp + 1;
                System.out.println("Os monstros me emboscaram enquanto eu tentava descansar!");
            } else {
                ep = ep + 1;
                System.out.println("Eu descanso em paz por algumas horas. Recuperei 1 de ENERGIA!");
                
                if (ep >= ep_max) {
                    ep = ep_max;
                    hp = hp + 1;
                    System.out.println("Estou plenamente descansado, recuperei 1 de HP!");
                    if (hp > hp_max) {
                        hp = hp_max;
                    }
                }
            }
        }
    }
    
    public void explorar() {
        int cExp = dado(20,1);
        
        if (vivo == false) {
            System.out.println("Eu estou morto, meu espirito vaga pelo submundo..");
        } else {
            if (ep <= 0 ) {
                System.out.println("Estou muito cansado pra explorar, preciso descansar...");
            } else {
                if (cExp == 1) {
                    hp = hp - 2;
                    ep = ep - 2;
                    xp = xp + 1;
                    armado = false;
                    System.out.println("Encontrei um mimico! Ele me feriu e engoliu minha espada...");
                }
                if (cExp > 1 && cExp < 7 ) {
                    hp = hp - 1;
                    ep = ep - 1;
                    xp = xp + 1;
                    System.out.println("Eu encontrei um grupo de monstros que me atacaram!");
                }
                if (cExp > 6 && cExp < 19) {
                    ep = ep - 1;
                    System.out.println("Eu não encontrei nada...");
                }
                if (cExp > 18 && cExp < 20) {
                    hp = hp + 1;
                    xp = xp + 1;
                    ep = ep - 1;
                    armado = true;
                    System.out.println("Eu encontrei um baú! Nele havia uma poção de cura e uma espada!");
                    if (armado == true) {
                        System.out.println("Eu já tenho uma espada, não posso carregar outra...");
                    }
                }
                if (cExp == 20) {
                    hp = hp + 2;
                    xp = xp + 2;
                    armado = true;
                    System.out.println("Eu encontrei uma sala do tesouro! Havia uma poção grande de cura e uma espada!");
                    if (armado == true) {
                        System.out.println("Eu já tenho uma espada, não posso carregar outra...");
                    }
                }
            }
        }  
        if (hp >= hp_max){
            hp = hp_max;
        }
        if (ep >= ep_max) {
            ep = ep_max;
        }
    }
    
    
    public String toString() {
        return "Calango : HP:" + hp + " ENERGIA:" + ep;
    }
}