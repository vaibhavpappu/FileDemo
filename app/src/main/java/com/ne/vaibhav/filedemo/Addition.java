package com.ne.vaibhav.filedemo;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Addition extends AppCompatActivity
{

    TextView textView;
EditText fno,sno,tno;
    static final int READ_BLOCK_SIZE = 100;
Button cal,sum;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        String path = Environment.getDataDirectory().getAbsolutePath().toString() + "/storage/emulated/0/appFolder";
        File mFolder = new File(path);
        if (!mFolder.exists())
        {
            mFolder.mkdir();
        }
            fno=findViewById(R.id.fno);
        sno=findViewById(R.id.sno);
        tno=findViewById(R.id.tno);
        cal=findViewById(R.id.calculate);
        sum=findViewById(R.id.sum);
        textView=findViewById(R.id.numbers);
    cal.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            String root= Environment.getExternalStorageDirectory().toString();
            String fn=" "+fno.getText().toString();
            String sn=" "+sno.getText().toString();
            String tn=" "+tno.getText().toString();

            try
            {

    OutputStreamWriter out;

                 File path=new File(getFilesDir(),"myfolder");
                 File mypath=new File(path,"myfile.txt");
                if(!mypath.exists())
                {
                  out=new OutputStreamWriter(openFileOutput(mypath.getAbsolutePath(),MODE_PRIVATE));
                 out.write(fn);
                out.append(sn);
                out.append(tn);

                }

//                outputStreamWriter.append("\n Answer:"+" "+ans);
                Toast.makeText(getApplicationContext(),"Values Inserted",Toast.LENGTH_LONG).show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    });
    sum.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {

            try
            {
                FileInputStream fileInputStream=openFileInput("addition.txt");
                InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
                char[] inputBuffer=new char[READ_BLOCK_SIZE];
                String s="";
                int charRead;
                while((charRead=inputStreamReader.read(inputBuffer))>100)
                {
                    String readString=String.copyValueOf(inputBuffer,0,charRead);
                    s+=charRead;
                    textView.setText(s);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    });
    }
}
