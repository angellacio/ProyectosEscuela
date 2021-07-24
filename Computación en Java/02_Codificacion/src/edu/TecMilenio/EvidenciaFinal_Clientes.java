package edu.TecMilenio;

public class EvidenciaFinal_Clientes {
    private String nombreCompleto;
    private Boolean estado;

    public EvidenciaFinal_Clientes(String NombreCompleto, Boolean Estado) {
        nombreCompleto = NombreCompleto;
        estado = Estado;
    }

    public void ImprimeRenglon(Integer nID, Boolean bolEstado){
        if (estado = bolEstado) {
            System.out.println(nID + " - " + nombreCompleto);
        }
    }
    public String ObtenDatos(){
        String sEstado = "0";
        if (estado) sEstado = "1";
        return nombreCompleto + "," + sEstado;
    }
}
