package jornada;
import java.util.*;
public class Jornada extends heroi{

    public static void main(String[] args) {
        heroi h1 = new heroi();
        // Utilize os comandos:
        // h1.status() // Pra ver o status do Calango.
        // h1.atacar() // Pra enviar o calango á combate.
        // h1.descansar() // Pra fazer o calango descansar e recuperar vida/energia.
        // h1.explorar() // Pra explorar novas áreas da masmorra.
        
    }
}



class dado {
    public static int dado(int max, int min) {
        Random aleatorio = new Random();
        int d = aleatorio.nextInt((max - min) + 1) + min;
        return d;
    }
}
