package com.ne.vaibhav.filedemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
{
    String TAG = "Permsission : ";
   TextView textView;
EditText user_name,address,email,mo_no;
Button saveBtn,retriveBtn,nextbtn;
String n;
    static final int READ_BLOCK_SIZE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        email=findViewById(R.id.email);
        mo_no=findViewById(R.id.m_no);
        saveBtn=findViewById(R.id.save);
        textView=findViewById(R.id.textview);
        retriveBtn=findViewById(R.id.retrive);
        nextbtn=findViewById(R.id.next);
        saveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                save();

                }

        });

        retriveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {

                    FileInputStream fileIn=openFileInput("source.txt");
                    InputStreamReader inputStreamReader=new InputStreamReader(fileIn);
                    char[] inputBuffer= new char[READ_BLOCK_SIZE];
                String s="";
                int charRead;
                while ((charRead=inputStreamReader.read(inputBuffer))>0)
                {
                    String readString=String.copyValueOf(inputBuffer,0,charRead);
                    s+=readString;
                    textView.setText(s);
                }
                    inputStreamReader.close();

                }

                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        nextbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(MainActivity.this,Addition.class);
                startActivity(intent);
            }
        });
        }

    private boolean save() {
        n="Name:"+" "+user_name.getText().toString();
        String addr="Address:"+" "+address.getText().toString();
        String emailId="Email:"+" "+email.getText().toString();
        String mno="Moblile:"+" "+mo_no.getText().toString();

        String TAG = "Permsission : ";
        if (Build.VERSION.SDK_INT >= 23) {
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            File root_text = Environment.getExternalStorageDirectory();
            try{ File folder = new File(Environment.getExternalStorageDirectory() + "/TESTEXAMPLE");
                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdir();
                }
                BufferedWriter fwv = new BufferedWriter(new FileWriter(new File("/sdcard/TESTEXAMPLE/SavedData.txt"), false));
                if (root_text.canWrite()) {
                    fwv.write("\n Name:"+n);
                    fwv.write("\n Address:"+addr);
                    fwv.write("\n Email"+emailId);
                    fwv.write("\n Mobile no:"+mno);
                    fwv.close();
                }
            }catch (Exception e){
                Log.e("MODEL", "ERROR: " + e.toString());
            }
        }

            try
            {
                /*
                FileOutputStream fileout=openFileOutput("source.txt",Context.MODE_WORLD_READABLE);
                OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileout);
                outputStreamWriter.write("\n"+n.getBytes());
                outputStreamWriter.append("\n"+addr.getBytes());
                outputStreamWriter.append("\n"+emailId.getBytes());
                outputStreamWriter.append("\n"+mno +"\n"+mno.getBytes());
                Toast.makeText(MainActivity.this, "File Saved to "+getFilesDir()+"/"+"source.txt", Toast.LENGTH_SHORT).show();
                outputStreamWriter.close();*/
            }
            catch (Exception e)
            {
                e.printStackTrace();

            }
            Toast.makeText(MainActivity.this, "Grant", Toast.LENGTH_SHORT).show();
            return true;
            }

        else {

            Toast.makeText(MainActivity.this, "Revoke", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return false;
        }

}
}

