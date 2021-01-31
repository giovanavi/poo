package Vetores;

import java.util.ArrayList;

class Msg{
    int id;
    String sender;
    String text;
    
    public Msg(int id,String nome, String text){
        this.id = id;
        this.sender = nome;
        this.text = text;
    }
    public String toString(){
        return id+":"+sender+":"+text;
    }
}

        
class Usuario {
    String username;
    ArrayList<Msg> inbox;
    int contNaoLidos;
    
    public Usuario(String nome){
        this.username = nome;
        this.inbox = new ArrayList<Msg>();
        contNaoLidos = 0;
    } 
    
    public void sendMsg(int idMsg, Usuario dest, String text){
        Msg msg = new Msg(idMsg, this.username, text);
        dest.inbox.add(msg); 
        dest.contNaoLidos ++;
    }
    
    public ArrayList<Msg> getInbox(){
        ArrayList<Msg> output = new ArrayList<Msg>();
        for(int i=inbox.size()-contNaoLidos; i<inbox.size(); i++)
            output.add(inbox.get(i));
        contNaoLidos = 0;
        return output;
    }

}

class Sistema{
    ArrayList<Usuario> usuarios;
    int idNextMsg;
    
    public Sistema(){
        this.usuarios = new ArrayList<>();
    }
    
    public Usuario getUser(String username){
        for(Usuario usuario : usuarios)
            if(usuario.username.equals(username))
                return usuario;
        return null;
    }
    
    public void addUser(String username){
        if(this.getUser(username)!=null){
            System.out.println("user ja existe;");
            return;
        }
        usuarios.add(new Usuario(username));
    }
   
    void sendMsg(String sender, String receiver, String text){
        Usuario one = this.getUser(sender);
        Usuario two = this.getUser(receiver);
        if(one==null){
            System.out.println(sender+" nao existe");
            return;
        }
        if(two==null){
            System.out.println(receiver+" nao existe");
            return;
        }
        one.sendMsg(idNextMsg, two, text);
        idNextMsg ++;
    }
    
    String readMsg(String username){
        Usuario one = this.getUser(username);
        if(one==null){
            System.out.println(username+" nao existe");
            return "inbox vazio";
        }
        String saida = "";
        for(Msg msg : one.getInbox()){
            saida += msg+" ";
        }
        return saida;
    }
}


public class Mensagem{
    public static void main(String[]args){
        Sistema sistema = new Sistema();

        sistema.addUser("pedro");
        sistema.addUser("edvaldo");
        sistema.addUser("yago");
        
        sistema.sendMsg("yago", "pedro", "paga veaco");
        System.out.println(sistema.readMsg("pedro"));
        sistema.sendMsg("pedro", "edvaldo", "corre aqui");
        System.out.print(sistema.readMsg("pedro"));
        System.out.print(sistema.readMsg("edvaldo"));
        sistema.sendMsg("edvaldo", "pedro", "to esperando");
        System.out.println(sistema.readMsg("pedro"));
       
    }
}