package com.example.matej.constructionlog;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editPrezime,editTip_projekta,editCijena_materijala,editCijena_posla,editText_ID;
    Button btnAdd,btnView,btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        myDb = new DatabaseHelper(this);

        editPrezime =(EditText)findViewById(R.id.editText_Prezime);
        editTip_projekta=(EditText)findViewById(R.id.editText_Tip_projekta);
        editCijena_materijala=(EditText)findViewById(R.id.editText_Cijena_materijala);
        editCijena_posla=(EditText)findViewById(R.id.editText_Cijena_posla);
        editText_ID=(EditText)findViewById(R.id.editText_ID);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnView=(Button)findViewById(R.id.btnView);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        AddData();
        ViewAll();
        UpdateData();
        DeleteData();

    }

    public void AddData(){
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editPrezime.getText().toString(),
                                editTip_projekta.getText().toString(),
                                editCijena_materijala.getText().toString(),
                                editCijena_posla.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(DatabaseActivity.this, "Podatci spremljeni", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DatabaseActivity.this, "Podatci nisu spremljeni", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateData(editText_ID.getText().toString(),
                                editPrezime.getText().toString(),
                                editTip_projekta.getText().toString(),
                                editCijena_materijala.getText().toString(),
                                editCijena_posla.getText().toString());
                        if(isUpdated == true)
                            Toast.makeText(DatabaseActivity.this, "Podatci ažurirani", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DatabaseActivity.this, "Podatci nisu ažurirani", Toast.LENGTH_LONG).show();
                        }
                }
        );
    }

    public void ViewAll(){
        btnView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Podatci","Nema evidentiranih podataka");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("ID: "+res.getString(0)+"\n");
                            buffer.append("PREZIME: "+res.getString(1)+"\n");
                            buffer.append("TIP PROJEKTA: "+res.getString(2)+"\n");
                            buffer.append("CIJENA MATERIJALA: "+res.getString(3)+"\n");
                            buffer.append("CIJENA POSLA: "+res.getString(4)+"\n\n");
                        }
                        showMessage("Projekti",buffer.toString());
                    }
                }
        );
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows=myDb.deleteData(editText_ID.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(DatabaseActivity.this, "Podatci obrisani", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DatabaseActivity.this, "Podatci nisu obrisani", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
