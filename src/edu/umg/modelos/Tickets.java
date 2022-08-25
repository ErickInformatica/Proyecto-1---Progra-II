package edu.umg.modelos;

public class Tickets {
     private Integer ticketId ;
     private String nitUsuario;
     private String descipcionTicket;
     private String tipoCola;
     private String estado;

     private static int count = 0;

    public Tickets() {
    }

    public Tickets(Integer ticketId, String nitUsuario, String descipcionTicket, String tipoCola, String estado) {
        this.ticketId = ticketId;
        this.nitUsuario = nitUsuario;
        this.descipcionTicket = descipcionTicket;
        this.tipoCola = tipoCola;
        this.estado = estado;
    }

    public Tickets(String nitUsuario, String descipcionTicket, String tipoCola, String estado) {
        setTicketId(++count);
        this.nitUsuario = nitUsuario;
        this.descipcionTicket = descipcionTicket;
        this.tipoCola = tipoCola;
        this.estado = estado;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getNitUsuario() {
        return nitUsuario;
    }

    public void setNitUsuario(String nitUsuario) {
        this.nitUsuario = nitUsuario;
    }

    public String getDescipcionTicket() {
        return descipcionTicket;
    }

    public void setDescipcionTicket(String descipcionTicket) {
        this.descipcionTicket = descipcionTicket;
    }

    public String getTipoCola() {
        return tipoCola;
    }

    public void setTipoCola(String tipoCola) {
        this.tipoCola = tipoCola;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return "Tickets{" +
                "ticketId=" + ticketId +
                ", nitUsuario='" + nitUsuario + '\'' +
                ", descipcionTicket='" + descipcionTicket + '\'' +
                ", tipoCola='" + tipoCola + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
