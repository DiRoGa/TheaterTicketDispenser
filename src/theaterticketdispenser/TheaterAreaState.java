/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

import java.io.Serializable;

/**
 *
 * @author d.rodriguezgal.2020
 */
class TheaterAreaState implements Serializable{
    private final TheaterArea area;
    private String name;

    public TheaterAreaState(TheaterArea area){
        this.area = area;
        this.name = name;
    }

    public SeatState getSeatState(int row, int col) {
        return area.getSeat(row, col);
    }

    public void loadSeats(){
        this.area.loadSeat();
    }
    
    public void setSeats(int row, int col) { 
        this.area.setSeat(row, col);
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return area.getRows();
    }

    public int getCols() {
        return area.getCols();
    }
}
