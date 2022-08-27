package edu.umg.controladores;

import edu.umg.modelos.Bitacora;
import edu.umg.modelos.Tickets;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.ArrayList;
import java.util.Random;
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
            return "soporte";
        } else if (ticketEscalado.getTipoCola() == "soporte" && ticketEscalado.getEstado() != "Resuelto" && ticketEscalado.getEstado() != "Asignado") {
            pArray.get(iArray).setTipoCola("desarrollo");
            return "desarrollo";
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

        int posArray = arrayAsignar.size() -1;
        if (posArray < 0) posArray = 0;
        Random r = new Random();
        int randNumPos = r.nextInt((posArray - 0) + 1) + 0;
//        int randNumPos = (int)(Math.random() * (((arrayAsignar.size()-1) - 0) + 1)) + 0;
        if(arrayAsignar.size() > 0){
            if(arrayAsignar.get(0).getTipoCola().equals("mesa")) {
                Table bitTableAsigMesa = new Table(5, BorderStyle.CLASSIC);

                bitTableAsigMesa.setColumnWidth(2, 70, 70);
                bitTableAsigMesa.addCell("Id Ticket");
                bitTableAsigMesa.addCell("Nit");
                bitTableAsigMesa.addCell("Descripcion");
                bitTableAsigMesa.addCell("Tipo Cola");
                bitTableAsigMesa.addCell("Estado");

                bitTableAsigMesa.addCell(String.format("%06d", arrayAsignar.get(0).getTicketId()));
                bitTableAsigMesa.addCell(arrayAsignar.get(0).getNitUsuario());
                bitTableAsigMesa.addCell(arrayAsignar.get(0).getDescipcionTicket());
                bitTableAsigMesa.addCell(arrayAsignar.get(0).getTipoCola());
                bitTableAsigMesa.addCell(arrayAsignar.get(0).getEstado());

                System.out.println(bitTableAsigMesa.render());

                //Cambiar Estado Ticket
                Integer iArray = getIndexCola(arrayAsignar.get(0).getTicketId());
                pArray.get(iArray).setEstado("Asignado");

                System.out.println("");

                return arrayAsignar.get(0).getTicketId();
            } else if (arrayAsignar.get(posArray).getTipoCola().equals("soporte")) {
                Table bitTableAsigSoporte = new Table(5, BorderStyle.CLASSIC);

                bitTableAsigSoporte.setColumnWidth(2, 70, 70);
                bitTableAsigSoporte.addCell("Id Ticket");
                bitTableAsigSoporte.addCell("Nit");
                bitTableAsigSoporte.addCell("Descripcion");
                bitTableAsigSoporte.addCell("Tipo Cola");
                bitTableAsigSoporte.addCell("Estado");

                bitTableAsigSoporte.addCell(String.format("%06d", arrayAsignar.get(0).getTicketId()));
                bitTableAsigSoporte.addCell(arrayAsignar.get(posArray).getNitUsuario());
                bitTableAsigSoporte.addCell(arrayAsignar.get(posArray).getDescipcionTicket());
                bitTableAsigSoporte.addCell(arrayAsignar.get(posArray).getTipoCola());
                bitTableAsigSoporte.addCell(arrayAsignar.get(posArray).getEstado());

                System.out.println(bitTableAsigSoporte.render());

                Integer iArray = getIndexCola(arrayAsignar.get(posArray).getTicketId());
                pArray.get(iArray).setEstado("Asignado");

                System.out.println("");

                return arrayAsignar.get(posArray).getTicketId();
            } else if (arrayAsignar.get(randNumPos).getTipoCola().equals("desarrollo")) {
                Table bitTableAsigDesarrollo = new Table(5, BorderStyle.CLASSIC);

                bitTableAsigDesarrollo.setColumnWidth(2, 70, 70);
                bitTableAsigDesarrollo.addCell("Id Ticket");
                bitTableAsigDesarrollo.addCell("Nit");
                bitTableAsigDesarrollo.addCell("Descripcion");
                bitTableAsigDesarrollo.addCell("Tipo Cola");
                bitTableAsigDesarrollo.addCell("Estado");

                bitTableAsigDesarrollo.addCell(String.format("%06d", arrayAsignar.get(randNumPos).getTicketId()));
                bitTableAsigDesarrollo.addCell(arrayAsignar.get(randNumPos).getNitUsuario());
                bitTableAsigDesarrollo.addCell(arrayAsignar.get(randNumPos).getDescipcionTicket());
                bitTableAsigDesarrollo.addCell(arrayAsignar.get(randNumPos).getTipoCola());
                bitTableAsigDesarrollo.addCell(arrayAsignar.get(randNumPos).getEstado());

                System.out.println(bitTableAsigDesarrollo.render());
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

            Table bitTableSolucion = new Table(5, BorderStyle.CLASSIC);

            bitTableSolucion.setColumnWidth(2, 70, 70);
            bitTableSolucion.addCell("Id Ticket");
            bitTableSolucion.addCell("Nit");
            bitTableSolucion.addCell("Descripcion");
            bitTableSolucion.addCell("Tipo Cola");
            bitTableSolucion.addCell("Estado");

            bitTableSolucion.addCell(String.format("%06d", tAsignado.get(0).getTicketId()));
            bitTableSolucion.addCell(tAsignado.get(0).getNitUsuario());
            bitTableSolucion.addCell(tAsignado.get(0).getDescipcionTicket());
            bitTableSolucion.addCell(tAsignado.get(0).getTipoCola());
            bitTableSolucion.addCell(tAsignado.get(0).getEstado());

            System.out.println(bitTableSolucion.render());

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
        Table bitTableMostrar = new Table(5, BorderStyle.CLASSIC);

        bitTableMostrar.setColumnWidth(2, 70, 70);
        bitTableMostrar.addCell("Id Ticket");
        bitTableMostrar.addCell("Nit");
        bitTableMostrar.addCell("Descripcion");
        bitTableMostrar.addCell("Tipo Cola");
        bitTableMostrar.addCell("Estado");

        for (int i=0; i< pArray.size(); i++){
            bitTableMostrar.addCell(String.format("%06d", pArray.get(i).getTicketId()));
            bitTableMostrar.addCell(pArray.get(i).getNitUsuario());
            bitTableMostrar.addCell(pArray.get(i).getDescipcionTicket());
            bitTableMostrar.addCell(pArray.get(i).getTipoCola());
            bitTableMostrar.addCell(pArray.get(i).getEstado());

        }
        System.out.println(bitTableMostrar .render());
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

        Table bitTable = new Table(5, BorderStyle.CLASSIC);

        bitTable.setColumnWidth(2, 70, 70);
        bitTable.addCell("Id Ticket");
        bitTable.addCell("Nit");
        bitTable.addCell("Descripcion");
        bitTable.addCell("Tipo Cola");
        bitTable.addCell("Estado");
        for (int i=0; i< ticketArrayFiltro.size(); i++){
            bitTable.addCell(String.format("%06d", ticketArrayFiltro.get(i).getTicketId()));
            bitTable.addCell(ticketArrayFiltro.get(i).getNitUsuario());
            bitTable.addCell(ticketArrayFiltro.get(i).getDescipcionTicket());
            bitTable.addCell(ticketArrayFiltro.get(i).getTipoCola());
            bitTable.addCell(ticketArrayFiltro.get(i).getEstado());
        }
        System.out.println(bitTable.render());

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


        Table ticketUsuarioTable = new Table(5, BorderStyle.CLASSIC);

        ticketUsuarioTable.setColumnWidth(2, 70, 70);
        ticketUsuarioTable.addCell("Id Ticket");
        ticketUsuarioTable.addCell("Nit");
        ticketUsuarioTable.addCell("Descripcion");
        ticketUsuarioTable.addCell("Tipo Cola");
        ticketUsuarioTable.addCell("Estado");

        for (int i=0; i< ticketArrayUsuario.size(); i++){
            ticketUsuarioTable.addCell(String.format("%06d", ticketArrayUsuario.get(i).getTicketId()));
            ticketUsuarioTable.addCell(ticketArrayUsuario.get(i).getNitUsuario());
            ticketUsuarioTable.addCell(ticketArrayUsuario.get(i).getDescipcionTicket());
            ticketUsuarioTable.addCell(ticketArrayUsuario.get(i).getTipoCola());
            ticketUsuarioTable.addCell(ticketArrayUsuario.get(i).getEstado());

        }
        System.out.println(ticketUsuarioTable.render());
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

            Table ticketTableMesa = new Table(5, BorderStyle.CLASSIC);

            ticketTableMesa.setColumnWidth(2, 70, 70);
            ticketTableMesa.addCell("Id Ticket");
            ticketTableMesa.addCell("Nit");
            ticketTableMesa.addCell("Descripcion");
            ticketTableMesa.addCell("Tipo Cola");
            ticketTableMesa.addCell("Estado");

            for (int i=0; i< colaMesaAyuda.size(); i++){
                ticketTableMesa.addCell(String.format("%06d", colaMesaAyuda.get(i).getTicketId()));
                ticketTableMesa.addCell(colaMesaAyuda.get(i).getNitUsuario());
                ticketTableMesa.addCell(colaMesaAyuda.get(i).getDescipcionTicket());
                ticketTableMesa.addCell(colaMesaAyuda.get(i).getTipoCola());
                ticketTableMesa.addCell(colaMesaAyuda.get(i).getEstado());

            }
            System.out.println(ticketTableMesa.render());

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
            Table ticketTableSoporte = new Table(5, BorderStyle.CLASSIC);

            ticketTableSoporte.setColumnWidth(2, 70, 70);
            ticketTableSoporte.addCell("Id Ticket");
            ticketTableSoporte.addCell("Nit");
            ticketTableSoporte.addCell("Descripcion");
            ticketTableSoporte.addCell("Tipo Cola");
            ticketTableSoporte.addCell("Estado");
            for (int i=0; i< colaSoporteTec.size(); i++){
                ticketTableSoporte.addCell(String.format("%06d", colaSoporteTec.get(i).getTicketId()));
                ticketTableSoporte.addCell(colaSoporteTec.get(i).getNitUsuario());
                ticketTableSoporte.addCell(colaSoporteTec.get(i).getDescipcionTicket());
                ticketTableSoporte.addCell(colaSoporteTec.get(i).getTipoCola());
                ticketTableSoporte.addCell(colaSoporteTec.get(i).getEstado());
            }
            System.out.println(ticketTableSoporte.render());
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
            Table ticketTableDesarrollo = new Table(5, BorderStyle.CLASSIC);

            ticketTableDesarrollo.setColumnWidth(2, 70, 70);
            ticketTableDesarrollo.addCell("Id Ticket");
            ticketTableDesarrollo.addCell("Nit");
            ticketTableDesarrollo.addCell("Descripcion");
            ticketTableDesarrollo.addCell("Tipo Cola");
            ticketTableDesarrollo.addCell("Estado");

            for (int i=0; i< colaDesarrollo.size(); i++){
                ticketTableDesarrollo.addCell(String.format("%06d", colaDesarrollo.get(i).getTicketId()));
                ticketTableDesarrollo.addCell(colaDesarrollo.get(i).getNitUsuario());
                ticketTableDesarrollo.addCell(colaDesarrollo.get(i).getDescipcionTicket());
                ticketTableDesarrollo.addCell(colaDesarrollo.get(i).getTipoCola());
                ticketTableDesarrollo.addCell(colaDesarrollo.get(i).getEstado());
            }
            System.out.println(ticketTableDesarrollo.render());
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
            Table ticketTableResuelto = new Table(5, BorderStyle.CLASSIC);

            ticketTableResuelto.setColumnWidth(2, 70, 70);
            ticketTableResuelto.addCell("Id Ticket");
            ticketTableResuelto.addCell("Nit");
            ticketTableResuelto.addCell("Descripcion");
            ticketTableResuelto.addCell("Tipo Cola");
            ticketTableResuelto.addCell("Estado");
            for (int i=0; i< colaResuelto.size(); i++){
                ticketTableResuelto.addCell(String.format("%06d", colaResuelto.get(i).getTicketId()));
                ticketTableResuelto.addCell(colaResuelto.get(i).getNitUsuario());
                ticketTableResuelto.addCell(colaResuelto.get(i).getDescipcionTicket());
                ticketTableResuelto.addCell(colaResuelto.get(i).getTipoCola());
                ticketTableResuelto.addCell(colaResuelto.get(i).getEstado());
            }
            System.out.println(ticketTableResuelto.render());
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
