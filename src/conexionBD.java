
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class conexionBD {
    
    
    public static final String URL="jdbc:mysql://localhost:3306/asamblea";
    public static final String USERNAME="root";
    public static final String PASSEORD="";

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
