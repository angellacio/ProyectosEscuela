package edu.TecMilenio;

import javax.print.attribute.DateTimeSyntax;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class EvidenciaFinal_CitasManejo {
    private static BufferedReader datoCCitas = new BufferedReader(new InputStreamReader(System.in));
    private static Map<Integer, EvidenciaFinal_Clientes> Pasientes = null;
    private static Map<Integer, EvidenciaFinal_Doctores> Doctores = null;
    private static Map<Integer, EvidenciaFinal_Citas> Citas = null;

    public static void MuestraMenu() {
        String sOpcion = "";
        boolean bolSigeEvidencia = true;
        try {
            load(1);
            load(2);
            load(3);
            do {
                System.out.println("   ** Manejo de consultas Medicas - Administra Usuarios **");
                System.out.println("        1 - Altas");
                System.out.println("        2 - Bajas");
                System.out.println("        3 - Consulta");
                System.out.println("        X/x - Regresar Menu Consulta Medica.");
                System.out.print("Favor de especificar la opción deseada: ");

                sOpcion = datoCCitas.readLine().toString().trim().toUpperCase();
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
        Integer nID=0, nPasiente = 0, nDoctor = 0;
        String sMotivo = "", dCita = null, hCita = null;
        try {
            System.out.println("\n                     Alta de un cliente\n");
            System.out.print("Favor de proporcionar un Pasiente: ");
            nPasiente = Integer.parseInt(datoCCitas.readLine().toString().trim());
            System.out.print("Favor de proporcionar un Doctor: ");
            nDoctor = Integer.parseInt(datoCCitas.readLine().toString().trim());
            System.out.print("Favor de proporcionar un Motivo e la sita: ");
            sMotivo = datoCCitas.readLine().toString().trim();
            System.out.print("Favor de proporcionar la fecha de la sita: ");
            dCita = datoCCitas.readLine().toString().trim();
            System.out.print("Favor de proporcionar la hora de la sita: ");
            hCita = datoCCitas.readLine().toString().trim();

            nID = Citas.size() +1;
            Citas.put(nID, new EvidenciaFinal_Citas(nPasiente, nDoctor, sMotivo, dCita, hCita));
            save();
            System.out.print("\n\nGuardado correctamente. Preciona ENTER para continuar. ");
            datoCCitas.readLine();
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
            for (Map.Entry<Integer, EvidenciaFinal_Citas> item : Citas.entrySet()) {
                item.getValue().ImprimeRenglon(item.getKey(), "Falta", "Falta");
            }
            System.out.print("\n\nPreciona ENTER para continuar. ");
            datoCCitas.readLine();
        }
        catch (Exception ex){
            System.out.println("¡¡ ERROR !!" + ex.getMessage());
        }
    }

    private static void load(Integer nOpcionCarga){
        BufferedReader brAgenda = null;
        String sLinea = "", Archivo = "";
        try {
            if (nOpcionCarga == 1) Archivo = "src/Evi_Citas.txt";
            else if (nOpcionCarga == 2) Archivo = "src/Evi_Pasientes.txt";
            else if (nOpcionCarga == 3) Archivo = "src/Evi_Doctores.txt";
            if (nOpcionCarga == 1) Citas = new HashMap<>();
            else if (nOpcionCarga == 2) Pasientes = new HashMap<>();
            else if (nOpcionCarga == 3) Doctores = new HashMap<>();

            brAgenda = new BufferedReader(new FileReader(Archivo));
            while ((sLinea = brAgenda.readLine()) != null) {
                String[] sDato = null;
                if (sLinea != ""){
                    sDato = sLinea.split(",");
                    if (nOpcionCarga == 1) {
                        Citas.put(Integer.parseInt(sDato[0].trim()),
                                new EvidenciaFinal_Citas(Integer.parseInt(sDato[1].trim()), Integer.parseInt(sDato[2].trim()),
                                        sDato[3].trim(), sDato[4].trim(), sDato[5].trim()));
                    }
                    else if (nOpcionCarga == 2) {
                        Pasientes.put(Integer.parseInt(sDato[0].trim()),
                                new EvidenciaFinal_Clientes(sDato[1].trim(), sDato[2].trim().equals("1")));
                    }
                    else if (nOpcionCarga == 3) {
                        Doctores.put(Integer.parseInt(sDato[0].trim()),
                                new EvidenciaFinal_Doctores(sDato[1].trim(), sDato[2].trim(), sDato[3].trim().equals("1")));
                    }
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
            bwAgenda = new BufferedWriter(new FileWriter("src/Evi_Citas.txt"));

            for (Map.Entry<Integer, EvidenciaFinal_Citas> item : Citas.entrySet()) {
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

    private static String BuscaPasiente(Integer nPasiente){
        String sResult = "";



        return sResult;
    }
}
