package Client;

//import java.net.InetAddress;
import java.net.UnknownHostException;

public class Aluno implements java.io.Serializable{
    private String ip = null;
    
    private int porta = 0;
    private String nickName = null;
    private String email = null;
    private String curso = null;
    
    public Aluno(String ip, String nick, String em, String cur, int por) throws UnknownHostException { 
        /*InetAddress myself = InetAddress.getLocalHost ();
        ip=myself.getHostAddress();*/
        this.ip = ip;
      
        this.porta = por;
        this.nickName = nick;
        this.email = em;
        this.curso = cur;
    }
    
    public String getCurso() {
        return this.curso;
    }
    
    public void setCurso(String cur) {
        this.curso = cur;
    }
    
    public String getEmail() {
       return this.email;
    }
    
    public void setEmail(String em) {
        this.email = em;
    }
    
    public String getNickName() {
        return this.nickName;
    }
    
    public void setNickName(String nick) {
        this.nickName = nick;
    }
    
    public String getIP() {
        return this.ip;
    }
    
    public int getPorta() {
        return this.porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
    
    @Override
    public String toString() {
        return nickName;
    }
}
