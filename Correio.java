import java.util.ArrayList;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Correio extends Remote {
  
    public boolean cadastrarUsuario (Usuario u) throws RemoteException;
    
    //Função para autenticação do usuário
    // public boolean autenticar(String nome, String senha) throws RemoteException;

    // Recupera a primeira mensagem na lista de mensagens do usuario; a mensagem deve ser removida
    // Exigir autenticação do usuário
	public Mensagem getMensagem (String userName, String senha) throws RemoteException;
    
    // retorna o número de mensagens na fila de mensagens dos usuário
    // Exigir autenticação do usuário
    public int getNMensagens (String userName, String senha) throws RemoteException;
	
	// Exigir autenticação do usuário (senha do remetente, incluído como atributo da mensagem)
	public boolean sendMensagem (Mensagem m, String senha, String userNameDestinatario) throws RemoteException;
}
