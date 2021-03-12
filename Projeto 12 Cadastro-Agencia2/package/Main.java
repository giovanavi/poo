package CadastroS12;

import java.util.Scanner;

public class Main{
    public static void main(String[]args){
        Agencia agencia = new Agencia();
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            String line = scanner.nextLine();
            String[] in = line.split(" ");
            
            if(in[0].equals("end")){
                break;
            }else if(in[0].equals("show")){
                System.out.println(agencia);
            }else if(in[0].equals("addCli")){
                try{
                    agencia.addCliente(in[1]);
                }catch(IllegalArgumentException e){
                    System.out.println("fail: cliente ja existe");
                }
            }else if(in[0].equals("deposito")){
                try{
                    agencia.depositar(Integer.parseInt(in[1]), Float.parseFloat(in[2]));
                }catch(IllegalArgumentException e){
                    System.out.println("fail: valor invalido");
                }catch(NullPointerException n){
                    System.out.println("fail: conta inexistente");
                }
            }else if(in[0].equals("saque")){
                try{
                    agencia.sacar(Integer.parseInt(in[1]),Float.parseFloat(in[2]));
                }catch(IllegalArgumentException e){
                    System.out.println("fail: valor invalido");
                }catch(NullPointerException n){
                    System.out.println("fail: conta inexistente");
                }
            }else if(in[0].equals("transf")){
                try{
                    agencia.transferir(Integer.parseInt(in[1]), Integer.parseInt(in[2]), Float.parseFloat(in[3]));
                }catch(IllegalArgumentException e){
                    System.out.println("fail: valor invalido");
                }catch(NullPointerException n){
                    System.out.println("fail: conta inexistente");
                }
            }else if(in[0].equals("update")){
                agencia.attMensalContas();  
            }else{
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
