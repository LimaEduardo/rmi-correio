import java.util.LinkedList;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Usuario implements Serializable {

	private String userName;
    private String senha;
    private LinkedList<Mensagem> listaDeMensagens;

    Usuario(String userName, String senha){
        this.userName = userName;
        this.senha = senha;
        this.listaDeMensagens = new LinkedList<Mensagem>();
    }

    public String getUserName(){
        return this.userName;
    }

    public String getSenha(){
        return this.senha;
    }

    public void adicionaMensagem(Mensagem m){
        this.listaDeMensagens.add(m);
    }

    public Mensagem getPrimeiraMensagem(){
        if(this.listaDeMensagens.size() == 0) {
            return null;
        }
        Mensagem mensagem = this.listaDeMensagens.pop();
        return mensagem;
    }

    public int getQuantidadeMensagens() {
        return this.listaDeMensagens.size();
    }

}