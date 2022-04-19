package com.alfaisal.lab8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myData;
    String id_val,name_val;
    int num_val;
    EditText id,name,number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myData = new DatabaseHelper(this);
        id=(EditText) findViewById(R.id.textView);
        name=(EditText) findViewById(R.id.textView2);
        number=(EditText) findViewById(R.id.textView3);
        Button add = (Button) findViewById(R.id.button);
        Button view = (Button) findViewById(R.id.button2);
        Button delete = (Button) findViewById(R.id.button3);


        //add button listener
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_val=id.getText().toString();
                name_val=name.getText().toString();
                num_val=Integer.parseInt(number.getText().toString());
                myData.AddEmployee(id_val,name_val,num_val);
                Toast.makeText(MainActivity.this, "Successful Add", Toast.LENGTH_SHORT).show();
            }
        });




        //view button listener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cur = myData.ViewEmployee();
                StringBuffer buffer = new StringBuffer();
                while (cur.moveToNext()){
                    buffer.append("id:"+cur.getString(0)+"\n");
                    buffer.append("name:"+cur.getString(1)+"\n");
                    buffer.append("salary:"+cur.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("All Employee");
                builder.setMessage(buffer.toString());
                builder.show();
                Toast.makeText(MainActivity.this,"Successful View",Toast.LENGTH_LONG).show();
            }
        });



        //delete button lister
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myData.DeleteEmployee(id_val);
                Toast.makeText(MainActivity.this,"Successful Delete",Toast.LENGTH_LONG).show();
            }
        });
    }
}
