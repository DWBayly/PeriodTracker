package davidb.periodtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChangeProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_profile);
        Button b = (Button)findViewById(R.id.button6);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),Main.class);
                startActivity(intent);
            }

        });
        final TextView cp = (TextView)findViewById(R.id.textView2);
        if(Main.current!=null){
            cp.setText(Main.current.getName());
        }
        final Spinner selectProfile = (Spinner)findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        Iterator it = Main.profileList.entrySet().iterator();
        while(it.hasNext()){
            HashMap.Entry p = (HashMap.Entry) it.next();
            list.add(p.getKey().toString());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectProfile.setAdapter(dataAdapter);
        Button sp = (Button)findViewById(R.id.selectProfileB);
        sp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    if (selectProfile.getSelectedItem() != null) {
                        Main.current = Main.profileList.get(selectProfile.getSelectedItem());
                        Intent intent = new Intent(v.getContext(), Main.class);
                        startActivity(intent);
                    }
                }catch (Exception e){
                    cp.setText(e.getLocalizedMessage());
                }
            }

        });

    }
}
