package com.sesiones.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.sesiones.database.DBConnection;
import com.sesiones.database.SchemeDB;
import com.sesiones.model.Usuario;

// El nombre UsuarioDAOImp -> el "imp" es de implementado (es decir, que has implementado la interfaz correpondiente a su dao)
public class UsuarioDAOImp implements UsuarioDAO{
    // Como se comporta la lógica contra la base de datos (Esto es ir contra la base de datos constantemente)
    
    private Connection conexion;
    private PreparedStatement pst; // => se inicializa en el método donse se vaya a utilizar
    private ResultSet rst;

    //En el contructor, realizamos la conexión para que cada vez que se invoque un objeto <UsuarioDAOImp>, nos conecte a la BD
    public UsuarioDAOImp () {
        conexion = DBConnection.getConnection();
    }

    @Override
    public void insertarUsuario(Usuario usuario) throws SQLException { 
    /* Cuando aplicamos un <trhows "nombreException"> estamos haciendo que CUANDO se utilice el método, se tiene que controlar
    la exception donde se vaya a utilizar dicho método */
        // INSERT INTO usuario (nombre, mail, telefono, idPerfil) VALUES (usuario.nombre, asd, asd, asd)
        // %s -> porque son String. Son sustituidos por los <args>
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)"
                , SchemeDB.TAB_NAME, SchemeDB.COL_NAME, SchemeDB.COL_MAIL, SchemeDB.COL_PHONE, SchemeDB.COL_PROFILE);
        // Ahora vamos a parametrizar para el <PreparedStatement>
        // => A través de la conexión, crea un PreparedStatement en el cual pasamos la query que quiero que ejecutes
            // => Ahora podemos controlar la exception de dos maneras: try-catch o throws "nombreException" en la firma del método
            // Si cambiamos la firma del metodo sería con <throws SQLException> en el método de la interfaz
        pst = conexion.prepareStatement(query);
        pst.setString(1,usuario.getNombre()); // Parametro 1
        pst.setString(2,usuario.getCorreo()); // Parametro 2
        pst.setInt(3,usuario.getTelefono()); // Parametro 3
        pst.setInt(4,usuario.getIdPerfil()); // Parametro 4
        pst.execute(); // => Así se utiliza para saber si devuelve o no un resulSet (devuelve boolean)
            // pst.executeUpdate(); // => Así compruebas cuantas filas se han visto afectadas (devuelve un int)
        
    }

    @Override
    public int eliminarUsuarios(String correo) {
        return 0;
    }

    @Override
    public ArrayList<Usuario> listaUSuarios() {
        ArrayList <Usuario> listaResultante = new ArrayList<>();
        String query = "SELECT * FROM " + SchemeDB.TAB_NAME;
        try {
            pst = conexion.prepareStatement(query);
            rst = pst.executeQuery(); // => el executeQuery del <PreparedStatement> lo guardamos en el <ResulSet>
            // x -> next() -> devuelve un boolean por lo que lo metemos en un while
            // El ResulSet es un conjunto de datos (d, d, d, d, d, d ...)
            while ( rst.next() ){  
                String nombre = rst.getString(SchemeDB.COL_NAME);
                String correo = rst.getString(SchemeDB.COL_MAIL);
                int telefono = rst.getInt(SchemeDB.COL_PHONE);
                int perfil = rst.getInt(SchemeDB.COL_PROFILE);
                listaResultante.add(new Usuario (nombre, correo, telefono, perfil));
            }
        } catch (SQLException e) {
            System.out.println("Fallo en la sentencia SQL");
        }
        return listaResultante;
    }

    @Override
    public void actualizarUsuario(Usuario usuario, Object[] datos) {
        
    }

}
