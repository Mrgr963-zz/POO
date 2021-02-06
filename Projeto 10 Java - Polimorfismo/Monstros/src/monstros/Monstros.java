package monstros;
import java.util.*;

interface Monster {
    String getNome();
    void atacar();
    void intimidar();
}

class MonstroBase {
    String nome;
    boolean alive = true;
    
    MonstroBase (String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
}

class Slime extends MonstroBase implements Monster {
    int tamanho;
    
    public Slime(String nome, int tam) {
        super(nome);
        this.nome = nome;
        this.tamanho = tam;
    }
    
    @Override
    public void atacar() {
        System.out.println("O Slime engulfa o inimigo!");
    }

    @Override
    public void intimidar() {
        System.out.println("Glub...");
    }
}

class SlimeGigante extends Slime {
    
    public SlimeGigante (String nome,int tam) {
        super(nome, tam);
        this.nome = nome;
        this.tamanho = tam;
    }
    
    @Override
    public void atacar() {
       if (tamanho > 0) {
           tamanho -= 1;
           System.out.println("O slime é muito grande! O inimigo o atacou!");
       } else {
           System.out.println("O slime perdeu muito tamanho, agora ele pode atacar!");
           super.atacar();
       }
    }
    
    @Override
    public void intimidar() {
        if (tamanho > 0) {
            System.out.println("Blob blob blob...");
        } else {
            System.out.println("O Slime está muito pequeno e emite menos ruido!");
            super.intimidar();
        }
    }

}

public class Monstros {
    public static void main(String[] args) {
        ArrayList<Monster> monstros = new ArrayList<Monster>();
        monstros.add(new Slime("Slime azul", 2));
        monstros.add(new Slime("Cubo Gelatinoso", 2));
        monstros.add(new SlimeGigante("Pudim Negro", 2));
        
        
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        for (int i = 0; i < monstros.size(); i++) {
            System.out.println(monstros.get(i).getNome());
        }
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        
        for (int j = 0; j < 4; j++) {
            System.out.println(monstros.get(0).getNome());
            monstros.get(0).atacar();
            monstros.get(0).intimidar();
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println(monstros.get(1).getNome());
            monstros.get(1).atacar();
            monstros.get(1).intimidar();
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println(monstros.get(2).getNome());
            monstros.get(2).atacar();
            monstros.get(2).intimidar();
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        }
    }
}
