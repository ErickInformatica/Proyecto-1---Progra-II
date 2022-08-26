package edu.umg.controladores;

import edu.umg.modelos.Bitacora;
import edu.umg.modelos.Tickets;

import java.util.ArrayList;

public class ControladorTicket {


    /**
     * Agregar Ticket a Modelo
     * @param nitUsuario
     * @param descripcionTicket
     * @return Ticket agregado a Cola Mesa de Ayuda
     */
    public Tickets agregarTicket(String nitUsuario, String descripcionTicket){
        Tickets ticketCreado = new Tickets(String.valueOf(nitUsuario), descripcionTicket, "Mesa de Ayuda", "Creado");
        return ticketCreado;
    }

    /**
     * Agregar Ticket a Modelo de Manera Masiva
     * @param nitUsuario
     * @param descripcionTicket
     * @param tipoCola
     * @return Ticket Creado
     */
    public Tickets agregarTicketMasivo(String nitUsuario, String descripcionTicket, String tipoCola){
        Tickets ticketCreado = new Tickets(String.valueOf(nitUsuario), descripcionTicket, tipoCola, "Creado");
        return ticketCreado;
    }
}
