package com.example.cedcoss.mythirdapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

@Deprecated
public class MainActivity extends Activity {
    String msg;
    int data_block=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save=(Button)findViewById(R.id.save);
        // Ok fine do it
        Button load=(Button)findViewById(R.id.load);
        final EditText message=(EditText)findViewById(R.id.message);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              msg=  message.getText().toString();
                try {
                    FileOutputStream fou=openFileOutput("text.txt",MODE_WORLD_WRITEABLE);
                    OutputStreamWriter osw= new OutputStreamWriter(fou);
                    try {
                        osw.write(msg);
                        osw.flush();
                        osw.close();
                        Toast.makeText(getBaseContext(),"data saved",Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis=openFileInput("text.txt");
                    InputStreamReader isr=new InputStreamReader(fis);
                    char[] data =new char[data_block];
                    String final_data="";
                    int size;
                    try {
                        while((size=isr.read(data))>0){
                            String read_data=String.copyValueOf(data,0,size);
                            final_data+=read_data;
                            data=new char[data_block];
                        }
                        Toast.makeText(getBaseContext(),"messgae"+final_data,Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void callme(View view) {
        EditText editText = (EditText) findViewById(R.id.message);
        String numbe=(editText.getText().toString());
        Uri number = Uri.parse("tel:" + numbe);

        Intent callIntent = new Intent(Intent.ACTION_CALL, number);
        callIntent.setData(number);
        startActivity(callIntent);
    }
}
