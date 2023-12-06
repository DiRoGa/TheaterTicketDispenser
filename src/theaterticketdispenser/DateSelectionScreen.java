/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import sienens.TheaterTicketDispenser;
import java.time.LocalDate;

/**
 *
 * @author d.rodriguezgal.2020
 */
public class DateSelectionScreen extends Screen {
    private final Theater theater;
    private TheaterTicketDispenser dispenser;
    private final DispenserManager manager;
    private final ArrayList<LocalDate> dates;
    private Map<LocalDate, TheaterState> schedule = new HashMap<>(4);
    private final boolean[] isNew;

    public DateSelectionScreen(ScreenMode mode, DispenserManager manager, Theater theater) throws IOException, CommunicationException, ClassNotFoundException {
        super(mode, manager);
        this.dispenser = dispenser;
        this.schedule = schedule;
        this.dates = new ArrayList<>();
        this.manager = manager;
        this.theater = theater;
        this.isNew = new boolean[5];
    }

    private List<LocalDate> getDatesFromToday() {
        int j = 0;
        LocalDate newDay = LocalDate.now();       
        
        for (int i = 0; i < 4; i++) {           
            LocalDate nextDay = newDay.plusDays(j);
            
            if (nextDay.getDayOfWeek() == DayOfWeek.MONDAY){
                j++;
                LocalDate overDay = newDay.plusDays(j);
                this.dates.add(overDay);
            } else {
                this.dates.add(nextDay);
            }             
            j++;
        }                       
        
        return dates;
    }

    private void loadStateFiles() throws IOException, ClassNotFoundException{      
       ArrayList<LocalDate> weekDays = (ArrayList<LocalDate>) this.getDatesFromToday();

        try {
            for (int i = 0; i < weekDays.size(); i++) {  //Por cada opción de día, creo un fichero o compruebo su existencia
                String directory = "./files/ConfigFilesExample/states/";
                File state = new File(directory + weekDays.get(i));              
                            
                String fileName = directory + weekDays.get(i);   
                
                    if(!state.exists()){                                        //Si el archivo que vamos a crear no existe en el directorio
                        state.createNewFile();                                  //Se crea          
                        this.schedule.put(weekDays.get(i), new TheaterState(theater, weekDays.get(i)));
                        
                        isNew[i] = true;
                        
                        System.out.println("File created.");                    
                    } else {                                                    //Si el archivo ya existe     
                        if(state.length() >= 1) {                                //Si el archivo es mayor o igual a uno (por lo tanto, alguien ha comrpado entradas en una instancia anterior)
                            FileInputStream estadoTeatro = new FileInputStream(fileName);
                            ObjectInputStream lector = new ObjectInputStream(estadoTeatro);

                            TheaterState nuevoEstado = (TheaterState) lector.readObject();
                            this.schedule.put(weekDays.get(i), nuevoEstado); //Se añade al mapa la fecha (como llave) y el theaterState(como valor)

                            estadoTeatro.close();
                            lector.close();
                            
                            isNew[i] = false;
                            
                            System.out.println("File already created.");      
                        }
                    }
            }
                    
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TheaterState getState(int pos) throws FileNotFoundException, IOException, ClassNotFoundException{        
        return schedule.get(dates.get(pos));
    }
    
    public String getDay(int pos){
        return dates.get(pos).toString();
    }
    
    public LocalDate getDate(int pos){
        return dates.get(pos);
    }

    public boolean getIsNew(int pos){
        return isNew[pos];
    }
    
    public void menu() throws IOException, CommunicationException, ClassNotFoundException {  
        this.loadStateFiles();

        manager.showScreen(30, this, 2);              
    }
}
