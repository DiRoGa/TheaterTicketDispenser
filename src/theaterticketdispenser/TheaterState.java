/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author d.rodriguezgal.2020
 */
public class TheaterState implements Serializable{
    private ArrayList<TheaterAreaState> areasState = new ArrayList<>();
    private final LocalDate date;
    private final Theater theater;

    public TheaterState(Theater theater, LocalDate date) throws IOException {
        this.date = date;
        this.theater = theater;
        this.areasState = areasState;
        this.setAreas();
        this.Serializer();
    }

    public LocalDate getDate() { 
        return date;
    }
    
    public int getNumAreas() {
        return theater.getNumAreas();       
    }
    
    private void setAreas() {
        for (int i = 0; i < theater.getNumAreas(); i++) {
            TheaterAreaState area = new TheaterAreaState(theater.getArea(i));
            areasState.add(area);
        }
    }
    
    public TheaterAreaState getArea(int pos){            
        return areasState.get(pos);        
    }
    
    private void Serializer() throws FileNotFoundException, IOException{
        try {
            FileOutputStream estadoTeatro = new FileOutputStream("./files/ConfigFilesExample/states/" + this.getDate());
            ObjectOutputStream escritor = new ObjectOutputStream(estadoTeatro);

            escritor.writeObject(this);

            escritor.close();
            estadoTeatro.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
