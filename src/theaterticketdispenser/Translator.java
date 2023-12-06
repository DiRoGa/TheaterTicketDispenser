/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author d.rodriguezgal.2020
 */
class Translator {
    private final String translatorFile;
    private final HashMap<String,String> messages;

    public Translator(String translatorFile) {
        this.translatorFile = translatorFile;
        this.messages = new HashMap<>();
        this.read();
    }

    private void read() {      
        File translationInfo = new File(translatorFile);
        try {
            Scanner reader = new Scanner(translationInfo);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(":");
                if(parts.length == 2){
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    this.messages.put(key, value);
                }                   
            }                     
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String translate(String msg) {
        String text = this.messages.get(msg);
        return text;
    }
}
