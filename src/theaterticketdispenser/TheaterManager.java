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
class TheaterManager {
    public void run() throws IOException, CommunicationException, ClassNotFoundException{
        DispenserManager manager = new DispenserManager(); 
        Screen screen = new Screen(ScreenMode.options, manager);    
              
        screen.setDispenserManager(manager);
        
        while(true){
            new WelcomeScreen(ScreenMode.options, manager);
        }
    }
}
