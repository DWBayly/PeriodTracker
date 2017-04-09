package davidb.periodtracker;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main extends AppCompatActivity {
    public static Profile current;
    public static HashMap<String,Profile> profileList = new HashMap<String,Profile>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Scanner in = null;
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        TextView top= (TextView) findViewById(R.id.top);
        try{
            File f = new File("Profiles");
            if(f.exists()) {
                in = new Scanner(new File("Profiles"));
                String name = "";
                while (in.hasNext()) {
                    name = in.next();
                    Date d = sdf.parse(in.next());
                    int cycle = in.nextInt();
                    Profile P = new Profile(name, d, cycle);
                    String temp = in.next();
                    while (temp != "?") {
                        P.getCalender().put(sdf.parse(temp), in.next());
                    }
                    profileList.put(P.getName(), P);
                }
                current = profileList.get(name);
                top.setText("Current profile is "+name);
            }else{
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Profiles");
                file.createNewFile();
                top.setText(file.getAbsolutePath());
            }
        }catch(Exception e){
            top.setText(e.getLocalizedMessage());
        }


        try{
            if(current!=null) {
                top.setText("Current profile:" + current.getName());
            }
        }catch(Exception e){
            top.setText(e.getLocalizedMessage());
        }
        final Button b = (Button)findViewById(R.id.button);
        final Button b2 = (Button)findViewById(R.id.button2);
        final Button b3 = (Button)findViewById(R.id.button3);
        final Button b4 = (Button)findViewById(R.id.button4);
        if(current==null){
            b2.setVisibility(View.GONE);
            b3.setVisibility(View.GONE);
            b4.setVisibility(View.GONE);
        }
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),MakeProfile.class);
                startActivity(intent);


            }

        });


        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),ChangeProfile.class);
                startActivity(intent);

            }

        });


        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),Calender.class);
                startActivity(intent);
            }

        });


        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),MakeEntry.class);
                startActivity(intent);

            }

        });
    }
    protected void onDestroy(){
        super.onDestroy();
        try {
            PrintWriter w = new PrintWriter("Profiles", "UTF-8");
            Iterator e = profileList.entrySet().iterator();
            while (e.hasNext()){
                Map.Entry pair = (Map.Entry)e.next();
                Profile P = (Profile) pair.getValue();
                w.write(P.getName()+" "+P.getDate()+" "+ P.getCycle());
                Iterator i = P.getCalender().entrySet().iterator();
                while(i.hasNext()){
                    Map.Entry cp = (Map.Entry)e.next();
                    w.write(cp.getKey()+" "+cp.getValue());
                }
                w.write("?");

            }
            w.close();
        }catch (IOException e){

        }

    }

}
