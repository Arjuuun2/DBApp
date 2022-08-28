
package com.example.dbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3;
    CDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText) findViewById(R.id.e1);
        e2=(EditText) findViewById(R.id.e2);
        e3=(EditText) findViewById(R.id.e3);
        db=new CDB(this);
    }

    public void OnSave(View v){

        String dn,dl;
        dn=e2.getText().toString();
        dl=e3.getText().toString();
        Toast.makeText(this, "dn+dl", Toast.LENGTH_SHORT).show();
        db.addDept(dn, dl);
        e2.setText("");
        e3.setText("");
    }
    public void OnFind(View v) {
        String a[];
        try {
            String dno = e1.getText().toString();
            a = db.getOneDepartment(Integer.parseInt(dno));
            if (a[0] != "") {
                e2.setText(a[0]);
                e3.setText(a[1]);

            } else {
                Toast.makeText(this, "Not Found>>", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Log.d("SELECT123", e.toString());
        }
    }
        //View v1 = v;
        public void onDelete(View v){
            String dno=e1.getText().toString();
            db.deleteDept(Integer.parseInt(dno));
            e2.setText("");
            e3.setText("");
        }
        public void onUpdate(View v){

            String dno=e1.getText().toString();
            String dn,dl;
            dn=e2.getText().toString();
            dl=e3.getText().toString();
            db.update(Integer.parseInt(dno),dn,dl);
            e2.setText("");
            e3.setText("");
        }

        public void onList(View v){
            List<CDept> rec=db.getAllvalues();
            String str="";
            for(CDept cr : rec){
                String log = "DId: " + cr.id+ " ,DNAME: " + cr.dname + " ,DLOC:" +cr.dloc;
                log=log+ "\n";
                str=str +log;
            }
            TextView t=(TextView) findViewById(R.id.tv);
            t.setText(str);

        }


}