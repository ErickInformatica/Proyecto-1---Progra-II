package edu.umg.controladores;

import edu.umg.modelos.Bitacora;
import edu.umg.modelos.Tickets;

import java.util.ArrayList;
import java.util.Scanner;

public class ManejoColas {

    private ArrayList<Tickets> pArray = new ArrayList();

    //Agreagr a Array
    public void agregarCola (Tickets ticket){
        pArray.add(ticket);
    }

    //Devolver Array
    public ArrayList<Tickets> getTicketsArray(){
        return pArray;
    }

    public Tickets getLIFO (){
        if(!pArray.isEmpty()){
            Tickets t = pArray.get(pArray.size()-1);
            return t;
        } else {
            return null;
        }
    }

    public Tickets eliminarLIFO (){
        if(!pArray.isEmpty()){
            Tickets t = pArray.get(pArray.size()-1);
            pArray.remove(pArray.size()-1);
            return t;
        } else {
            return null;
        }
    }

    public Tickets getFIFO (){
        if(!pArray.isEmpty()){
            Tickets t = pArray.get(0);
            return t;
        } else {
            return null;
        }
    }

    public Tickets eliminarFIFO (){
        if(!pArray.isEmpty()){
            Tickets t = pArray.get(0);
            pArray.remove(0);
            return t;
        } else {
            return null;
        }
    }

    public String escalarTicket(Tickets ticketEscalado, String nitSoporte){
        Integer iArray = getIndexCola(ticketEscalado.getTicketId());
        if(ticketEscalado.getTipoCola().equals("Mesa de Ayuda") && ticketEscalado.getEstado() != "Resuelto" && ticketEscalado.getEstado() != "Asignado"){
            pArray.get(iArray).setTipoCola("Soporte Tecnico");
            return "Soporte Tecnico";
        } else if (ticketEscalado.getTipoCola() == "Soporte Tecnico" && ticketEscalado.getEstado() != "Resuelto" && ticketEscalado.getEstado() != "Asignado") {
            pArray.get(iArray).setTipoCola("Desarrollador");
            return "Desarrollador";
        } else{
            return null;
        }
    }

    //Asignar Ticket
    public Integer asignarTicket(String rolIngreso){
        ArrayList<Tickets> arrayAsignar = new ArrayList();
        for (int i=0; i< pArray.size(); i++){
            if(pArray.get(i).getTipoCola().equals(rolIngreso) && pArray.get(i).getEstado() != "Resuelto" && pArray.get(i).getEstado() != "Asignado"){
                arrayAsignar.add(pArray.get(i));
            }
        }

        int randNumPos = (int)(Math.random() * ((arrayAsignar.size()-1 - 0) + 1)) + 0;

        if(arrayAsignar.size() > 0){
            if(arrayAsignar.get(0).getTipoCola() == "Mesa de Ayuda") {
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
            } else if (arrayAsignar.get(arrayAsignar.size()-1).getTipoCola() == "Soporte Tecnico") {
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
            } else if (arrayAsignar.get(randNumPos).getTipoCola() == "Desarrollador") {
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

    //Solucion Ticket
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

            return bitResuelta;
        } else {
            return null;
        }
    }


    //Mostrar todos los tickets
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

    // Mostrar un ticket
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

    public Bitacora resolverEstadoTicket(Tickets ticketEscalado, String nitSoporte, String mensajeBitacora){
        Integer iArray = getIndexCola(ticketEscalado.getTicketId());
        pArray.get(iArray).setEstado("Resuelto");
        Bitacora camEstadoBit = ControladorBitacora.cambiarEstadoBitacora(ticketEscalado.getTicketId(), nitSoporte, mensajeBitacora,"Resuelto");

        return camEstadoBit;
    }

    //Obtener Ticket por ID
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


    //Obtener Index del Array.
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
