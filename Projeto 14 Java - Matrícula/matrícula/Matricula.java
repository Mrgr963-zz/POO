import java.util.*;

class Discp {
    String id;
    Map<String,Aluno> alunos;

    public Discp(String id) {
        this.id = id;
        this.alunos = new TreeMap<>();
    }

    public void matricular(Aluno aluno) {
        if (alunos.containsKey(aluno.id)) {
            throw new RuntimeException("ERROR: Aluno já matriculado!");
        } else {
            alunos.put(aluno.id,aluno);
        }
    }

    public void desmatricular(String idAluno) {
        if (alunos.get(idAluno) != null) {
            alunos.remove(idAluno);
        } else {
            throw new RuntimeException("ERROR: ALUNO NÃO ENCONTRADO!");
        }
    }

    boolean checkAluno(String idAluno){
        if(this.alunos.get(idAluno) != null){
            return true;
        }
        return false;
    }

    public List<Aluno> getAlunos() {
        List<Aluno> lista = new ArrayList<Aluno>();

        for (int i = 0; i < alunos.size(); i++) {
            lista.add(alunos.get(i));
        }
        return lista;
    }

    public String getId() {
        return id;
    }

    public String toString(){
        String aux = "Alunos:\n";
        for(Map.Entry<String, Aluno> entry : this.alunos.entrySet()){
            aux += entry.getValue() + "\n";
        }
        return aux;
    }
}

class Aluno {
    String id;
    Map<String, Discp> m_discp;

    public Aluno(String id) {
        this.id = id;
        this.m_discp = new TreeMap<String, Discp>();
    }

    public void matricular(Discp discp) {
        if (m_discp.containsKey(discp.id)) {
            throw new RuntimeException("ERROR: DISCIPLINA JÁ MATRICULADA!");
        }
        else {
            m_discp.put(discp.id,discp);
        }
    }

    public void desmatricular(String idDiscp) {
        if (m_discp.get(idDiscp) != null) {
            m_discp.remove(idDiscp);
        } else {
            throw new RuntimeException("ERROR: DISCIPLINA NÃO MATRICULADA!");
        }
    }

    public List<Discp> getDisciplinas() {
        List<Discp> lista = new ArrayList<>();

        for (int i = 0; i < m_discp.size(); i++) {
            lista.add(m_discp.get(i));
        }
        return lista;
    }
    
    public String getId() {
        return id;
    }

    public String toString(){
        String aux = this.id + " [ ";
        for(Map.Entry<String, Discp> entry : this.m_discp.entrySet()){
            aux += entry.getKey() + " ";
        }
        aux += "]";
        return aux;
    }
}

class Sistema {
    Map<String, Aluno> alunos;
    Map<String, Discp> discips;

    public Sistema() {
        this.alunos = new TreeMap<>();
        this.discips = new TreeMap<>();
    }

    public void addAluno(String idAluno) {
        if (alunos.containsKey(idAluno)) {
            throw new RuntimeException("ERROR: Aluno já matriculado!");
        } else {
            alunos.put(idAluno, new Aluno(idAluno));
        }
    }

    public void addDiscp(String idDiscp) {
        if (discips.containsKey(idDiscp)) {
            throw new RuntimeException("ERROR: DISCIPLINA JÁ MATRICULADA!");
        }
        else {
            discips.put(idDiscp, new Discp(idDiscp));
        }
    }

    public void matricular(String idAluno, String idDisc) {
        Aluno aluno = getAluno(idAluno);
        String ui[] = idDisc.split(" ");

        for(int i = 0; ui.length > i; i++){
            Discp discp = getDiscp(ui[i]);
            if(discp.checkAluno(aluno.id) == true){
                System.out.println("ERROR: ALUNO JÁ MATRICULADO NESTA DISCIPLINA!");
                return;
            }
            aluno.matricular(discp);
            discp.matricular(aluno);
        }
    }

    public void desmatricular(String idAluno, String idDisc) {
        Aluno aluno = getAluno(idAluno);
        String ui[] = idDisc.split(" ");

        for(int i = 0; ui.length > i; i++){
            Discp discp = getDiscp(ui[i]);
            if(discp.checkAluno(aluno.id) == false){
                System.out.println("ERROR: ALUNO NÃO ESTÁ MATRICULADO NESTA DISCIPLINA!");
                return;
            }
            aluno.desmatricular(idDisc);
            discp.desmatricular(idAluno);
        }
    }

    public void rmAluno(String idAluno) {
        Aluno aluno = getAluno(idAluno);

        for(Map.Entry<String, Discp> entry : aluno.m_discp.entrySet()){
            entry.getValue().desmatricular(idAluno);
        }
    }

    boolean checkAluno(String idAluno){
        if(this.alunos.get(idAluno) != null){
            return true;
        }
        return false;
    }

    Aluno getAluno(String alunoNome){
        if(checkAluno(alunoNome) == false){
            throw new RuntimeException("ERROR: ALUNO NÃO MATRICULADO!");
        }
        return this.alunos.get(alunoNome);
    }

    boolean checkDiscp(String idDiscp){
        if(this.discips.get(idDiscp) != null){
            return true;
        }
        return false;
    }

    Discp getDiscp(String idDiscp){
        if(checkDiscp(idDiscp) == false){
            throw new RuntimeException("ERROR: DISCIPLINA NÃO REGISTRADA!");
        }
        return this.discips.get(idDiscp);
    }

    public String toString(){
        String aux = "Alunos:\n";
        for(Map.Entry<String, Aluno> entry : this.alunos.entrySet()){
            aux += entry.getValue() + "\n";
        }
        aux += "\nMaterias:\n";
        for(Map.Entry<String, Discp> entry : this.discips.entrySet()){
            aux += entry.getValue() + "\n";
        }
        return aux;
    }
}


public class Matricula {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema s1 = new Sistema();

        while (true) {
            try {
                String line = scanner.nextLine();
                String ui[] = line.split(" ");

                if (ui[0].equals("stop")) {
                    break;
                } else if (ui[0].equals("show")) {
                    System.out.print(s1);
                } else if (ui[0].equals("nwalu")) {
                    for(int i = 1; ui.length > i; i++){
                        s1.addAluno(ui[i]);
                    }
                } else if (ui[0].equals("tie")) {
                    for(int i = 2; ui.length > i; i++){
                        s1.matricular(ui[1], ui[i]);
                    }
                } else if (ui[0].equals("untie")) {
                    for(int i = 2; ui.length > i; i++){
                        s1.desmatricular(ui[1], ui[i]);
                    }
                } else if (ui[0].equals("rmalu")) {
                    s1.rmAluno(ui[1]);
                } else {
                    System.out.println("ERROR: COMANDO INVÁLIDO!");
                }
            }
            catch (RuntimeException error) {
                System.out.println(error.getMessage());
            }
        }

    }
}

