package edu.TecMilenio;

public class EvidenciaFinal_Usuarios {
    private String nombre;
    private String password;

    public EvidenciaFinal_Usuarios(String Nombre, String Password) {
        nombre = Nombre;
        password = Password;
    }

    public void ImprimeRenglon(String sUsuario) {
            System.out.println(sUsuario + " - " + password + " - " + nombre);
    }

    public String ObtenDatos(){
        return password + "," + nombre;
    }
}
