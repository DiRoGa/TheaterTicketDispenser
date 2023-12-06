/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;

/**
 *
 * @author d.rodriguezgal.2020
 */
public class SeatSelectionScreen extends Screen{
    private TheaterAreaState selectArea;
    private TheaterState theaterState;
    private final DispenserManager manager;

    public SeatSelectionScreen(ScreenMode mode, DispenserManager manager) throws IOException, CommunicationException, ClassNotFoundException {
        super(mode, manager);
        this.manager = manager;
        this.selectArea = selectArea;  
        this.theaterState = theaterState;
    }
    
    public void updateState(TheaterState theaterState) throws IOException{
        try {
            FileOutputStream estadoTeatro = new FileOutputStream("./files/ConfigFilesExample/states/" + theaterState.getDate());
            ObjectOutputStream escritor = new ObjectOutputStream(estadoTeatro);

            escritor.writeObject(theaterState);

            escritor.close();
            estadoTeatro.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void menu() throws IOException, CommunicationException, ClassNotFoundException{
        manager.showScreen(30, this, 4); 
    }
}
