/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package theaterticketdispenser;

/**
 *
 * @author d.rodriguezgal.2020
 */
final class TranslatorManager {
    private Translator translator;
    private String activeIdiom;

    public TranslatorManager() {
        this.translator = translator;
        this.activeIdiom = activeIdiom;
        this.translators(0);
    }
     
    public void translators(int pos){
        switch(pos){
            case 0:
                this.setActiveIdiom("esp");
                translator = new Translator("./files/ConfigFilesExample/translation/esp.txt");
                break;
            case 1:
                this.setActiveIdiom("eng");
                translator = new Translator("./files/ConfigFilesExample/translation/eng.txt");
                break;
            case 2:
                this.setActiveIdiom("cat");
                translator = new Translator("./files/ConfigFilesExample/translation/cat.txt");
                break;
            case 3:
                this.setActiveIdiom("usk");
                translator = new Translator("./files/ConfigFilesExample/translation/usk.txt");
                break;
            default:
                this.setActiveIdiom("esp");
                translator = new Translator("./files/ConfigFilesExample/translation/esp.txt");
                break;
        } 
    }
    
    public String translate(String msg){
        return translator.translate(msg);
    }   
    
    public String getActiveIdiom() {
        return activeIdiom;
    }

    public void setActiveIdiom(String activeIdiom) {
        this.activeIdiom = activeIdiom;
    }    
}
