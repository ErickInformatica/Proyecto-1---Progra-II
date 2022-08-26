package edu.umg.controladores;

import edu.umg.modelos.Bitacora;
import edu.umg.modelos.Tickets;

import java.util.ArrayList;
import java.util.Scanner;

public class ManejoColas {

    /**
     * Array de Tickets
     */
    private ArrayList<Tickets> pArray = new ArrayList();

    /***
     * Agreagr Ticket a Array
     * @param ticket a agregar
     */
    public void agregarCola (Tickets ticket){
        pArray.add(ticket);
    }

    /***
     * Devolver Array
     * @return Array Tickets con todos los datos
     */
    public ArrayList<Tickets> getTicketsArray(){
        return pArray;
    }

    /**
     * Escala el Ticket al siguiente nivel
     * @param ticketEscalado Id Ticket
     * @param nitSoporte
     * @return String, tipo de cola escalado
     */
    public String escalarTicket(Tickets ticketEscalado, String nitSoporte){
        Integer iArray = getIndexCola(ticketEscalado.getTicketId());
        if(ticketEscalado.getTipoCola().equals("mesa") && ticketEscalado.getEstado() != "Resuelto" && ticketEscalado.getEstado() != "Asignado"){
            pArray.get(iArray).setTipoCola("soporte");
            return "Soporte Tecnico";
        } else if (ticketEscalado.getTipoCola() == "soporte" && ticketEscalado.getEstado() != "Resuelto" && ticketEscalado.getEstado() != "Asignado") {
            pArray.get(iArray).setTipoCola("desarrollo");
            return "Desarrollador";
        } else{
            return null;
        }
    }

