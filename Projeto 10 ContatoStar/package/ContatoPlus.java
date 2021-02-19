package ContatoS10;

public class ContatoPlus extends Contato{
    boolean starred;
    
    public ContatoPlus(String nome) {
        super(nome);
        this.starred = false;
    }

    public boolean getStarred(){
        return starred;
    }
    
    public void setStarred(int valor){
        
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
