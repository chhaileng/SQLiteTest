package info.chhaileng.sqlitetest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textContact = (TextView) findViewById(R.id.textContact);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);

        final DatabaseHandler db = new DatabaseHandler(this);

        List<User> aa = db.getAllUsers();
        textContact.setText("");

        for (User cn : aa) {
            textContact.append("No. : " + cn.getID()+"\n");
            textContact.append("ID: " + cn.getUid()+"\n");
            textContact.append("Name: " + cn.getName()+"\n");
            textContact.append("Email: " + cn.getEmail()+"\n");
            textContact.append("Pass: " + cn.getPassword()+"\n");
            textContact.append("-------------------------------------------------------\n");


            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getEmail();
            Log.d("Data from db >>>> ", log);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Insert: ", "Inserting ..");
                if (!etName.getText().toString().equals("") && !etEmail.getText().toString().equals("")) {
                    db.addUser(new User("2",etName.getText().toString(), etEmail.getText().toString(), "p@$SW0rD"));
                }
                else
                    Toast.makeText(MainActivity.this, "Reload", Toast.LENGTH_SHORT).show();

                List<User> users = db.getAllUsers();
                textContact.setText("");

                for (User cn : users) {
                    textContact.append("No. : " + cn.getID()+"\n");
                    textContact.append("ID: " + cn.getUid()+"\n");
                    textContact.append("Name: " + cn.getName()+"\n");
                    textContact.append("Email: " + cn.getEmail()+"\n");
                    textContact.append("Pass: " + cn.getPassword()+"\n");
                    textContact.append("-------------------------------------------------------\n");

                    String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getEmail();
                    Log.d("Data from db >>>> ", log);
                }

                etName.setText("");
                etEmail.setText("");

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users = db.getAllUsers();
                textContact.setText("");

                db.updateUser(new User(1,"9",etName.getText().toString(),etEmail.getText().toString(),"NewP@$sW0rD"));


                List<User> aa = db.getAllUsers();
                for (User cn : aa) {
                    textContact.append("No. : " + cn.getID()+"\n");
                    textContact.append("ID: " + cn.getUid()+"\n");
                    textContact.append("Name: " + cn.getName()+"\n");
                    textContact.append("Email: " + cn.getEmail()+"\n");
                    textContact.append("Pass: " + cn.getPassword()+"\n");
                    textContact.append("-------------------------------------------------------\n");
                }

                etName.setText("");
                etEmail.setText("");

            }
        });

    }

}
