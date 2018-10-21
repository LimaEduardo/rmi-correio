import java.util.LinkedList;
import java.io.Serializable;

public class Usuario implements Serializable {

	private String userName;
    private String senha;
    private LinkedList<Mensagem> listaDeMensagens;

}