    /**
     * Asignar Ticket
     * @param rolIngreso
     * @return Ticket Index Array
     */
    public Integer asignarTicket(String rolIngreso){
        ArrayList<Tickets> arrayAsignar = new ArrayList();
        for (int i=0; i< pArray.size(); i++){
            if(pArray.get(i).getTipoCola().equals(rolIngreso) && pArray.get(i).getEstado() != "Resuelto" && pArray.get(i).getEstado() != "Asignado"){
                arrayAsignar.add(pArray.get(i));
            }
        }

        int randNumPos = (int)(Math.random() * ((arrayAsignar.size()-1 - 0) + 1)) + 0;

        if(arrayAsignar.size() > 0){
            if(arrayAsignar.get(0).getTipoCola() == "mesa") {
                String formatoTabla = "| %-8d | %-40s | %-16s |%-16s |%n";

                System.out.println("+----------+------------------------------------------+------------------+-----------------+");
                System.out.println("|Id Ticket |Descripcion                               | Tipo Cola        | Estado          |");
                System.out.println("+----------+------------------------------------------+------------------+-----------------+");
                System.out.format(formatoTabla, arrayAsignar.get(0).getTicketId(), arrayAsignar.get(0).getDescipcionTicket(), arrayAsignar.get(0).getTipoCola(),
                        arrayAsignar.get(0).getEstado());
                System.out.println("+----------+------------------------------------------+------------------+-----------------+");

                //Cambiar Estado Ticket
                Integer iArray = getIndexCola(arrayAsignar.get(0).getTicketId());
                pArray.get(iArray).setEstado("Asignado");

                System.out.println("");

                return arrayAsignar.get(0).getTicketId();
            } else if (arrayAsignar.get(arrayAsignar.size()-1).getTipoCola() == "soporte") {
                String formatoTabla = "| %-8d | %-40s | %-16s |%-16s |%n";

                System.out.println("+----------+------------------------------------------+------------------+-----------------+");
                System.out.println("|Id Ticket |Descripcion                               | Tipo Cola        | Estado          |");
                System.out.println("+----------+------------------------------------------+------------------+-----------------+");
                System.out.format(formatoTabla, arrayAsignar.get(arrayAsignar.size()-1).getTicketId(), arrayAsignar.get(arrayAsignar.size()-1).getDescipcionTicket(), arrayAsignar.get(arrayAsignar.size()-1).getTipoCola(),
                        arrayAsignar.get(arrayAsignar.size()-1).getEstado());
                System.out.println("+----------+------------------------------------------+------------------+-----------------+");

                Integer iArray = getIndexCola(arrayAsignar.get(arrayAsignar.size()-1).getTicketId());
                pArray.get(iArray).setEstado("Asignado");

                System.out.println("");

                return arrayAsignar.get(arrayAsignar.size()-1).getTicketId();
            } else if (arrayAsignar.get(randNumPos).getTipoCola() == "desarrollo") {
                String formatoTabla = "| %-8d | %-40s | %-16s |%-16s |%n";

                System.out.println("+----------+------------------------------------------+------------------+-----------------+");
                System.out.println("|Id Ticket |Descripcion                               | Tipo Cola        | Estado          |");
                System.out.println("+----------+------------------------------------------+------------------+-----------------+");
                System.out.format(formatoTabla, arrayAsignar.get(randNumPos).getTicketId(), arrayAsignar.get(randNumPos).getDescipcionTicket(), arrayAsignar.get(randNumPos).getTipoCola(),
                        arrayAsignar.get(randNumPos).getEstado());
                System.out.println("+----------+------------------------------------------+------------------+-----------------+");

                Integer iArray = getIndexCola(arrayAsignar.get(randNumPos).getTicketId());
                pArray.get(iArray).setEstado("Asignado");

                System.out.println("");

                return arrayAsignar.get(randNumPos).getTicketId();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Solucion Ticket
     * @param tAsignado
     * @param nitIngreso
     * @return Bitacora de Solucion Ticket
     */
    public Bitacora solucionTicket(ArrayList<Tickets> tAsignado,String nitIngreso) {
        if(tAsignado.size() > 0){
            Scanner lecturaCadena = new Scanner(System.in);

            String formatoTabla = "| %-8d | %-40s | %-16s |%-16s |%n";

            System.out.println("+----------+------------------------------------------+------------------+-----------------+");
            System.out.println("|Id Ticket |Descripcion                               | Tipo Cola        | Estado          |");
            System.out.println("+----------+------------------------------------------+------------------+-----------------+");
            System.out.format(formatoTabla, tAsignado.get(0).getTicketId(), tAsignado.get(0).getDescipcionTicket(), tAsignado.get(0).getTipoCola(),
                    tAsignado.get(0).getEstado());
            System.out.println("+----------+------------------------------------------+------------------+-----------------+");

            System.out.println("");
            System.out.println("Ingrese el mensaje de resolucion de Ticket: ");
            String mensajeResolucion = lecturaCadena.nextLine();

            Bitacora bitResuelta = resolverEstadoTicket(tAsignado.get(0), nitIngreso, mensajeResolucion);

            Integer iArray = getIndexCola(tAsignado.get(0).getTicketId());
            pArray.get(iArray).setEstado("Resuelto");
            pArray.get(iArray).setTipoCola("Resuelto");

            return bitResuelta;
        } else {
            return null;
        }
    }


    //

    /***
     *Mostrar todos los tickets
     */
    public void mostrarTickets (){
        String formatoTabla = "| %-8d | %-40s | %-16s |%-16s |%n";

        System.out.println("+----------+------------------------------------------+------------------+-----------------+");
        System.out.println("|Id Ticket |Descripcion                               | Tipo Cola        | Estado          |");
        System.out.println("+----------+------------------------------------------+------------------+-----------------+");

        for (int i=0; i< pArray.size(); i++){
            System.out.format(formatoTabla, pArray.get(i).getTicketId(), pArray.get(i).getDescipcionTicket(), pArray.get(i).getTipoCola(), pArray.get(i).getEstado());
        }
        System.out.println("+----------+------------------------------------------+------------------+-----------------+");
    }


    /***
     *Mostrar un ticket
     * @param idTicket
     */
    public void mostrarIdTickets (Integer idTicket){
        //Filtro Array Tickets Id
        ArrayList<Tickets> ticketArrayFiltro = new ArrayList();
        for(int i = 0; i < pArray.size(); i++){
            if(pArray.get(i).getTicketId().equals(idTicket)){
                ticketArrayFiltro.add(pArray.get(i));
            }
        }


        String formatoTabla = "| %-8d | %-16s  | %-40s | %-16s |%-16s |%n";

        System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");
        System.out.println("|Id Ticket | Nit             | Descripcion                               | Tipo Cola        | Estado          |");
        System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");

        for (int i=0; i< ticketArrayFiltro.size(); i++){
            System.out.format(formatoTabla, ticketArrayFiltro.get(i).getTicketId(), ticketArrayFiltro.get(i).getNitUsuario() ,ticketArrayFiltro.get(i).getDescipcionTicket(),
                    ticketArrayFiltro.get(i).getTipoCola(), ticketArrayFiltro.get(i).getEstado());
        }

        System.out.println("+----------+------------------------------------------+------------------+-----------------+");
    }

    /***
     *  Reporte Ticket por Usuario
     * @param nitUsuario
     */
    public void repoteTicketsUsuario(String nitUsuario){
        //Filtro Array Tickets Id
        ArrayList<Tickets> ticketArrayUsuario = new ArrayList();
        for(int i = 0; i < pArray.size(); i++){
            if(pArray.get(i).getNitUsuario().equals(nitUsuario)){
                ticketArrayUsuario.add(pArray.get(i));
            }
        }


        String formatoTabla = "| %-8d | %-16s  | %-40s | %-16s |%-16s |%n";

        System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");
        System.out.println("|Id Ticket | Nit             | Descripcion                               | Tipo Cola        | Estado          |");
        System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");

        for (int i=0; i< ticketArrayUsuario.size(); i++){
            System.out.format(formatoTabla, ticketArrayUsuario.get(i).getTicketId(), ticketArrayUsuario.get(i).getNitUsuario() ,ticketArrayUsuario.get(i).getDescipcionTicket(),
                    ticketArrayUsuario.get(i).getTipoCola(), ticketArrayUsuario.get(i).getEstado());
        }

        System.out.println("+----------+------------------------------------------+------------------+-----------------+");
    }


    /**
     * Reporte de colas
     */
    public void reporteColas (){
        String formatoTabla = "| %-8d | %-16s  | %-40s | %-16s |%-16s |%n";

        //Filtro Array Tickets Id
        ArrayList<Tickets> colaMesaAyuda = new ArrayList();
        ArrayList<Tickets> colaSoporteTec = new ArrayList();
        ArrayList<Tickets> colaDesarrollo = new ArrayList();
        ArrayList<Tickets> colaAsignado = new ArrayList();
        ArrayList<Tickets> colaResuelto = new ArrayList();

        //Filtro MesaAyuda
        for(int i = 0; i < pArray.size(); i++){
            if(pArray.get(i).getTipoCola().equals("mesa")){
                colaMesaAyuda.add(pArray.get(i));
            }
        }

        if(colaMesaAyuda.size() > 0) {
            System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");
            System.out.println("|Id Ticket | Nit             | Descripcion                               | Tipo Cola        | Estado          |");
            System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");

            for (int i=0; i< colaMesaAyuda.size(); i++){
                System.out.format(formatoTabla, colaMesaAyuda.get(i).getTicketId(), colaMesaAyuda.get(i).getNitUsuario() ,colaMesaAyuda.get(i).getDescipcionTicket(),
                        colaMesaAyuda.get(i).getTipoCola(), colaMesaAyuda.get(i).getEstado());
            }

            System.out.println("+----------+------------------------------------------+------------------+-----------------+");
        } else {
            System.out.println("No hay tickets en la Cola Mesa de Ayuda .");
        }

        //Filtro SoporteTec
        for(int i = 0; i < pArray.size(); i++){
            if(pArray.get(i).getTipoCola().equals("soporte")){
                colaSoporteTec.add(pArray.get(i));
            }
        }

        if(colaSoporteTec.size() > 0) {
            System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");
            System.out.println("|Id Ticket | Nit             | Descripcion                               | Tipo Cola        | Estado          |");
            System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");

            for (int i=0; i< colaSoporteTec.size(); i++){
                System.out.format(formatoTabla, colaSoporteTec.get(i).getTicketId(), colaSoporteTec.get(i).getNitUsuario() ,colaSoporteTec.get(i).getDescipcionTicket(),
                        colaSoporteTec.get(i).getTipoCola(), colaSoporteTec.get(i).getEstado());
            }

            System.out.println("+----------+------------------------------------------+------------------+-----------------+");
        } else {
            System.out.println("No hay tickets en la Cola Soporte Tecnico.");
        }

        //Filtro Desarrollador
        for(int i = 0; i < pArray.size(); i++){
            if(pArray.get(i).getTipoCola().equals("desarrollo")){
                colaDesarrollo.add(pArray.get(i));
            }
        }

        if(colaDesarrollo.size() > 0) {
            System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");
            System.out.println("|Id Ticket | Nit             | Descripcion                               | Tipo Cola        | Estado          |");
            System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");

            for (int i=0; i< colaDesarrollo.size(); i++){
                System.out.format(formatoTabla, colaDesarrollo.get(i).getTicketId(), colaDesarrollo.get(i).getNitUsuario() ,colaDesarrollo.get(i).getDescipcionTicket(),
                        colaDesarrollo.get(i).getTipoCola(), colaDesarrollo.get(i).getEstado());
            }

            System.out.println("+----------+------------------------------------------+------------------+-----------------+");
        } else {
            System.out.println("No hay tickets en la Cola Desarrollador.");
        }

        //Filtro Resuelto
        for(int i = 0; i < pArray.size(); i++){
            if(pArray.get(i).getTipoCola().equals("Resuelto")){
                colaResuelto.add(pArray.get(i));
            }
        }

        if(colaResuelto.size() > 0) {
            System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");
            System.out.println("|Id Ticket | Nit             | Descripcion                               | Tipo Cola        | Estado          |");
            System.out.println("+----------+-----------------+------------------------------------------+------------------+-----------------+");

            for (int i=0; i< colaResuelto.size(); i++){
                System.out.format(formatoTabla, colaResuelto.get(i).getTicketId(), colaResuelto.get(i).getNitUsuario() ,colaResuelto.get(i).getDescipcionTicket(),
                        colaResuelto.get(i).getTipoCola(), colaResuelto.get(i).getEstado());
            }

            System.out.println("+----------+------------------------------------------+------------------+-----------------+");
        } else {
            System.out.println("No hay tickets en la Cola Resuelto.");
        }


    }


    /**
     * Cambia el Estado del Ticket a resuelto y genera Bitacora.
     * @param ticketEscalado
     * @param nitSoporte
     * @param mensajeBitacora
     * @return Retorna la Bitacora
     */
    public Bitacora resolverEstadoTicket(Tickets ticketEscalado, String nitSoporte, String mensajeBitacora){
        Integer iArray = getIndexCola(ticketEscalado.getTicketId());
        pArray.get(iArray).setEstado("Resuelto");
        Bitacora camEstadoBit = ControladorBitacora.cambiarEstadoBitacora(ticketEscalado.getTicketId(), nitSoporte, mensajeBitacora,"Resuelto");

        return camEstadoBit;
    }

    /**
     * Obtener Ticket por ID
     * @param idTicket
     * @return Ticket encontrado o null
     */
    public Tickets getIdTicket (Integer idTicket){
        boolean encontrado = false;
        Tickets ticketEncontrado = new Tickets();
        for (int i=0; i< pArray.size(); i++){
            if (pArray.get(i).getTicketId().equals(idTicket)){
                ticketEncontrado = new Tickets(pArray.get(i).getTicketId(), pArray.get(i).getNitUsuario(), pArray.get(i).getDescipcionTicket(),
                        pArray.get(i).getTipoCola(), pArray.get(i).getEstado());
                encontrado = true;
            }
        }

        //Verificar si lo encontro o no
        if(encontrado == false ){
            return null;
        } else {
            return ticketEncontrado;
        }

    }


    /**
     * Obtener Index del Array.
     * @param idTicket
     * @return Integer, index del ticket
     */
    public Integer getIndexCola (Integer idTicket){
        boolean encontrado = false;
        Integer ticketEncontrado = 0;
        for (int i=0; i< pArray.size(); i++){
            if (pArray.get(i).getTicketId().equals(idTicket)){
                ticketEncontrado = i;
                encontrado = true;
            }
        }

        //Verificar si lo encontro o no
        if(encontrado == false ){
            return null;
        } else {
            return ticketEncontrado;
        }

    }

}
