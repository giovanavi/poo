package ContatoS10;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        AgendaPlus agenda = new AgendaPlus();
        Scanner scanner = new Scanner(System.in);
            
        while(true){
            String line = scanner.nextLine();
            String[] in = line.split(" ");
            
            if(line.equals("end")){
                break;
            }else if(line.equals("show")){
                System.out.println(agenda);
            }else if(in[0].equals("add")){
                ArrayList<Fone> fones = new ArrayList<>();
                for(int i=2; i<in.length; i++){
                    fones.add(new Fone(in[i]));
                }
                try{
                    agenda.addContato(in[1], fones);
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }else if(in[0].equals("rmFone")){
                try{
                    agenda.rmFone(in[1], Integer.parseInt(in[2]));
                }catch(NullPointerException n){
                    System.out.println(n.getMessage());
                }catch(IndexOutOfBoundsException e){
                    System.out.println("fail: índice informado noa encontrado");
                }
            }else if(in[0].equals("rmContato")){
                try{
                    agenda.rmContato(in[1]);
                }catch(NullPointerException e){
                    System.out.println(e.getMessage());
                }
            }else if(in[0].equals("search")){
                agenda.search(in[1]);//nao retornou
            }else if(in[0].equals("star")){
                try{
                    agenda.bookmark(in[1]);
                }catch(NullPointerException e){
                    System.out.println(e.getMessage());
                }
            }else if(in[0].equals("unstar")){
                try{
                    agenda.unBookmark(in[1]);
                }catch(NullPointerException e){
                    System.out.println(e.getMessage());
                }
            }else if(in[0].equals("starred")){
                agenda.getBookmarks();
            }else{
                System.out.println("fail: comando invalido");
            }
        }
    }
}
