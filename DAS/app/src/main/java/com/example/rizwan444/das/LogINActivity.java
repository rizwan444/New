package com.example.rizwan444.das;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogINActivity extends AppCompatActivity {

    DataBase dataBase;
    EditText Userid;
    EditText Password;

    TextView V;
    String userid,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        dataBase = new DataBase(this);



        V=(TextView) findViewById(R.id.txtV_Msg);
        Userid=(EditText) findViewById(R.id.editTxt_UserID);
        Password=(EditText) findViewById(R.id.editTxt_Password);

        userid= Userid.getText().toString();
        password= Password.getText().toString();



        Button btn= (Button) findViewById(R.id.btn_LogIn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor;
                cursor=dataBase.list_all(userid,password);
                if(cursor.getCount()!=0){
                    Toast.makeText(LogINActivity.this,"Welcome", Toast.LENGTH_LONG).show();
                }


                /*
                    StringBuffer buffer= new StringBuffer();
                    while(cursor.moveToNext()){
                    buffer.append("ID :"+cursor.getString(0)+"\n");
                    buffer.append("ID :"+cursor.getString(1)+"\n");
                    buffer.append("ID :"+cursor.getString(2)+"\n");
                    buffer.append("ID :"+cursor.getString(3)+"\n");
                    buffer.append("ID :"+cursor.getString(4)+"\n");
                    buffer.append("ID :"+cursor.getString(5)+"\n\n");
                }
                V.setText(buffer);*/

            }
        });

    }
}
