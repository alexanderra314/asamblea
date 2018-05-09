
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class conexionBD {
    
    
    public static final String URL="jdbc:mysql://db4free.net:3306/asamblea";
    public static final String USERNAME="adminasamblea";
    public static final String PASSEORD="admin12345";

    public Connection getConnection(){
         Connection con=null;
         try {
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection) DriverManager.getConnection(URL,USERNAME,PASSEORD);
             System.out.println("CONEXION EXITOSA");
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"NO HAY CONEXION CON LA BASE DE DATOS");
        }
         return con;
    }
}
