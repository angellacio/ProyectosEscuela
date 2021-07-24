package edu.TecMilenio;

public class EvidenciaFinal_Doctores {
    private String nombreCompleato;
    private String especialidad;
    private Boolean estado;

    public EvidenciaFinal_Doctores(String NombreCompleato, String Especialidad, Boolean Estado) {
        nombreCompleato = NombreCompleato;
        especialidad = Especialidad;
        estado = Estado;
    }
    public void ImprimeRenglon(Integer nID, Boolean bolEstado){
        if (estado = bolEstado) {
            System.out.println(nID + " - " + nombreCompleato + " - " + especialidad);
        }
    }

    public String ObtenDatos() {
        String sEstado = "0";
        if (estado) sEstado = "1";
        return nombreCompleato + ',' + especialidad + "," + sEstado;
    }


}
