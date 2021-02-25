package ContatoS10;

import java.util.List;

public class ContatoPlus extends Contato{
    boolean starred;

    public ContatoPlus(String nome, List<Fone> fones) {
        super(nome, fones);
        this.starred = false;
    }
    
    public boolean getStarred(){
        return starred;
    }
    
    public void setStarred(){
        if(starred == true)
            starred = false;
        else
            starred = true;
    } 
    
    public String toString(){
        String saida = "";
        if(starred==true)
            saida+="@ "+getName();
        else
            saida+="- "+getName();
        int i=0;
        for(Fone fone : fones){
            if(starred==true)
                saida +=" ["+i+":"+fone.toString()+"]";
            else
                saida +=" ["+i+":"+fone.toString()+"]";
            i++;
        }
        return saida;
    }
  
}
