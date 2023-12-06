/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author d.rodriguezgal.2020
 */
class Theater implements Serializable{
    private final String theaterDir;
    private String theaterPlay;
    private String theaterImage;
    private final ArrayList<String> info;
    private final ArrayList<String> area = new ArrayList<>();
    private ArrayList<TheaterArea> theaterAreas = new ArrayList<>();
    private String areaInfo;
    private int pos;
    
    public Theater(String theaterDir) { 
        this.pos = pos;
        this.theaterDir = theaterDir;
        this.theaterPlay = theaterPlay;
        this.theaterImage = theaterImage;
        this.theaterAreas = theaterAreas;
        this.info = new ArrayList<>();
        this.read();
    }
   
    private void read(){
        try {
            File theaterInfo = new File(theaterDir);
            Scanner reader = new Scanner(theaterInfo);
            
            while (reader.hasNextLine()) {
                this.info.add(reader.nextLine());
            }                      
            
            for (int i = 2; i < info.size(); i++) {
                area.add(info.get(i));
            }  
            
            this.setAreas();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAreaInfo(int pos) {   
        this.areaInfo = area.get(pos);
    }
    
    public String getAreaInfo(int pos) {
        return area.get(pos);
    }
    
    public String getAreaPrice(){
        String[] areaNames = areaInfo.split(";");
        return areaNames[1].substring(0, areaNames[1].length()-1);       
    }
        
    public String getAreaName() {
        String[] areaNames = areaInfo.split(";",3);
        String[] name = areaNames[0].split(":");
        return name[1];
    }   
    
    public int getNumAreas() {
        return area.size()-1; 
    }

    public String getFestivos(){
        String areaInfo = area.get(area.size()-1);
        String[] mult = areaInfo.split(":");
        return mult[1];
    }
    
    private void setAreas(){
        for (int i = 0; i < this.getNumAreas(); i++) {
            theaterAreas.add(new TheaterArea(area.get(i).split(";",3)[2]));
        }
    }
    
    public TheaterArea getArea(int pos){
      return theaterAreas.get(pos);
    }
}
