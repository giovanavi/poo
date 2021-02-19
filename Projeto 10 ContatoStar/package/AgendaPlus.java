package ContatoS10;

import java.util.TreeMap;

public class AgendaPlus extends Agenda{
    TreeMap<String, ContatoPlus> bookmarks;
    
    public AgendaPlus(){
        this.bookmarks = new TreeMap<>();
    }
    
    @Override
    public boolean rmContato(String nome){
        if(!(bookmarks.containsKey(nome))){
            throw new NullPointerException("fail: contato informado não encontrado");
        }
        if(contatos.get(nome).starred == true){
            bookmarks.remove(nome);
            contatos.remove(nome);
            System.out.println("contato removidofav");
            return true;
        }
        contatos.remove(nome);
        System.out.println("contato removido");
        return true;
    }    
    
    public void bookmark(String id){
        if(!(contatos.containsKey(id))){
            throw new NullPointerException("fail: contato informado não encontrado");
        }
        ContatoPlus contato = getContato(id);
        contato.starred = true;
        bookmarks.put(id, contato);
        System.out.println("contato favoritado");          
    }
    
    public void unBookmark(String id){
        if(!(contatos.containsKey(id))){
            throw new NullPointerException("fail: contato informado não encontrado");
        } 
        ContatoPlus contato = getContato(id);        
        if(contato.starred == true){
            contato.starred = false;
            bookmarks.remove(id);
            System.out.println("tirado de favoritos");
        }     
    }
    
    public void getBookmarks(){
        for(ContatoPlus contato : bookmarks.values())
            System.out.println(contato);
    }
}
