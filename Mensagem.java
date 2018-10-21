import java.util.Date;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Mensagem implements Serializable {

	private String userNameRemetente;
    private String titulo;
    private String texto;
    private Date data;

    Mensagem(String userNameRemetente, String titulo, String texto){
        this.userNameRemetente = userNameRemetente;
        this.titulo = titulo;
        this.texto = texto;
        this.data = new Date();
    }

    public String getUserNameRemetente(){
        return this.userNameRemetente;
    }

    public String toString(){
        String resposta = "DE: " + this.userNameRemetente + " \n" +
        "TÍTULO: " + this.titulo + "\n" +
        "DATA: " + this.data + "\n" +
        "TEXTO: " + this.texto + "\n";
        return resposta;        
    }
}