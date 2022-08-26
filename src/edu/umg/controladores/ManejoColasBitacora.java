package edu.umg.controladores;

import edu.umg.modelos.Bitacora;

import java.util.ArrayList;

public class ManejoColasBitacora {

    /**
     * Array de Bitacoras
     */
    public ArrayList<Bitacora> bitacoraArray = new ArrayList();

    /**
     * Agrega Bitacora a Array
     * @param bitVariable
     */
    public void agregarColaBitacora (Bitacora bitVariable){
        bitacoraArray.add(bitVariable);
    }

    /**
     * @return array de Bitacoras
     */
    public ArrayList<Bitacora> getBitacorasArray(){
        return bitacoraArray;
    }

    /**
     * Muestra las Bitacoras por Ticket
     * @param idTicket
     */
    public void mostrarBitacorasXTicket(Integer idTicket){
        //Filtrar Array y devolver bitacora conforme al ticket
        ArrayList<Bitacora> bitArrayFiltro = new ArrayList();
        for(int i = 0; i < bitacoraArray.size(); i++){
            if(bitacoraArray.get(i).getTicket().equals(idTicket)){
                bitArrayFiltro.add(bitacoraArray.get(i));
            }
        }


        String formatoTabla = "| %-8d | %-16s |%-40s | %-26s |%-16s |%n";

        System.out.println("+----------+-----------------+------------------------------------------+----------------------------+-----------------+");
        System.out.println("|Id Ticket | Nit Soporte     | Mensaje                                  | Fecha Bitacora             | Evento          |");
        System.out.println("+----------+-----------------+------------------------------------------+----------------------------+-----------------+");

        for (int i=0; i< bitArrayFiltro.size(); i++){
            System.out.format(formatoTabla, bitArrayFiltro.get(i).getTicket(), bitArrayFiltro.get(i).getNitSoporte(), bitArrayFiltro.get(i).getMensaje(),
                    bitArrayFiltro.get(i).getFechaBitacora(), bitArrayFiltro.get(i).getEvento());
        }
        System.out.println("+----------+-----------------+------------------------------------------+----------------------------+-----------------+");
    }

}
