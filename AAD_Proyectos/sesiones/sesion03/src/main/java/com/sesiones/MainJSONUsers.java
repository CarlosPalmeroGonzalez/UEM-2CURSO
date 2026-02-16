package com.sesiones;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sesiones.Model.UsuarioJSON;
import com.sesiones.Model.UsuarioResponse;

public class MainJSONUsers {
    public static void main(String[] args) {
        // <ObjectMapper> nos permite hacer el mapeo de lo que nos viene desde la URL.
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            URL url = new URL ("https://dummyjson.com/users");
            // Introducimos la URL y la clase con la que se va a realizar el mapeo
            UsuarioResponse response = mapper.readValue(url, UsuarioResponse.class);
            
            for (UsuarioJSON item : response.getUsers()) {
                System.out.println(item);
            }
        } catch (MalformedInputException e) {
            System.out.println("Error en la URL");
        }catch (StreamReadException e) {
            System.out.println("Error en la lectura");
        } catch (DatabindException e) {
            System.out.println("Error en la asociación de datos");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
