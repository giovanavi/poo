
package TwitterS11;

import java.util.Scanner;

public class Main{
    public static void main(String[]args){  
        Controller sistema = new Controller();
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            String line = scanner.nextLine();
            String in[] = line.split(" ");
            try{
                if(line.equals("end")){
                    break;
                }else if(in[0].equals("addUser")){
                    sistema.addUser(in[1]);
                }else if(line.equals("show")){
                    System.out.println(sistema);
                }else if(in[0].equals("follow")){
                    Usuario one = sistema.getUser(in[1]);
                    Usuario two = sistema.getUser(in[2]);
                    one.follow(one, two);
                }else if(in[0].equals("unfollow")){
                    Usuario one = sistema.getUser(in[1]);
                    Usuario two = sistema.getUser(in[2]);
                    one.unfollow(one, two);                
                }else if(in[0].equals("timeline")){
                    Usuario user = sistema.getUser(in[1]);
                    System.out.println(user.getTimeline());
                }else if(in[0].equals("like")){
                    Usuario user = sistema.getUser(in[1]);
                    Tweet tw = user.getTweet(Integer.parseInt(in[2]));
                    tw.like(in[1]);
                }else if(in[0].equals("twittar")){
                    String msg = "";
                    for(int i = 2; i<in.length; i++)
                        msg+=in[i]+" ";
                    sistema.sendTweet(in[1], msg);
                }else{
                    System.out.println("fail: comando invalido");
                }
            }catch(NullPointerException ex){
                System.out.println(ex.getMessage());
            }
        } 
    }
}