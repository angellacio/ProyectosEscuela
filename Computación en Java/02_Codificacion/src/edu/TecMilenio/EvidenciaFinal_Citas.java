package edu.TecMilenio;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Calendar;

public class EvidenciaFinal_Citas {
    private Integer paciente;
    private Integer doctor;
    private String motivo;
    private String citaFecha;
    private String citaHora;

    public EvidenciaFinal_Citas(Integer Paciente, Integer Doctor, String Motivo, String CitaFecha, String CitaHora) {
        paciente = Paciente;
        doctor = Doctor;
        motivo = Motivo;
        citaFecha = CitaFecha;
        citaHora = CitaHora;
    }
    public void ImprimeRenglon(Integer nID, String sPaciente, String sDoctor) {
        System.out.println(nID + " - " + paciente + " " + sPaciente + " - " + doctor + " " + sDoctor + " - " + citaFecha + " " + citaHora + " - " + motivo);
    }
    public String ObtenDatos(){
        return paciente + "," + doctor + "," + motivo + "," + citaFecha + "," + citaHora;
    }
}
