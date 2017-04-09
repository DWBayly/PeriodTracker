package davidb.periodtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static android.R.interpolator.cycle;

/**
 * Created by David on 4/5/2017.
 */

public class MakeProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);
        Button b = (Button)findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),Main.class);
                startActivity(intent);
            }

        });
        final EditText nameEntry = (EditText)findViewById(R.id.editText2);
        nameEntry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                nameEntry.setText("");
            }

        });
        final EditText dateEntry = (EditText)findViewById(R.id.editText4);
        dateEntry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dateEntry.setText("");
            }

        });
        final EditText cycleLength = (EditText)findViewById(R.id.editText5);
        cycleLength.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cycleLength.setText("");
            }

        });

        final Button createProfile=(Button)findViewById(R.id.CPB);
        createProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String name = nameEntry.getText().toString();
                int cycle=0;
                try {
                    cycle = Integer.parseInt(cycleLength.getText().toString());
                }catch(Exception e){
                    createProfile.setText("Invalid Cycle Length "+ e.getLocalizedMessage());
                    return;
                }
                 try {
                    SimpleDateFormat sdf= new SimpleDateFormat("dd/M/yyyy");
                    Date date= sdf.parse(dateEntry.getText().toString());
                    Main.current= new Profile(name,date,cycle);
                     Main.profileList.put(name,Main.current);


                    Intent intent = new Intent(v.getContext(), Main.class);
                    startActivity(intent);

                }catch(Exception e){
                    createProfile.setText("Invalid Date : "+dateEntry.getText().toString());
                }
            }

        });

    }
}
