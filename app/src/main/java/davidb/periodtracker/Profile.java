package davidb.periodtracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by David on 4/5/2017.
 */

public class Profile {
    private String name;

    public HashMap<Date, String> getCalender() {
        return calender;
    }

    public void setCalender(HashMap<Date, String> calender) {
        calender = calender;
    }

    private HashMap<Date,String> calender;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    private int cycle;

    public Profile(String name,Date date, int cycle) {
        this.name=name;
        this.date=date;
        this.cycle=cycle;
        calender =new HashMap<Date,String>();
        calender.put(date,"First Cycle");
    }
    public String CheckDate(Date d){
        try{
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            String message = sdf.format(d);
            if( calender.containsKey(d)){
                return calender.get(d);
            }else{
                long diff = TimeUnit.DAYS.convert(d.getTime()-date.getTime(),TimeUnit.MILLISECONDS);
                if(diff%cycle==0){
                    return message+"\nPeriod start here ";
                }else if(diff%cycle<7){
                    return message + "\nPeriod expected ";
                }else {
                    return message+ "\nClear day expected ";
                }

            }

        }catch(Exception e){
            return e.getLocalizedMessage();

        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
