import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Cliente {

    private static int showMenu(){
        Scanner input1 = new Scanner( System.in );
        int op;
        do {
            System.out.println("------------------");
            System.out.println("1 - Cadastrar novo usuário");
            System.out.println("2 - Enviar mensagem para um usuário");
            System.out.println("3 - Recuperar mensagens");
            System.out.println("4 - Recuperar número de mensagens");
            System.out.println("0 - Sair");
            System.out.println("------------------");
            op = input1.nextInt();
        } while(op != 0 && op < 1 && op > 3);
        return op;
    }

    public Cliente(){

    }


    public static ArrayList<String> lerDadosUsuario() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Digite o nome do usuário: ");
        String nome = input.nextLine();
        System.out.println("Digite a senha do usuário: ");
        String senha = input.nextLine();

        ArrayList<String> dados = new ArrayList<String>();
        dados.add(nome);
        dados.add(senha);
        return dados;
    }
    
    public static ArrayList<String> leDadosMensagem() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Digite o titulo da mensagem: ");
        String titulo = input.nextLine();
        System.out.println("Digite o texto da mensagem: ");
        String texto = input.nextLine();
        System.out.println("Digite o destinatario da mensagem: ");
        String destinatario = input.nextLine();

        ArrayList<String> dados = new ArrayList<String>();
        dados.add(titulo);
        dados.add(texto);
        dados.add(destinatario);
        return dados;
    }
    public static void main (String args[]) {
        String host = (args.length < 1) ? null : args[0];
        int op = showMenu();
        try {
            Registry registry = LocateRegistry.getRegistry(host); 
            Correio correio = (Correio) registry.lookup("Correio");
            while (op != 0){
                ArrayList<String> dados;
                boolean response;
                switch(op){
                    case 1:
                        // Cadastrar novo usuário
                        dados = lerDadosUsuario();
                        Usuario usuario = new Usuario(dados.get(0), dados.get(1));
                        response = correio.cadastrarUsuario(usuario);
                        if(response) {
                            System.out.println("Usuario cadastrado com sucesso");
                        } else {
                            System.out.println("Não foi possível cadastrar este usuário.");
                        }
                        break;
                    case 2:
                        // Enviar mensagem para o usuário
                        dados = lerDadosUsuario();
                        ArrayList<String> dadosMensagem = leDadosMensagem();
                        
                        Mensagem mensagem = new Mensagem(dados.get(0), dadosMensagem.get(0), dadosMensagem.get(1));
                        
                        response = correio.sendMensagem(mensagem, dados.get(1), dadosMensagem.get(2));
                        if (response){
                            System.out.println("Mensagem enviada com sucesso");
                        } else {
                            System.out.println("Falha no envio da mensagem");
                        }
                        System.out.println(response);
                        break;
                    case 3:
                        // Recuperar mensagem
                        break;
                    case 4:
                        // Recuperar o número de mensagens
                        dados = lerDadosUsuario();
                        int numeroMensagens = correio.getNMensagens(dados.get(0), dados.get(1));
                        if(numeroMensagens == -1) {
                            System.out.println("Falha de autenticacao");
                        } else {
                            System.out.println("Número de mensagens: " + numeroMensagens);
                        }
                        System.out.println();
                        break;
                    case 0:
                        System.out.println("Fim da execucao");
                        break;
                    default:
                        System.out.println("Opcao invalida");
                }
                op = showMenu();
            }
        } catch (Exception e) {
            System.err.println("Cliente exception: " + e.toString());
            e.printStackTrace();
        }
    }

}