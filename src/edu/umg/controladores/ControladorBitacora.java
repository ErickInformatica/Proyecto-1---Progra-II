package edu.umg.controladores;

import edu.umg.modelos.Bitacora;
import edu.umg.modelos.Tickets;

import java.util.ArrayList;

public class ControladorBitacora {

    public static Bitacora agregarBitacora(Integer idTicket, String nitSoporte){
        Bitacora bCreada = new Bitacora(idTicket,nitSoporte, "Ticket Creado", "Creacion");
        return bCreada;
    }

    public static Bitacora escalarBitacora(Integer idTicket, String nitSoporte, String tipoEscalado){
        Bitacora bCreada = new Bitacora(idTicket,nitSoporte, "Ticket Escalado a " + tipoEscalado, "Escalado");
        return bCreada;
    }

    public static Bitacora asignarBitacora(Integer idTicket, String nitSoporte){
        Bitacora bCreada = new Bitacora(idTicket,nitSoporte, "Ticket Asignado a " + nitSoporte, "Asignacion");
        return bCreada;
    }

    public static Bitacora resueltoBitacora(Integer idTicket, String nitSoporte){
        Bitacora bCreada = new Bitacora(idTicket,nitSoporte, "Ticket Resuelto por " + nitSoporte, "Resuelto");
        return bCreada;
    }

    public static Bitacora cambiarEstadoBitacora(Integer idTicket, String nitSoporte , String mensajeBitacora, String estadoTicket){
        Bitacora bCreada = new Bitacora(idTicket,nitSoporte, mensajeBitacora, estadoTicket);
        return bCreada;
    }

}
