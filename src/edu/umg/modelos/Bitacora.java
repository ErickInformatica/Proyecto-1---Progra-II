package edu.umg.modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bitacora {
    private Integer ticket;
    private String nitSoporte;
    private String fechaBitacora;
    private String mensaje;
    private String evento;



    public Bitacora(Integer ticket, String nitSoporte, String mensaje, String evento) {
        this.ticket = ticket;
        this.nitSoporte = nitSoporte;
        this.fechaBitacora = setFechaBitacora();
        this.mensaje = mensaje;
        this.evento = evento;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public String getNitSoporte() {
        return nitSoporte;
    }

    public void setNitSoporte(String nitSoporte) {
        this.nitSoporte = nitSoporte;
    }

    public String getFechaBitacora() {
        return fechaBitacora;
    }

    public String setFechaBitacora() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "Bitacora{" +
                "ticket=" + ticket +
                ", nitSoporte='" + nitSoporte + '\'' +
                ", fechaBitacora='" + fechaBitacora + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", evento='" + evento + '\'' +
                '}';
    }
}
