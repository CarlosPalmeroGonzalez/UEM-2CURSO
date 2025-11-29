package com.evaluables.dao;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.evaluables.database.DBConnection;
import com.evaluables.database.SchemeDB;
import com.evaluables.model.Producto;
import com.evaluables.model.ProductoJSON;
import com.evaluables.model.ProductoResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ProductoDAOImp implements ProductoDAO{
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rst;
    private ProductoResponse response;

    public ProductoDAOImp() {
        con = DBConnection.obtenerConexion(); // Establecemos la conexión cada vez que creamos un objeto con el constructor vacio en el controller
    }

    @Override
    public void insertarProductos() throws SQLException{

        String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)"
            , SchemeDB.TAB_PR
            , SchemeDB.TAB_PR_COL_ID
            , SchemeDB.TAB_PR_COL_NOMBRE
            , SchemeDB.TAB_PR_COL_DESCRIPCION
            , SchemeDB.TAB_PR_COL_CANTIDAD
            , SchemeDB.TAB_PR_COL_PRECIO
        );
        String query2 = String.format("INSERT INTO %s (%s) VALUES (?)"
            , SchemeDB.TAB_PF
            , SchemeDB.TAB_PF_COL_IDPRODUCTO
        );
        try {
            URL urlDummy = new URL ("https://dummyjson.com/products");
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(urlDummy,ProductoResponse.class);
            // Introducimos todos los productos en la tabla "productos"
            for (ProductoJSON p : response.getProducts()) {
                pst = con.prepareStatement(query);
                pst.setInt(1, p.getId());
                pst.setString(2, p.getTitle());
                pst.setString(3, p.getDescription());
                pst.setInt(4, p.getStock());
                pst.setDouble(5, p.getPrice());
                pst.execute();
            }
            // Introducimos los productos de más de 1000€ de coste en la tabla "productos_fav"
            for (ProductoJSON p : response.getProducts()) {
                pst = con.prepareStatement(query2);
                if (p.getPrice() > 1000) { 
                    pst.setInt(1,p.getId());
                    pst.execute(); 
                } 
            }
        } catch (MalformedURLException e) {
            System.err.println("ERROR en la crear el objeto URL.");
            System.out.println(e.getMessage());
        } catch (MalformedInputException e) {
            System.err.println("ERROR en la URL de importado de datos."); 
            System.out.println(e.getMessage());
        } catch (StreamReadException e) {
            System.err.println("ERROR en la lectura.");
            System.out.println(e.getMessage());
        } catch (DatabindException e) {
            System.err.println("ERROR en la asociacón de los datos.");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("ERROR generalizado.");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Producto> listaProductos() {
        String query = "SELECT * FROM " + SchemeDB.TAB_PR;
        ArrayList <Producto> listaProductos = new ArrayList<>();
        try {
            pst = con.prepareStatement(query);
            rst = pst.executeQuery();
            while (rst.next()) {
                int id = rst.getInt(SchemeDB.TAB_PR_COL_ID);
                String nombre = rst.getString(SchemeDB.TAB_PR_COL_NOMBRE);
                String descripcion = rst.getString(SchemeDB.TAB_PR_COL_DESCRIPCION);
                int cantidad = rst.getInt(SchemeDB.TAB_PR_COL_CANTIDAD);
                double precio = rst.getDouble(SchemeDB.TAB_PR_COL_PRECIO);
                listaProductos.add(new Producto(id, nombre, descripcion, cantidad, precio));
            }
            return listaProductos;
        } catch (SQLException e) {
            System.err.println("ERROR al realizar ResultSet");
        }
        return null;
    }

    @Override
    public ArrayList<Producto> listaProductosFav() {
        // select * from productos_fav pf left join productos p on pf.id_producto = p.id 
        String query = "SELECT * FROM " + SchemeDB.TAB_PF + " LEFT JOIN " + SchemeDB.TAB_PR + " ON " 
            + SchemeDB.TAB_PF+"."+SchemeDB.TAB_PF_COL_IDPRODUCTO + " = " + SchemeDB.TAB_PR+"."+SchemeDB.TAB_PR_COL_ID;
        ArrayList <Producto> listaProductos = new ArrayList <>();
        try {
            pst = con.prepareStatement(query);
            rst = pst.executeQuery();
            while (rst.next()) {
                int id = rst.getInt(SchemeDB.TAB_PR_COL_ID);
                String nombre = rst.getString (SchemeDB.TAB_PR_COL_NOMBRE);
                String descripcion = rst.getString (SchemeDB.TAB_PR_COL_DESCRIPCION);
                int cantidad = rst.getInt(SchemeDB.TAB_PR_COL_CANTIDAD);
                double precio = rst.getDouble (SchemeDB.TAB_PR_COL_PRECIO);
                listaProductos.add(new Producto(id, nombre, descripcion, cantidad, precio, true ));
            }
            return listaProductos;
        } catch (SQLException e) {
            System.err.println("ERROR al realizar el ResultSet");
        }
        return null;
    }

}
