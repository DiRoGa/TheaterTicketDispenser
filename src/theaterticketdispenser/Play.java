/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author d.rodriguezgal.2020
 */
public class Play {
    private final String playDir;
    private String title;
    private String description;
    private String image;
    private final ArrayList<String> info = new ArrayList<>();
    
    public Play(String playDir) {
        this.playDir = playDir;        
        this.title = title;
        this.description = description;
        this.image = image;
        this.read();
    }

    private void read(){
        try {
            File playInfo = new File(playDir);
            Scanner reader = new Scanner(playInfo);
            
            while (reader.hasNextLine()) {
                this.info.add(reader.nextLine());   
            }                   
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getTitle() {  
        this.title = info.get(0).replace("play_name:", "");
        return title;
    }

    public String getDescription() {
        this.description = info.get(2).replace("description:", "");
        return description;
    }

    public String getImage() {
        this.image = info.get(1).replace("play_poster:", "");
        return image;
    }  
}
