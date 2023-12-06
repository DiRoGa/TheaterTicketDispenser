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
class TheaterArea implements Serializable{
    private final String areaDir;
    private int rows;
    private String name;
    private SeatState[][] seats;
    private final ArrayList<String> info = new ArrayList<>();
    private boolean isFirstTime = true;
    private String newDir;

    public TheaterArea(String areaDir) {
        this.areaDir = areaDir;
        this.newDir = newDir;
        this.name = name;
        this.seats = seats;
        this.isFirstTime = isFirstTime;
        this.read();
    }    
    
    private void read(){
        try {
            File areaInfo = new File("./files/ConfigFilesExample/" + areaDir);
            Scanner reader = new Scanner(areaInfo);
            
            while (reader.hasNextLine()) {
                this.info.add(reader.nextLine());             
            }                       
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    
    public int getRows() {
        this.rows = info.size();
        return rows;
    }

    public int getCols() {   
        String aux = "";
        
        for (int i = 0; i < info.size(); i++) {            
            if (info.get(i).length() > aux.length()){
                aux = info.get(i);
            }
        }
        return aux.length();
    }
    
    /*public String getName() {
        return name;
    }*/

    public String getAreaDir() {
        return areaDir;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void loadSeat() {
        if (isFirstTime == true) {
            isFirstTime = false;          
            
            seats = new SeatState[this.getRows()][this.getCols()];
            
            for (int i = 0; i < this.getRows(); i++) {
                for (int j = 0; j < this.getCols(); j++) {
                    seats[i][j] = SeatState.empty;
                }
            }

            for (int i = 0; i < this.getRows(); i++) {
                for (int j = 0; j < this.info.get(i).length(); j++) {
                    if (this.info.get(i).charAt(j) == '*') {
                        seats[i][j] = SeatState.free;
                    }
                }
            }
            
            sameDir(areaDir);
        }
    }
    
    public SeatState getSeat(int row, int col){
        return seats[row][col];
    }
    
    public void setSeat(int row, int col){
        this.seats[row][col] = SeatState.occupied;
    }   

    private void sameDir(String dir) {
        this.newDir = dir;
    }
}