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
public class IdiomSelectionScreen extends Screen{
    private final DispenserManager manager;
    
    public IdiomSelectionScreen(ScreenMode mode, DispenserManager manager) {
        super(mode, manager);
        this.manager = manager;
    }
    
    public void menu() throws IOException, CommunicationException, ClassNotFoundException{
        manager.showScreen(30, this, 1);       
    }
}
