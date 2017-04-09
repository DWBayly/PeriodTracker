package davidb.periodtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_entry);
        Button b = (Button)findViewById(R.id.button8);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),Main.class);
                startActivity(intent);
            }

        });
        final EditText notes = (EditText)findViewById(R.id.notes);
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    notes.setText("");
                }
        });
        final EditText mkeDate= (EditText)findViewById(R.id.DateEntry);
        mkeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mkeDate.setText("");
            }
        });
        final Spinner spinner = (Spinner)findViewById(R.id.CycleType);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.Entry_types,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button mke = (Button)findViewById(R.id.MKE);
        mke.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                    Date date = sdf.parse(mkeDate.getText().toString());
                    Main.current.getCalender().put(date,spinner.getSelectedItem()+"\n"+notes.getText().toString());
                }catch(Exception e){

                }
                Intent intent = new Intent(v.getContext(),Main.class);
                startActivity(intent);
            }

        });

    }
}
