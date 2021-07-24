package edu.TecMilenio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Console;

public class EvidenciaFinal {
    private static BufferedReader datoCapturaCitas = new BufferedReader(new InputStreamReader(System.in));

    private static String getUserName(String prompt){
        String username = null;
        System.out.print(prompt);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            username = br.readLine().trim();
        }
        catch (IOException e) {
            System.out.println("¡Error al ingresar tu nombre!");
            System.exit(1);
        }
        return username;
    }

    private static String getPassword(String prompt) {
        String password = "";
        //ConsoleEraser consoleEraser = new ConsoleEraser();
        System.out.print(prompt);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //consoleEraser.start();
        try {
            password = in.readLine();
        }
        catch (IOException e){
            System.out.println("¡Error al ingresar la contraseña!");
            System.exit(1);
        }
        //consoleEraser.halt();
        System.out.print("\b");
        return password;
    }
    private static class ConsoleEraser extends Thread {
        private boolean running = true;
        public void run() {
            while (running) {
                System.out.print("\b ");
                try {
                    Thread.currentThread().sleep(1);
                }
                catch(InterruptedException e) {
                    break;
                }
            }
        }
        public synchronized void halt() {
            running = false;
        }
    }

    public static void ValidaUsuario() {
        String sUsuario = "", sPassword = "";
        String sMensajeUsuario = "";
        try{
            sUsuario = getUserName("Favor de ingresar el Usuario: ");
            sPassword = getPassword("Favor de ingresar la Contraseña: ");

            sMensajeUsuario = EvidenciaFinal_UsuariosManejo.Valida(sUsuario, sPassword);
            if (sMensajeUsuario == "") {
                System.out.print("Usuario y Contraseña correctas '" + sUsuario + "' - '" + sPassword + "'");
                MuestraMenuAplicacion();
            }
            else {
                CredencialesIncorrectas(sMensajeUsuario + " Desea continuar en el aplicativo Consulta Medica: Si/S o No/N: ");
            }
        }
        catch (Exception ex) {
            System.out.print("¡¡¡ Error !!! '" + ex.getMessage() + "'");
        }
        finally{ }
    }

    private static void CredencialesIncorrectas(String sMensaje) {
        String sContinuar = "";
        try{
            System.out.println();
            System.out.print(sMensaje);
            sContinuar = datoCapturaCitas.readLine().toString().trim().toUpperCase();

            switch (sContinuar){
                case "SI":
                case "S":
                    ValidaUsuario();
                    break;
                case "NO":
                case "N":
                    break;
                default:
                    CredencialesIncorrectas(sMensaje);
                    break;
            }

        }
        catch (Exception ex) { }
        finally{ }
    }

    private static void MuestraMenuAplicacion(){
        String sOpcionCitas = "";
        boolean bolSigeEvidencia = true;
        try {
            do{
                System.out.println("\n");
                System.out.println("   ** Manejo de consultas Medicas **\n");
                System.out.println("        1 - Administra Pacientes");
                System.out.println("        2 - Administra Doctores");
                System.out.println("        3 - Administra Citas");
                System.out.println("        4 - Administra Usuarios");
                System.out.println("        X/x - Regresar Menu Principal.\n");
                System.out.print("Favor de especificar la opción deseada: ");

                sOpcionCitas = datoCapturaCitas.readLine().toString().trim().toUpperCase();

                switch (sOpcionCitas) {
                    case "1":
                        EvidenciaFinal_ClientesManejo.MuestraMenu();
                        break;
                    case "2":
                        EvidenciaFinal_DoctoresManejo.MuestraMenu();
                        break;
                    case "3":
                        EvidenciaFinal_CitasManejo.MuestraMenu();
                        break;
                    case "4":
                        EvidenciaFinal_UsuariosManejo.MuestraMenu();
                        break;
                    case "X":
                        bolSigeEvidencia = false;
                        break;
                    default:
                        System.out.println("");
                        System.out.println("El tipo de dato ingresado no es correcto, ingresar un dato valido.");
                        System.out.println("");
                        MuestraMenuAplicacion();
                        break;
                }
            } while (bolSigeEvidencia);
        }
        catch (Exception ex){}
        finally { }
    }
}



