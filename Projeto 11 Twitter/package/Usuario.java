package TwitterS11;

import java.util.ArrayList;
import java.util.TreeMap;

public class Usuario {
    String username;
    int contNlidos;
    TreeMap<String, Usuario> followers;
    TreeMap<String, Usuario> following;
    ArrayList<Tweet> timeline;
    
    public Usuario(String username){
        this.contNlidos = 0;
        this.username = username;
        this.followers = new TreeMap<>();
        this.following = new TreeMap<>();
        this.timeline = new ArrayList<>();
    }
    public void follow(Usuario follows, Usuario other){
//other entra na minha lista de seguidos(following)
//follows entra na lista de seguidores(followers) de other
        if(follows.following.containsKey(other.username))
            return;       
        follows.following.put(other.username, other);
        other.followers.put(follows.username, follows);                
    }
   
    public void unfollow(Usuario unfollow, Usuario other){
//tirar other da minha lista de following
//sair da lista de folowers de other
        if(unfollow.following.containsKey(other.username)){
            unfollow.following.remove(other.username);
            other.followers.remove(unfollow.username);
            return;
        }
        throw new NullPointerException("fail: usuario nao encontrado");    
    }
  
    public void sendTweet(Tweet tweet){
        timeline.add(tweet);
        for(Usuario seguidores : followers.values()){
            seguidores.timeline.add(tweet);
            seguidores.contNlidos++;
        }
    }
    
    public Tweet getTweet(int idTweet){
        for(Tweet tw : timeline){
            if(tw.idTweet==idTweet)
                return tw;
        }
        throw new NullPointerException("fail: tweet nao existe");
    }  
    
    public String getUnread(){
        String saida = "";
        int aux = timeline.size() - contNlidos;
        for(int i=aux; i<timeline.size(); i++){
            saida+=timeline.get(i)+"\n";
        }
        contNlidos=0;
        return saida;
    }
    
    public String getTimeline(){
        String saida="";
        for(Tweet tweet : timeline)
            saida+=tweet+"\n";
        return saida;
    }    

    public TreeMap<String, Usuario> getSeguidos(){
        return following;
    }
 
    public TreeMap<String, Usuario> getSeguidores(){
        return followers;
    }    
    
    public String toString(){
        String saida=username+"\n  Seguidos   [ ";
        for(Usuario user : following.values())
            saida+=user.username+" ";
        saida+="]\n  Seguidores [ ";
        for(Usuario user : followers.values())
            saida+=user.username+" ";
        saida+="]";
        return saida;
    }
}
