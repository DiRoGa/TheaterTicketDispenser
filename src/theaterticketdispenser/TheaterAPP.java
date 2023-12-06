/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package theaterticketdispenser;

import java.io.IOException;
import javax.naming.CommunicationException;

/**
 *
 * @author d.rodriguezgal.2020
 */
public class TheaterAPP {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws javax.naming.CommunicationException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, CommunicationException, ClassNotFoundException {
        TheaterManager tm = new TheaterManager();
        tm.run();
    }
}
