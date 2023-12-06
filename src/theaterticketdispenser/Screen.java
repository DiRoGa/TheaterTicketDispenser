/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author d.rodriguezgal.2020
 */
class Screen {
    private final Play play = new Play("./files/ConfigFilesExample/play.txt");
    private DispenserManager manager;
    private final ScreenMode mode;
    private String title;
    private String description;
    private String image;
    private ArrayList<String> dates = new ArrayList<>();
    private ArrayList<String> options = new ArrayList<>();

    public Screen(ScreenMode mode, DispenserManager manager) {
        this.mode = mode;
        this.manager = manager;
        this.title = title;
        this.description = description;
        this.image = image;
        this.options = options;
        this.dates = dates;
    }

    public ArrayList<String> getOptions(int pos) {  
        this.options.clear();
        
        switch (pos) {
            case 0:
                options.add("Selecciona el idioma");
                options.add("Selecciona la fecha de representacion");
                break;

            case 1:
                options.add("Español");
                options.add("Inglés");
                options.add("Catalán");
                options.add("Euskera");
                break;

            case 2:
                int j = 0;
                LocalDate newDay = LocalDate.now();  
        
                for (int i = 0; i < 4; i++) {
                    LocalDate nextDay = newDay.plusDays(j);

                    if (nextDay.getDayOfWeek() == DayOfWeek.MONDAY) {
                        j++;
                        LocalDate overDay = newDay.plusDays(j);
                        options.add(overDay.toString());
                    } else {
                        options.add(nextDay.toString());
                    }
                    j++;
                }       
                break; 
            }
               
        options.add("Cancelar");
        return this.options;
    }
    
    public boolean checkFestivo(String day) {
        boolean isFestivo = false;

        if(day.equals("FRIDAY") || day.equals("SATURDAY") || day.equals("SUNDAY")){
            isFestivo = true;
        }    
        
        return isFestivo;
    }

    public String getTitle() {
        this.title = play.getTitle();
        return title;
    }

    public String getDescription() {
        this.description = play.getDescription();
        return description;
    }

    public String getImage() {
        this.image = play.getImage();
        return image;
    }

    public ScreenMode getScreenMode() {
        return this.mode;
    }

    public ScreenResult optionButtonPressed(char c) {
        return (c != 0 ? ScreenResult.exitScreen : ScreenResult.continueInScreen);
    }

    public DispenserManager getManager() {
        return manager;
    }

    public void setDispenserManager(DispenserManager manager) {
        this.manager = manager;
    }

    public ScreenResult begin() {
        return ScreenResult.continueInScreen;
    }

    public ScreenResult end() {
        return ScreenResult.exitScreen;
    }
}
