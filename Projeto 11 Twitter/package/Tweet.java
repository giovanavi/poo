package TwitterS11;

import java.util.TreeSet;

public class Tweet {
    int idTweet;
    String username;
    String msg;
    TreeSet<String> likes;
    
    public Tweet(int id, String username, String msg){
        this.idTweet = id;
        this.username = username;
        this.msg = msg;
        this.likes = new TreeSet<>();
    }
    
    public void like(String username){
        for(String nome : likes)
            if(nome.equals(username))
                return;
        likes.add(username);
    }
    
    public String toString(){
        String saida = "";
        saida +=idTweet+":"+username+"{ "+msg+"}";
        if(likes.size()>0){
            saida+="[ ";
            for(String nome : likes)
                saida+=nome+" ";
            saida+="]";
        }
        return saida;
    }
}
