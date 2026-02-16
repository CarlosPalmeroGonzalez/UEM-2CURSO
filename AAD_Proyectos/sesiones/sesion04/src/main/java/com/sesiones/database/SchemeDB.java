package com.sesiones.database;

/* Nos creamos una interfaz donde poder recoger con constantes los nombres de las tablas, columnas, etc.. para evitar fallos
y permitir posibles modificaciones en un futuro */
public interface SchemeDB {
    String TAB_NAME = "usuarios";
    String COL_ID = "id";
    String COL_NAME = "nombre";
    String COL_MAIL = "correo";
    String COL_PHONE = "telefono";
    String COL_PROFILE = "idPerfil";
}
