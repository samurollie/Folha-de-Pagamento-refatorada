package src.utilities;

import java.util.Date;

public class Timecard {
    //TODO adicionar hora que entrou e hora que saiu
    public Date entry = new Date();
    public Date exit = new Date();
    public int workedHours;
    
    public Timecard(Date entry, Date exit) {
        this.entry = entry;
        this.exit = exit;
        this.workedHours = exit.getHours() - entry.getHours();
    }
}
