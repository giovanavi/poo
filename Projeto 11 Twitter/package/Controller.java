package TwitterS11;

import java.util.Scanner;
import java.util.TreeMap;

public class Controller {
    TreeMap<String, Usuario> usuarios;
    TreeMap<String, Usuario> tweets;
    int nextIdTw;
    
    public Controller(){
        this.usuarios =  new TreeMap<>();
        this.tweets =  new TreeMap<>();
    }
    
    public void addUser(String username){
        if(!(usuarios.containsKey(username))){
            Usuario user = new Usuario(username);
            usuarios.put(username, user);
        }else{
            System.out.println("fail: usuario existente");
        }
            
    }    
    
    public void sendTweet(String username, String msg){
        Usuario user = this.getUser(username);       
        Tweet tweet = new Tweet(nextIdTw++, username, msg);
        user.sendTweet(tweet);
    }

    
    public Usuario getUser(String username){
        if(usuarios.containsKey(username))
            return usuarios.get(username);
        else        
           throw new NullPointerException("fail: usuario nao encontrado");
    }
    
    public String toString(){
        String saida = "";
        for(Usuario user : usuarios.values())
            saida+=user.toString()+"\n";
        return saida;
    }
}