package davidb.periodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by David on 4/5/2017.
 */

public class Calender extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender);
        Button b = (Button)findViewById(R.id.button7);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),Main.class);
                startActivity(intent);
            }

        });
        final TextView eventsTxt = (TextView)findViewById(R.id.eventsTxt);
        final CalendarView cdnr = (CalendarView)findViewById(R.id.calendarView);
        //System.out.print(new java.util.Date());
        String selectedDate = "30/09/2017";
        try {
            cdnr.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(selectedDate).getTime(), true, true);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //cdnr.
        cdnr.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                    //eventsTxt.setText(i+" "+i1+" "+i2);

                    eventsTxt.setText(Main.current.CheckDate(new Date(i-1900,i1,i2)));
            }
        });
    }
}
