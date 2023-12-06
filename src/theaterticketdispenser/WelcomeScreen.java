/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

import java.io.IOException;
import javax.naming.CommunicationException;
import sienens.TheaterTicketDispenser;

/**
 *
 * @author d.rodriguezgal.2020
 */
final class WelcomeScreen extends Screen {

    private TheaterTicketDispenser dispenser;
    private Theater theater;
    private final DispenserManager manager;

    public WelcomeScreen(ScreenMode mode, DispenserManager manager) throws IOException, CommunicationException, ClassNotFoundException {
        super(mode, manager);
        this.theater = theater;
        this.manager = manager;
        this.dispenser = dispenser;
        this.menu();
    }

    public void menu() throws IOException, CommunicationException, ClassNotFoundException { 
        manager.showScreen(30, this, 0); 
    }
}
