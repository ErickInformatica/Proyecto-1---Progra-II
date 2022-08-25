package edu.umg.controladores;

import edu.umg.modelos.Bitacora;
import edu.umg.modelos.Tickets;

import java.util.ArrayList;

public class ControladorTicket {


    public Tickets agregarTicket(String nitUsuario, String descripcionTicket){
        Tickets ticketCreado = new Tickets(String.valueOf(nitUsuario), descripcionTicket, "Mesa de Ayuda", "Creado");
        return ticketCreado;
    }

    public Tickets agregarTicketMasivo(String nitUsuario, String descripcionTicket, String tipoCola){
        Tickets ticketCreado = new Tickets(String.valueOf(nitUsuario), descripcionTicket, tipoCola, "Creado");
        return ticketCreado;
    }
}
