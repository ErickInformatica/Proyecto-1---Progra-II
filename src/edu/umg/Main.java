package edu.umg;

import edu.umg.controladores.ControladorBitacora;
import edu.umg.controladores.ControladorTicket;
import edu.umg.controladores.ManejoColas;
import edu.umg.controladores.ManejoColasBitacora;
import edu.umg.modelos.Bitacora;
import edu.umg.modelos.Tickets;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        int exit = 0;
        int opcion = 0;
        int opcionReporte = 0;

        ArrayList<Tickets> ticketsAsignados = new ArrayList();

        //Controlador para manejar colas
        ManejoColas colaTickets = new ManejoColas();
        ManejoColasBitacora colaBitacora = new ManejoColasBitacora();
        ControladorTicket controladorTicket = new ControladorTicket();



        Scanner lecturaCadena = new Scanner(System.in);


        System.out.println("Bienvenido al Sistema de Tickets, ingrese sus datos al sistema.");

        //Ingreso de Nit Login
        System.out.println("Ingrese su nit: ");
        String nitIngreso = lecturaCadena.nextLine();

        //Ingreso de Rol Valido
        System.out.println("Ingrese su rol");
        String rolIngreso = "";
        while (exit != 1) {
            rolIngreso = lecturaCadena.nextLine();
            System.out.println(rolIngreso == "Soporte Tecnico");
            //Verifica el ingreso de un rol valido
            if(rolIngreso.equals("Mesa de Ayuda")  || rolIngreso.equals("Soporte Tecnico") || rolIngreso.equals("Desarrollador")) {
                exit = 1;
            } else {
                System.out.println("Debe ingresar un rol valido de los siguiente: Mesa de Ayuda, Soporte Tecnico o Desarrollador");
            }
        }
        exit = 0;

        while (exit != 1) {
            Scanner entradaInteger = new Scanner(System.in);

            System.out.println("Bienvenido al Sistema de Tickets, seleccione la opcion a trabajar.");
            System.out.println("1. Crear Ticket");
            System.out.println("2  Asignar Ticket");
            System.out.println("3. Mover Ticket");
            System.out.println("4. Solucion Ticket");
            System.out.println("5. Reportes");
            try {
                opcion = entradaInteger.nextInt();
            } catch (Exception e) {
                System.out.println("Opcion no valida.");
                entradaInteger = new Scanner(System.in);
            }

            switch (opcion){
                case 1:
                    System.out.println("Crear Ticket, ingrese los parametros solicitados.");
                    System.out.println("Ingrese la Descripcion del Ticket: ");
                    String descCrear = lecturaCadena.nextLine();

                    //Crear Ticket
                    Tickets ticketCrear = controladorTicket.agregarTicket(nitIngreso, descCrear);
                    colaTickets.agregarCola(ticketCrear);

                    //Crear Bitacora
                    Bitacora bitCreada = ControladorBitacora.agregarBitacora(ticketCrear.getTicketId(),String.valueOf(nitIngreso));
                    colaBitacora.agregarColaBitacora(bitCreada);
                    break;
                case 2:
                    Integer asignarTicketId = colaTickets.asignarTicket(rolIngreso);

                    if(asignarTicketId == null){
                        System.out.println("No hay tickets en su cola por hacer.");
                    } else {
                        //Verificar si ya tiene ticket asignado
                        if(ticketsAsignados.size() == 0){
                            Tickets ticketAsignado = colaTickets.getIdTicket(asignarTicketId);
                            ticketsAsignados.add(ticketAsignado);

                            Bitacora bitAsignada = ControladorBitacora.asignarBitacora(asignarTicketId, nitIngreso);
                            colaBitacora.agregarColaBitacora(bitAsignada);
                            System.out.println("Ticket Asignado con Exito.");
                        } else {
                            System.out.println("Ya tiene un ticket asignado.");
                        }

                    }
                    break;
                case 3:
                    colaTickets.mostrarTickets();

                    System.out.println("");
                    System.out.println("Ingrese el id del Ticket a Escalar: ");
                    Integer idTicketMover = entradaInteger.nextInt();

                    if(colaTickets.getIdTicket(idTicketMover) == null){
                        System.out.println("Debe ingresar un Id Valido");
                    } else {
                        String tipoColaEscalado = colaTickets.escalarTicket(colaTickets.getIdTicket(idTicketMover), nitIngreso);
                        if(tipoColaEscalado == null){
                            System.out.println("No puede escalar mas el Ticket, el Desarrollador es el maximo.");
                        } else {
                            //Crear Bitacora Escalado
                            Bitacora bitEscalada = ControladorBitacora.escalarBitacora(idTicketMover, nitIngreso, tipoColaEscalado);
                            colaBitacora.agregarColaBitacora(bitEscalada);
                        }
                    }

                    break;
                case 4:
                    Bitacora bitacoraSolucion = colaTickets.solucionTicket(ticketsAsignados, nitIngreso);

                    if(bitacoraSolucion == null){
                        System.out.println("No hay tickets asignados para solucionar.");
                    } else {
                        //Verificar si ya tiene ticket asignado
                        if(ticketsAsignados.size() > 0){

                            Bitacora bitAsignada = ControladorBitacora.resueltoBitacora(ticketsAsignados.get(0).getTicketId(), nitIngreso);
                            colaBitacora.agregarColaBitacora(bitAsignada);
                            System.out.println("Ticket Asignado con Exito.");
                        } else {
                            System.out.println("No tiene un ticket asignado.");
                        }
                    }

                    break;
                case 5:

                    System.out.println("Seleccione el reporte a visualizar:");
                    System.out.println("1. Listado General de Tickets");
                    System.out.println("2  Listado Ticket por Cola");
                    System.out.println("3. Listado Ticket por Usuario");
                    opcionReporte = entradaInteger.nextInt();
                    switch (opcionReporte){
                        case 1:
                            ArrayList<Tickets> ticketsV = colaTickets.getTicketsArray();
                            for(int i = 0; i < ticketsV.size(); i++){
                                colaTickets.mostrarIdTickets(ticketsV.get(i).getTicketId());
                                colaBitacora.mostrarBitacorasXTicket(ticketsV.get(i).getTicketId());
                            }
//                            colaTickets.reporteTicketGeneral();
//                            System.out.println();
                            break;
                        case 2:
                            // Leer en String
                            String datos = new String(Files.readAllBytes(Paths.get("./informacion.json")));

                            // Leer JSON
                            JSONArray jArray = new JSONArray(datos);
                            for(int i = 0; i < jArray.length(); i++){

                                JSONObject object = jArray.getJSONObject(i);

                                Integer ticketJson = object.getInt("ticket");
                                Object nitJson = object.get("nitUsuario");
                                String mensajeJson = object.getString("problema");
                                String colaJson = object.getString("cola");

                                String nitJsonFinal = "";

                                if(nitJson instanceof Integer){
                                    nitJsonFinal = nitJson.toString();
                                } else {
                                    nitJsonFinal = nitJson.toString();
                                }

                                //Crear Ticket
                                Tickets ticketCrearMasivo = controladorTicket.agregarTicketMasivo(nitJsonFinal, mensajeJson, colaJson);
                                colaTickets.agregarCola(ticketCrearMasivo);

                                //Crear Bitacora
                                Bitacora bitCreadaMasivo = ControladorBitacora.agregarBitacora(ticketCrearMasivo.getTicketId(),String.valueOf(nitIngreso));
                                colaBitacora.agregarColaBitacora(bitCreadaMasivo);

                            }
                            break;
                        case 3:
                            break;
                    }

                    break;
            }


        }
    }
}
