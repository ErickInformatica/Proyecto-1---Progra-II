package edu.umg.modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bitacora {
    private Integer ticket;
    private String nitSoporte;
    private String fechaBitacora;
    private String mensaje;
    private String evento;


    /**
     * @param ticket
     * @param nitSoporte
     * @param mensaje
     * @param evento
     */
    public Bitacora(Integer ticket, String nitSoporte, String mensaje, String evento) {
        this.ticket = ticket;
        this.nitSoporte = nitSoporte;
        this.fechaBitacora = setFechaBitacora();
        this.mensaje = mensaje;
        this.evento = evento;
    }

    /**
     * @return Id Ticket
     */
    public Integer getTicket() {
        return ticket;
    }

    /**
     * @param ticket
     */
    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    /**
     * @return Nit Soporte
     */
    public String getNitSoporte() {
        return nitSoporte;
    }

    /**
     * @param nitSoporte
     */
    public void setNitSoporte(String nitSoporte) {
        this.nitSoporte = nitSoporte;
    }

    /**
     * @return Fecha Bitacora
     */
    public String getFechaBitacora() {
        return fechaBitacora;
    }

    /**
     * @return Fecha Actual
     */
    public String setFechaBitacora() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    /**
     * @return mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return Evento
     */
    public String getEvento() {
        return evento;
    }

    /**
     * @param evento
     */
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
