package twitter;
import java.util.*;

class Tweet {
    private int idTw;
    private String username;
    private String msg;
    private TreeSet <String> likes;
    
    public Tweet (int id, String username, String msg) {
        this.idTw = id;
        this.username = username;
        this.msg = msg;
        this.likes = new TreeSet<>();
    }
   
    public void like(String username) {
        likes.add(username);
    }
    
    public int getIdTw() {
        return idTw;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public String toString() {
        String aux = "";
        aux += idTw + ":" + username + " (" + msg + ")" + likes;
        return aux;
    }
}

class User {
    private String username;
    private Map<String, User> followers;
    private Map<String, User> following;
    private Map<Integer, Tweet> timeline;
    private int unreadCount;
    
    public User (String username) {
        this.following = new TreeMap<>();
        this.followers = new TreeMap<>();
        this.timeline = new TreeMap<>();
        this.username = username;
        this.unreadCount = 0;
    }
    
    public void follow (User user) {
        if (following.containsKey(user.getUsername())) {
            throw new RuntimeException("\"ERROR: USUÁRIO JÁ ESTÁ SENDO SEGUIDO!\"");
        } else {
            following.put(user.getUsername(), this);
            user.followers.put(this.getUsername(), this);
            System.out.println("VOCÊ SEGUIO O USUÁRIO!");
        }
    }
    
    public void unfollow (String username) {
        User user = following.get(username);
        
        if (user == null) {
            throw new RuntimeException("\"ERROR: VOCÊ NÃO SEGUE ESTE USUÁRIO!\"");
        } else {
            following.remove(username);
            user.followers.remove(this.username);
            System.out.println("VOCÊ PAROU DE SEGUIR O USUÁRIO!");
        }
    }
    
    public Tweet getTweet (int idTw) {
        Tweet tweet = timeline.get(idTw);
        tweet.like(getUsername());
        System.out.println("VOCÊ CURTIU O TWEET!");
        
        return tweet;
    }
    
    public void sendTweet(Tweet tweet) {
        this.timeline.put(tweet.getIdTw(),tweet);
        this.unreadCount += 1;
        
        for (User user : followers.values()) {
            user.getUsername();
            user.timeline.put(tweet.getIdTw(), tweet);
            user.unreadCount += 1;
        }
    }
    
    public String getUnread() {
        String aux = "";
        Map <Integer, Tweet> tl = new TreeMap<>();
        
        for (int i = timeline.size() - this.unreadCount; i < timeline.size(); i++) {
            tl.put(i, timeline.get(i));
        }
        return aux;
    }
    
    public String getTimeline() {
        String aux = "";
        Map <Integer, Tweet> tl = new TreeMap<>();
        for(int i=timeline.size() - this.unreadCount; i < timeline.size(); i++){
            tl.put(i, timeline.get(i));   
        }
        if(this.unreadCount==0){
            throw new RuntimeException("não há novos tweets");
        }
        unreadCount = 0;
        for (Tweet tweets : tl.values()) {
            aux += tweets;
        }
        return aux;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String toString(){
        String aux = "";
        
            aux += getUsername() + "\n" + "Followers " + followers.keySet();
            aux +="\n" + "Following " + following.keySet();
        return aux;
    }
}

class Controller {
    private Map <String, User> users;
    private Map<Integer, Tweet> tweets;
    int nextTwId;
    
    public Controller() {
        this.users = new TreeMap<>();
        this.tweets = new TreeMap<>();
    }
    
    public void sendTweet(String username, String msg) {
        Tweet tweet = new Tweet(nextTwId, username, msg);
        User user = getUser(username);
        
        if(!users.containsKey(username)){
            throw new RuntimeException("\"ERROR: USUÁRIO INEXISTENTE!\"");
        }
        tweets.put(nextTwId, tweet);
        user.sendTweet(tweet);
        nextTwId = nextTwId + 1;
        System.out.println("\"TWEET FEITO!\"");
    }
    
    public void addUser(String username) {
        User user = new User(username);
        
        if(!users.containsKey(username)){
            users.put(username, user);
            System.out.println("NOVO USUÁRIO CRIADO!");
        } else {
            System.out.println("USUÁRIO JÁ REGISTRADO!");
        }
    }
    
    public User getUser(String username){
        User user = users.get(username);
        
        if(user == null){
            throw new RuntimeException("\"ERROR: USUÁRIO INEXISTENTE!\"");
        } return user;
    }
    
    public String toString() {
        String aux = "";
        
        for(User user : users.values()){
            aux += user + "\n" + "\n";
        } return aux;    
    }
}

public class Twitter {
    public static void main(String[] args) {
        Controller c1 = new Controller();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            try {
                String line = scanner.nextLine();
                String ui[] = line.split(" ");
                
                if (ui[0].equals("stop")) {
                    break;
                } else if (ui[0].equals("show")) {
                    System.out.println(c1);
                } else if (ui[0].equals("addUser")) {
                    c1.addUser(ui[1]);
                } else if (ui[0].equals("follow")) {
                    User user1 = c1.getUser(ui[1]);
                    User user2 = c1.getUser(ui[2]);
                    
                    user1.follow(user2);
                } else if (ui[0].equals("twittar")) {
                    String user = ui[1];
                    String msg = "";
                    
                    for (int i = 2; i < ui.length; i++) {
                        msg += ui[i] + " ";
                    }
                    c1.sendTweet(user, msg);
                } else if (ui[0].equals("like")) {
                    User user = c1.getUser(ui[1]);
                    Tweet tweet = user.getTweet(Integer. parseInt(ui[2]));
                    
                    tweet.like(ui[1]);
                } else if (ui[0].equals("unfollow")) {
                    User user1 = c1.getUser(ui[1]);
                    
                    user1.unfollow(ui[2]);
                } else if (ui[0].equals("timeline")) {
                    User user = c1.getUser(ui[1]);
                    
                    System.out.println(user.getTimeline());
                }
                else {
                    throw new RuntimeException("\"ERROR: COMANDO INVÁLIDO!\"");
                }
            }
            catch (RuntimeException error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
