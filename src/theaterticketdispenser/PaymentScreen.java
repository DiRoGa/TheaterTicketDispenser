/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

import java.io.IOException;
import javax.naming.CommunicationException;

/**
 *
 * @author d.rodriguezgal.2020
 */
public class PaymentScreen extends Screen{
    private final DispenserManager manager;

    public PaymentScreen(ScreenMode mode, DispenserManager manager) throws IOException, CommunicationException, ClassNotFoundException {
        super(mode, manager);
        this.manager = manager;
        this.menu();
    }
    
    public final void menu() throws IOException, CommunicationException, ClassNotFoundException{
        manager.showScreen(30, this, 5); 
    }   
}
