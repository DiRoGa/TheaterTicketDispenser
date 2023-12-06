/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.naming.CommunicationException;
import sienens.TheaterTicketDispenser;
import urjc.UrjcBankServer;

/**
 *
 * @author d.rodriguezgal.2020
 */
class DispenserManager {
    private final TranslatorManager translator = new TranslatorManager();
    private DateSelectionScreen date;
    private IdiomSelectionScreen idiom;
    private SeatSelectionScreen seatSel;
    private final TheaterTicketDispenser dispenser = new TheaterTicketDispenser();
    private final UrjcBankServer bank = new UrjcBankServer();
    private final Theater theater = new Theater("./files/ConfigFilesExample/theater.txt");
    private TheaterState theaterState;
    private TheaterAreaState theaterAreaState;
    private Screen screen;
    private int options;
    private int maxSeats;
    private ArrayList<String> choosenSeats;
    private int day;
    private int transPos;

    public void showScreen(int time, Screen screen, int options) throws IOException, CommunicationException, ClassNotFoundException {
        this.date = date;
        this.idiom = idiom;
        this.screen = screen;
        this.options = options;
        this.maxSeats = 0;

        this.ScreenManager(dispenser);
        screen.setDispenserManager(this);

        switch (options) {           
            case 0:
                this.date = new DateSelectionScreen(screen.getScreenMode(), this, theater);
                this.idiom = new IdiomSelectionScreen(screen.getScreenMode(), this);
                
                switch (dispenser.waitEvent(time)) {
                    case 'A':                                            
                        screen.optionButtonPressed('A');
                        this.ScreenManager(dispenser);
                        idiom.menu();
                        break;

                    case 'B':                       
                        screen.optionButtonPressed('B');                    
                        this.ScreenManager(dispenser);
                        date.menu();
                        break;
                        
                    case 'D':
                        break;
                }
                screen.end();
                break;
            case 1:
                char c = dispenser.waitEvent(time);                              
                switch (c){
                    case 'A':
                        transPos = 0;
                        break;
                    
                    case 'B':
                        transPos = 1;
                        break;

                    case 'C':
                        transPos = 2;
                        break;  
                        
                    case 'D':
                        transPos = 3;
                        break;
                }               
                this.translator.translators(transPos);
                screen.end();
                break;

            case 2:
                c = dispenser.waitEvent(time);
                
                switch (c) {
                    case 'A':
                        this.day = 0;
                        screen.optionButtonPressed('A');
                        break;

                    case 'B':
                        this.day = 1;
                        screen.optionButtonPressed('B');
                        break;

                    case 'C':
                        this.day = 2;
                        screen.optionButtonPressed('C');
                        break;

                    case 'D':
                        this.day = 3;
                        screen.optionButtonPressed('D');
                        break;

                    case 'E':
                        this.day = 4;
                        screen.optionButtonPressed('E');
                        break;
                }
                
                if(c != 'F'){
                    theaterState = date.getState(day);                   
                    AreaSelectionScreen area = new AreaSelectionScreen(screen.getScreenMode(), this);
                    area.menu();
                }
                screen.end();
                break;

            case 3:
                int areaState = 0;
                
                c = dispenser.waitEvent(time);

                switch (c) {
                    case 'A':
                        areaState = 0;
                        screen.optionButtonPressed('A');
                        break;

                    case 'B':
                        areaState = 1;
                        screen.optionButtonPressed('B');
                        break;

                    case 'C':
                        areaState = 2;
                        screen.optionButtonPressed('C');
                        break;

                    case 'D':
                        areaState = 3;
                        screen.optionButtonPressed('D');
                        break;

                    case 'E':
                        areaState = 4;
                        screen.optionButtonPressed('E');
                        break;
                }
                
                if(c != 'F'){
                    theater.setAreaInfo(areaState);
                    theaterAreaState = theaterState.getArea(areaState);
                    this.seatSel = new SeatSelectionScreen(ScreenMode.theater, this);
                    seatSel.menu();
                }
                screen.end();
                break;
            case 4:
                this.choosenSeats = new ArrayList<String>();

                c = ' ';

                while ((c != 0 && maxSeats < 4) && (c != 'A' && c!= 'B')) {
                    c = dispenser.waitEvent(time);
                    byte col = (byte) (c & 0xFF);
                    byte row = (byte) ((c & 0xFF00) >> 8);

                    if(c != 1){
                        if (c != 'A' && c != 'B' && maxSeats != 4) {
                            if (theaterAreaState.getSeatState(row - 1, col - 1).equals(SeatState.free) && !choosenSeats.contains(row + ";" + col)) {                           
                                dispenser.setTitle(translator.translate("Asiento seleccionado"));
                                dispenser.markSeat(row, col, 1);
                                choosenSeats.add(row + ";" + col);                         
                                this.maxSeats++;
                            } else {
                                dispenser.setTitle(translator.translate("Asiento ocupado, seleccione otro"));
                            }
                        }
                    }
                }
                
                if (c != 'B') {
                    PaymentScreen ps = new PaymentScreen(ScreenMode.message, this);                   
                    screen.end();
                }
                break;
                
            case 5:           
                c = dispenser.waitEvent(time);
                
                while (c == '1' && c != 'B') {
                    dispenser.retainCreditCard(false);
                    if (bank.comunicationAvaiable()) {
                        int price = 0;

                        if (screen.checkFestivo(date.getDate(day).getDayOfWeek().toString()) == true) {      //Probablemente haya que hacerlo con Theater, porque TheaterState no lo tocamos              
                            price = Integer.parseInt(theater.getAreaPrice()) * Integer.parseInt(theater.getFestivos());
                        } else { 
                            price = Integer.parseInt(theater.getAreaPrice());
                        }
                        
                        bank.doOperation(dispenser.getCardNumber(), price);

                        ArrayList<String> text = new ArrayList<>();

                        for (int i = 0; i < choosenSeats.size(); i++) {
                            text.add("   " + screen.getTitle());
                            text.add("   ===================");
                            text.add("   Area: " +  theater.getAreaName());
                            text.add("   Día: " + date.getDay(day));
                            text.add("   Fila: " + choosenSeats.get(i).split(";")[0]);
                            text.add("   Asiento: " + choosenSeats.get(i).split(";")[1]);
                            text.add("   Precio " + price + "€");

                            dispenser.print(text);
                            
                            text.clear();
                            
                            theaterAreaState.setSeats(Integer.parseInt(choosenSeats.get(i).split(";")[0])-1,Integer.parseInt(choosenSeats.get(i).split(";")[1])-1);
                        }
                        
                        dispenser.retainCreditCard(false);
                        dispenser.setOption(1, null);
                        dispenser.setDescription("Gracias por su compra");                       
                        
                        c = 0;
                        
                    } else {
                        dispenser.setOption(1, null);
                        dispenser.setDescription(translator.translate("Lo sentimos, no se pudo realizar la operacion"));
                        dispenser.expelCreditCard(30);
                    }
                    
                    if (dispenser.expelCreditCard(30) == false) {
                        dispenser.retainCreditCard(true);
                        dispenser.setOption(1, null);
                        dispenser.setDescription(translator.translate("Se ha guardado la tarjeta al no ser extraida"));
                        dispenser.waitEvent(15);
                        c = 0;
                    }                                      
                }  
                this.choosenSeats.clear();
                this.seatSel.updateState(theaterState);               
                break;
        }
    }

