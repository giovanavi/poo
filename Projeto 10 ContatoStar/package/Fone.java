package ContatoS10;

public class Fone {
    String number;
    String id;
    
    public Fone(String id, String number){
        this.id = id;
        this.number = number;
    }
    public Fone(String serial){
        String vetor[] = serial.split(":");
        id = vetor[0];
        number = vetor[1];
    }
    
    public static boolean validade(String number){
        String validos = "1234567890()-";
        for(int i=0; i<number.length(); i++){
            char c = number.charAt(i);
            if(validos.indexOf(c)==-1){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString(){
        return id+":"+number;
    }    
}
