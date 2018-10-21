import java.util.Date;
import java.io.Serializable;


public class Mensagem implements Serializable {

	private String userNameRemetente;
    private String titulo;
    private String texto;
    private Date data;
}