package com.example.nt_kadai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calc (View view){
        //taking ip
        EditText[] ipAddress = new EditText[4];
        ipAddress[0] = findViewById(R.id.ip);
        ipAddress[1] = findViewById(R.id.ip1);
        ipAddress[2] = findViewById(R.id.ip2);
        ipAddress[3] = findViewById(R.id.ip3);

        int tmp;
        int tmpBin;
        String strIpAddress = "";
        for(int i=0; i<ipAddress.length;i++){

            tmp = Integer.parseInt(ipAddress[i].getText().toString());
            strIpAddress += Integer.toBinaryString(tmp);
        }


        //String strIpAddress = ipAddress.getText().toString();
        //double dIpAddress = Double.parseDouble(strIpAddress);

        //taking mask
        EditText[] ipMask = new EditText[4];
        ipMask[0] = findViewById(R.id.mask);
        ipMask[1] = findViewById(R.id.mask1);
        ipMask[2] = findViewById(R.id.mask2);
        ipMask[3] = findViewById(R.id.mask3);


        String strMask = "";
        for(int i=0; i<ipMask.length;i++){

            tmp = Integer.parseInt(ipMask[i].getText().toString());
            strMask += Integer.toBinaryString(tmp);
        }

        //String strIpMask = ipMask.getText().toString();
        //double dIpMask = Double.parseDouble(strIpMask);

        byte[] octet = strIpAddress.getBytes(StandardCharsets.UTF_8);

        String tmpByte = "";

        for(int i = 0; i<octet.length; i++){
            tmpByte += octet[i];
        }

        ipAddress ip = ipAddressString(strIpAddress);

        TextView rslt = findViewById(R.id.result);
        rslt.setText(strIpAddress);
        TextView rsltMask = findViewById(R.id.maskRslt);
        rsltMask.setText(strMask);
        TextView rsltByte = findViewById(R.id.byteArr);
        rsltByte.setText(tmpByte);


    }
}