    public void ScreenManager(TheaterTicketDispenser dispenser) throws IOException, CommunicationException, ClassNotFoundException {
        for (int i = 0; i < 6; i++) {
            dispenser.setOption(i, null);
        }
        
        if (options != 0) {
            dispenser.setOption(1, translator.translate(screen.getOptions(options).get(screen.getOptions(options).size()-1)));
        }

        switch (options) {
            case 0: {
                screen.begin();
                dispenser.setMenuMode();
                dispenser.setTitle(translator.translate("Bienvenido al teatro Alfil"));
                dispenser.setImage(screen.getImage());
                dispenser.setDescription(screen.getDescription());
                ArrayList<String> opc = screen.getOptions(options);
                for (int i = 0; i < opc.size(); i++) {
                    dispenser.setOption(i, translator.translate(opc.get(i)));
                }
                break;
            }
            case 1: {
                screen.begin();
                dispenser.setTitle(translator.translate("Selecciona el idioma"));
                ArrayList<String> opc = screen.getOptions(options);
                for (int i = 0; i < opc.size(); i++) {
                    dispenser.setOption(i, opc.get(i));
                }
                break;
            }
            case 2: {
                screen.begin();
                ArrayList<String> opc = screen.getOptions(options);
                dispenser.setTitle(translator.translate("Selecciona la fecha de representacion"));
                for (int i = 0; i < opc.size(); i++) {
                    dispenser.setOption(i, opc.get(i));
                }
                break;
            }
            case 3:
                screen.begin();
                dispenser.setTitle(translator.translate("Selecciona la zona"));
                for (int i = 0; i < theater.getNumAreas(); i++) {
                    String[] data = theater.getAreaInfo(i).split(";");
                    dispenser.setOption(i, data[0].split(":")[1] + " (" + data[1] + ")");
                }
                break;
            case 4:
                screen.begin();
                dispenser.setTheaterMode(theaterAreaState.getRows(), theaterAreaState.getCols());
                dispenser.setTitle(translator.translate("Selecciona tus asientos"));
                this.drawArea(theaterAreaState);
                dispenser.setOption(0, translator.translate("Aceptar"));
                dispenser.setOption(1, translator.translate("Cancelar"));
                break;
            case 5:
                screen.begin();
                dispenser.setMessageMode();
                dispenser.setTitle(translator.translate("Realizando pago"));
                dispenser.setDescription(translator.translate("Introduzca su tarjeta para continuar"));
                dispenser.setOption(0, null);
                dispenser.setOption(1, translator.translate(screen.getOptions(options).get(screen.getOptions(options).size()-1)));
                break;
            default:
                WelcomeScreen welcome = new WelcomeScreen(ScreenMode.options, this);
        }
    }

    private void drawArea(TheaterAreaState theaterAreaState) {
        theaterAreaState.loadSeats();

        for (int i = 0; i < theaterAreaState.getRows(); i++) {
            for (int j = 0; j < theaterAreaState.getCols(); j++) {
                switch (theaterAreaState.getSeatState(i, j)) {
                    case free:
                        dispenser.markSeat(i + 1, j + 1, 2);
                        break;
                    case empty:
                        dispenser.markSeat(i + 1, j + 1, 0);
                        break;
                    case occupied:
                        dispenser.markSeat(i + 1, j + 1, 1);
                        break;
                    default:
                        break;
                }
            }
        }
    }

}
