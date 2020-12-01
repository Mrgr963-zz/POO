# Relatório do Exercício S02E01 - João Victor (G.R) Serra Souza 

## Índice []()
<!--TOC_BEGIN-->
- [Relatório do Exercício S02E01](#Exercício-S02E01)
<!--TOC_END-->

## Exercício-S02E01
**A Jornada do Calango**

**1)**
Em minha atividade tentei moldar o exemplo do calango pra algo que eu goste, um Dungeon-Crawler, gênero de jogo de RPG muito popular na época do DOS. Minha ideia principal era já adaptar o código a partir de um pequeno projeto a parte que eu havia de feito durante a cadeira de FUP, onde testei diversos mecanismos com base na geração de números aleatórios.
Usando esses sistemas, procurei fazer algo simples, porém nos moldes dos jogos que eu tanto gosto, onde o jogador poderia atacar monstros, se curar de seus ferimentos, explorar mapas e evoluir seu personagem.

**2)**
Fiz o trabalho todo sozinho, com apenas a ajuda de um colega meu que entende melhor de Java, ele me ajudou a resolver algumas dúvidas que eu tinha sobre a definição das classes e sintaxes que ainda tenho dificuldade, bem como me explicar como importar funções da biblioteca do Java, que foi de grande ajuda. Fora ele, utilizei alguns sites da internet pra entender melhor o código de geração de números aleatórios, e o exemplo dado pelo professor pra usar como base.

**3)**
No geral utilizei o Netbeans como IDE, fiz inicialmente três arquivos de classe java diferente, um para os comandos, outro para o personagem do heróis calango e um ultimo pra usar na geração de números aleatórios. Devido a alguns problemas na parte de importar a classe que continha o "rolador de dados", resolvi deixá-lo no arquivo dos comandos, este foi o primeiro objeto que criei.
Utilizando dos conceitos abordados pelo professor, bem como meu conhecimento geral sobre o modelo do jogo, comecei criando o herói. Criei os atributos que iriam ser utilizadas no jogo e logo em seguida utilizei o construtor para colocar seus valores iniciais. Criei também um objeto do herói no arquivo principal e testes, tudo certo.
Depois de criar os atributos, comecei a fazer os métodos, começando pelo de ataque, onde, a princípio um número aleatório decidiria a dificuldade do combate, que é dividido em 4 dificuldades de acordo com a rolagem do dado, sendo o 1 o mais dificil, onde o herói sofreria mais dano, e a dificuldade 4 a mais fácil, onde o herói receberia mais XP . Este sistema foi pego com base nas tabelas de encontros aleatório de RPG's de mesa como AD&D, D&D 5° Edição, Dungeon World e Iron Sworn, onde, quando um heróis anda por uma Masmorra, ele pode encontrar diversas criaturas que tem suas respectivas dificuldades de combate estabelecidas a partir da rolagem de um dado (Que normalmente é um dado de 20 lados).
Após realizar o método de combate e testar as porcentagens, adicionei alguns "verificadores", estes "verificadores" foram colocados no começo e no fim do código pra verificar se o personagem estava morto, sem energia ou desarmado, se os verificadores identificassem alguma dessas características, eles definiam um resultado especifico pro curso de ação do herói.
Após realizar o método de atacar, segui para o método de descanso, onde seria a principal forma do personagem recuperar energia e vida. Neste método eu me utilizei como referencia o código de descanso do calango, visto em sala de aula. Utilizei também um gerador de números aleatórios para ter a chance do herói ser atacado durante seu sono. Quando o herói estivesse com sua energia completa, apenas assim ele poderia recuperar vida. Por fim, adicionei os verificadores para verificar se o personagem estava com hp/energia cheia e/ou morto.
Após o descanso, fiz o método de exploração, que semelhante ao método de ataque constaria com 4 possiveis "destinos" para o herói, sendo o destino n° 1 o pior, onde o herói perderia muita vida e energia, e o destino n° 4, onde ele encontraria tesouros e armas novas! Diferente dos combates e do descanso, a exploração é algo mais recompensador, onde o herói pode evoluir sem necessáriamente enfrentar monstros (a depender de sua sorte)

**4)**
Aprendi muita coisa sobre a sintaxe e algumas peculiariedades sobre as classes do Java bem como da linguagem no geral, como vim do JS, é algo completamente diferente e ainda me parece bem complexo, consegui dominar pelo menos a criação de métodos para um objeto, sua invocação e a manipulação de seus atributos/estados, bem como os conceitos de exportação e o comando extend. Também entendi como funciona um gerador de números aleatórios no Java e alguns por menores que nem sabia que seriam problema!
Minha maior dificuldade, no geral foi entender como utilizar uma classe em um arquivo diferente, isso me deu uma baita dor de cabeça por algumas boas horas, bem como o que significava cada parte da criação das classes e funções (O public,void,static...). Mas ao todo deu pra utilizar vários do conhecimento de lógica que aprendi em FUP nesta atividade.

**5)**
Depois do término da aula fui direto pro Netbeans começar o trabalho, terminei o código era próximo das 22:00, porém, durante todo o processo me dei um tempo pra jantar, tomar banho e descansar. Ao total, creio que levou umas 3 horas de processo até o fim do código.