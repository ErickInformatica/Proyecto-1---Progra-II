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

    /**
     * @param ticketId
     * @param nitUsuario
     * @param descipcionTicket
     * @param tipoCola
     * @param estado
     */
    public Tickets(Integer ticketId, String nitUsuario, String descipcionTicket, String tipoCola, String estado) {
        this.ticketId = ticketId;
        this.nitUsuario = nitUsuario;
        this.descipcionTicket = descipcionTicket;
        this.tipoCola = tipoCola;
        this.estado = estado;
    }

    /**
     * @param nitUsuario
     * @param descipcionTicket
     * @param tipoCola
     * @param estado
     */
    public Tickets(String nitUsuario, String descipcionTicket, String tipoCola, String estado) {
        setTicketId(++count);
        this.nitUsuario = nitUsuario;
        this.descipcionTicket = descipcionTicket;
        this.tipoCola = tipoCola;
        this.estado = estado;
    }

    /**
     * @return ticketID
     */
    public Integer getTicketId() {
        return ticketId;
    }

    /**
     * @param ticketId
     */
    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * @return Nit Usuario
     */
    public String getNitUsuario() {
        return nitUsuario;
    }

    /**
     * @param nitUsuario
     */
    public void setNitUsuario(String nitUsuario) {
        this.nitUsuario = nitUsuario;
    }

    /**
     * @return Descripcion Ticket
     */
    public String getDescipcionTicket() {
        return descipcionTicket;
    }

    /**
     * @param descipcionTicket
     */
    public void setDescipcionTicket(String descipcionTicket) {
        this.descipcionTicket = descipcionTicket;
    }

    /**
     * @return Tipo Cola
     */
    public String getTipoCola() {
        return tipoCola;
    }

    /**
     * @param tipoCola
     */
    public void setTipoCola(String tipoCola) {
        this.tipoCola = tipoCola;
    }

    /**
     * @return Estado Ticket
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     */
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
