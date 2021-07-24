package edu.TecMilenio;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EvidenciaFinal_UsuariosManejo {
    private static BufferedReader datoCUsuarios = new BufferedReader(new InputStreamReader(System.in));
    private static Map<String, EvidenciaFinal_Usuarios> Usuarios = null;

    public static String Valida(String sUsuario, String sPassword){
        String sResult = "";



        return sResult;
    }

    public static void MuestraMenu() {
        String sOpcion = "";
        boolean bolSigeEvidencia = true;
        try {
            load(1);
            do {
                System.out.println("   ** Manejo de consultas Medicas - Administra Usuarios **");
                System.out.println("        1 - Altas");
                System.out.println("        2 - Bajas");
                System.out.println("        3 - Consulta");
                System.out.println("        X/x - Regresar Menu Consulta Medica.");
                System.out.print("Favor de especificar la opción deseada: ");

                sOpcion = datoCUsuarios.readLine().toString().trim().toUpperCase();
                switch (sOpcion) {
                    case "1":
                        Alta();
                        break;
                    case "2":
                        Borrar();
                        break;
                    case "3":
                        Consulta();
                        break;
                    case "X":
                        System.out.println("");
                        System.out.println(".........");
                        bolSigeEvidencia = false;
                        break;
                    default:
                        System.out.println("");
                        System.out.println("El tipo de dato ingresado no es correcto, ingresar un dato valido.");
                        System.out.println("");
                        MuestraMenu();
                        break;
                }
                sOpcion = "";
            } while (bolSigeEvidencia);
        }
        catch (Exception ex){}
        finally { }
    }

    public static void Alta() {
        String Nombre = "", Usuario = "", Password = "";
        try {
            System.out.println("\n                     Alta de un cliente\n");
            System.out.print("Favor de proporcionar un Usuario: ");
            Usuario = datoCUsuarios.readLine().toString().trim();
            System.out.print("Favor de proporcionar un Password: ");
            Password = datoCUsuarios.readLine().toString().trim();
            System.out.print("Favor de proporcionar un Nombre: ");
            Nombre = datoCUsuarios.readLine().toString().trim();

            Usuarios.put(Usuario, new EvidenciaFinal_Usuarios(Nombre, Password));
            save();
            System.out.print("\n\nGuardado correctamente. Preciona ENTER para continuar. ");
            datoCUsuarios.readLine();
        }
        catch (Exception ex){
            System.out.println("¡¡ ERROR !!" + ex.getMessage());
        }
    }
    public static void Borrar() {
        try {

        }
        catch (Exception ex){
            System.out.println("¡¡ ERROR !!" + ex.getMessage());
        }
    }
    public static void Consulta() {
        try {
            System.out.println("\n\nDatos Pasientes:");
            for (Map.Entry<String, EvidenciaFinal_Usuarios> item : Usuarios.entrySet()) {
                item.getValue().ImprimeRenglon(item.getKey());
            }
            System.out.print("\n\nPreciona ENTER para continuar. ");
            datoCUsuarios.readLine();
        }
        catch (Exception ex){
            System.out.println("¡¡ ERROR !!" + ex.getMessage());
        }
    }

    private static void load(Integer nOpcionCarga){
        BufferedReader brAgenda = null;
        String sLinea = "", ArchivoPasientes = "";
        try {
            if (nOpcionCarga == 1) ArchivoPasientes = "src/Evi_Usuario.txt";
            Usuarios = new HashMap<>();
            brAgenda = new BufferedReader(new FileReader(ArchivoPasientes));
            while ((sLinea = brAgenda.readLine()) != null) {
                String[] sDato = null;
                if (sLinea != ""){
                    sDato = sLinea.split(",");
                    if (nOpcionCarga == 1)
                        Usuarios.put(sDato[0].trim(),
                                new EvidenciaFinal_Usuarios(sDato[1].trim(), sDato[2].trim()));
                }
            }
        }
        catch (Exception ex){
            System.out.println(" ¡¡ ERROR !! " + ex.getMessage());
        }
        finally {
            try {
                if (brAgenda != null) brAgenda.close();
            }
            catch (IOException ex) { System.out.println(" ¡¡ ERROR !! " + ex.getMessage()); }
        }
    }
    private static void save(){
        BufferedWriter bwAgenda = null;
        String sLinea;
        try {
            bwAgenda = new BufferedWriter(new FileWriter("src/Evi_Usuario.txt"));

            for (Map.Entry<String, EvidenciaFinal_Usuarios> item : Usuarios.entrySet()) {
                bwAgenda.write(item.getKey() + "," + item.getValue().ObtenDatos() + "\n");
            }
        }
        catch (Exception ex){
            System.out.println(" ¡¡ ERROR !! " + ex.getMessage());
        }
        finally {
            try {
                if (bwAgenda != null) bwAgenda.close();
            }
            catch (IOException ex) { System.out.println(" ¡¡ ERROR !! " + ex.getMessage()); }
        }
    }
}
