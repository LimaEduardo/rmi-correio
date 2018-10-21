import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class Servidor implements Correio {
    private String endereco;
    private ArrayList<Usuario> usuarios;
    // private int porta;

    public Servidor(String endereco) {
        this.endereco = endereco;
        this.usuarios = new ArrayList<Usuario>();
        // this.porta = porta;
    }

    private boolean autenticar(String nome, String senha) {
        Usuario usuario = this.getUsuarioPorNome(nome);
        if(usuario == null) {
            return false;
        }
        if(usuario.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

    public Usuario getUsuarioPorNome(String nome) {
        for (Usuario usuario : this.usuarios){
            if(usuario.getUserName().equals(nome)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean jaExiste(String nome) {
        if(getUsuarioPorNome(nome) != null) {
            return true;
        }
        return false;
    }

    public boolean cadastrarUsuario (Usuario u) {
        if (this.jaExiste(u.getUserName())){
            return false;
        }
        this.usuarios.add(u);
        return true;
    }

    // Recupera a primeira mensagem na lista de mensagens do usuario; a mensagem deve ser removida
    // Exigir autenticação do usuário
    public Mensagem getMensagem (String userName, String senha) {
        return (new Mensagem("aaaaaaaaaaaaaaaa", "aaaaaa", "aaaaa"));
    }

    // retorna o número de mensagens na fila de mensagens dos usuário
    // Exigir autenticação do usuário
    public int getNMensagens (String userName, String senha) {
        Usuario usuario = this.getUsuarioPorNome(userName);
        if(usuario == null) {
            return -1;
        }
        if(!usuario.getSenha().equals(senha)) {
            return -1;
        }
        return usuario.getQuantidadeMensagens();
    }

    // Exigir autenticação do usuário (senha do remetente, incluído como atributo da mensagem)
    public boolean sendMensagem (Mensagem m, String senha, String userNameDestinatario) {
        System.out.println(m.getUserNameRemetente() + " " + userNameDestinatario);
        if(!this.autenticar(m.getUserNameRemetente(), senha)) {
            return false;
        }
        Usuario destino = this.getUsuarioPorNome(userNameDestinatario);
        if(destino == null) {
            return false;
        }
        destino.adicionaMensagem(m);
        return true;
    }

    public static void main(String args[]) {
        if(args.length != 1) {
            System.out.println("Modo de execucao do programa: ");
            System.out.println("java Servidor <endereco> <porta>");
            return;
        }
        try {
            System.out.println(args[0]);
            Servidor servidor = new Servidor(args[0]);
            System.setProperty("java.rmi.server.hostname", servidor.endereco);

            //Create and export a remote object
            Correio stub = (Correio) UnicastRemoteObject.exportObject(servidor,0);
            
            //Register the remote object with a Java RMI registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Correio", stub);

            System.out.println("Server Ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}