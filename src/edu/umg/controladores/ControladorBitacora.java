package edu.umg.controladores;

import edu.umg.modelos.Bitacora;
import edu.umg.modelos.Tickets;

import java.util.ArrayList;

public class ControladorBitacora {

    /**
     * Agregar Bitacora a Modelo
     * @param idTicket
     * @param nitSoporte
     * @return
     */
    public static Bitacora agregarBitacora(Integer idTicket, String nitSoporte){
        Bitacora bCreada = new Bitacora(idTicket,nitSoporte, "Ticket Creado", "Creacion");
        return bCreada;
    }

    /**
     * Crea Bitacora de Ticket Escalado
     * @param idTicket
     * @param nitSoporte
     * @param tipoEscalado
     * @return
     */
    public static Bitacora escalarBitacora(Integer idTicket, String nitSoporte, String tipoEscalado){
        Bitacora bCreada = new Bitacora(idTicket,nitSoporte, "Ticket Escalado a " + tipoEscalado, "Escalado");
        return bCreada;
    }

    /**
     * Crea Bitacor de Asignacion de Ticket
     * @param idTicket
     * @param nitSoporte
     * @return
     */
    public static Bitacora asignarBitacora(Integer idTicket, String nitSoporte){
        Bitacora bCreada = new Bitacora(idTicket,nitSoporte, "Ticket Asignado a " + nitSoporte, "Asignacion");
        return bCreada;
    }

    /**
     * Crea Bitacora de Resolucion de Ticket
     * @param idTicket
     * @param nitSoporte
     * @return
     */
    public static Bitacora resueltoBitacora(Integer idTicket, String nitSoporte){
        Bitacora bCreada = new Bitacora(idTicket,nitSoporte, "Ticket Resuelto por " + nitSoporte, "Resuelto");
        return bCreada;
    }

    /**
     * Crea Bitacora de Cambio de Estado de Bitacora
     * @param idTicket
     * @param nitSoporte
     * @param mensajeBitacora
     * @param estadoTicket
     * @return
     */
    public static Bitacora cambiarEstadoBitacora(Integer idTicket, String nitSoporte , String mensajeBitacora, String estadoTicket){
        Bitacora bCreada = new Bitacora(idTicket,nitSoporte, mensajeBitacora, estadoTicket);
        return bCreada;
    }

}
