package edu.TecMilenio;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EvidenciaFinal_ClientesManejo {
    private static BufferedReader datoCCliente = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, EvidenciaFinal_Clientes> Pacientes = null;

    public static void MuestraMenu() {
        String sOpcion = "";
        boolean bolSigeEvidencia = true;
        try {
            load(1);
            do {
                System.out.println("   ** Manejo de consultas Medicas - Administra Pacientes **");
                System.out.println("        1 - Altas");
                System.out.println("        2 - Bajas");
                System.out.println("        3 - Consulta - Activos");
                System.out.println("        4 - Consulta - Inactivos");
                System.out.println("        X/x - Regresar Menu Consulta Medica.");
                System.out.print("Favor de especificar la opción deseada: ");

                sOpcion = datoCCliente.readLine().toString().trim().toUpperCase();
                switch (sOpcion) {
                    case "1":
                        Alta();
                        break;
                    case "2":
                        Borrar();
                        break;
                    case "3":
                        Consulta(true);
                        break;
                    case "4":
                        Consulta(false);
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
        Integer nID = 0;
        String Nombre = "";
        try {
            System.out.println("\n                     Alta de un cliente\n");
            System.out.print("Favor de proporcionar un nombre:");
            Nombre = datoCCliente.readLine().toString().trim();
            nID = Pacientes.size() + 1;
            Pacientes.put(nID, new EvidenciaFinal_Clientes(Nombre, true));
            save();
            System.out.print("\n\nGuardado correctamente. Preciona ENTER para continuar. ");
            datoCCliente.readLine();
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
    public static void Consulta(boolean bolEstado) {
        try {
            System.out.println("\n\nDatos Pasientes:");
            for (Map.Entry<Integer, EvidenciaFinal_Clientes> item : Pacientes.entrySet()) {
                item.getValue().ImprimeRenglon(item.getKey(), bolEstado);
            }
            System.out.print("\n\nPreciona ENTER para continuar. ");
            datoCCliente.readLine();
        }
        catch (Exception ex){
            System.out.println("¡¡ ERROR !!" + ex.getMessage());
        }
    }

    private static void load(Integer nOpcionCarga){
        BufferedReader brAgenda = null;
        String sLinea = "", ArchivoPasientes = "";
        try {
            if (nOpcionCarga == 1) ArchivoPasientes = "src/Evi_Pasientes.txt";
            Pacientes = new HashMap<>();
            brAgenda = new BufferedReader(new FileReader(ArchivoPasientes));
            while ((sLinea = brAgenda.readLine()) != null) {
                String[] sDato = null;
                if (sLinea != ""){
                    sDato = sLinea.split(",");
                    if (nOpcionCarga == 1)
                        Pacientes.put(Integer.parseInt(sDato[0].trim()),
                                new EvidenciaFinal_Clientes(sDato[1].trim(), sDato[2].trim().equals("1")));
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
            bwAgenda = new BufferedWriter(new FileWriter("src/Evi_Pasientes.txt"));

            for (Map.Entry<Integer, EvidenciaFinal_Clientes> item : Pacientes.entrySet()) {
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
