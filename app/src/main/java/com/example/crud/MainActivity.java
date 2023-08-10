package com.example.crud;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
	EditText address,name, contact, dob;
	Button insert, update, delete, view;
	DBHelper DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name = findViewById(R.id.name);
		contact = findViewById(R.id.contact);
		address=findViewById(R.id.address);
		dob = findViewById(R.id.dob);
		insert = findViewById(R.id.btnInsert);
		update = findViewById(R.id.btnUpdate);
		delete = findViewById(R.id.btnDelete);
		view = findViewById(R.id.btnView);
		DB = new  com.example.crud.DBHelper(this);
		insert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String nameTXT = name.getText().toString();
				String addressTXT = address.getText().toString();
				String contactTXT = contact.getText().toString();
				String dobTXT = dob.getText().toString();

				Boolean checkinsertdata = DB.insertuserdata(nameTXT,addressTXT, contactTXT, dobTXT);
				if(checkinsertdata==true)
					Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
			}        });
		update.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String nameTXT = name.getText().toString();
				String addressTXT = address.getText().toString();
				String contactTXT = contact.getText().toString();
				String dobTXT = dob.getText().toString();

				Boolean checkupdatedata = DB.updateuserdata(nameTXT,addressTXT ,contactTXT, dobTXT);
				if(checkupdatedata==true)
					Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
			}        });
		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String nameTXT = name.getText().toString();
				Boolean checkudeletedata = DB.deletedata(nameTXT);
				if(checkudeletedata==true)
					Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
			}        });

		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Cursor res = DB.getdata();
				if(res.getCount()==0){
					Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
					return;
				}
				StringBuffer buffer = new StringBuffer();

				while (res.moveToNext()) {
					buffer.append("Name: " + res.getString(0) + "\n");
					buffer.append("Address: " + res.getString(1) + "\n"); // Use index 1 for address
					buffer.append("Contact: " + res.getString(2) + "\n");
					buffer.append("Date of Birth: " + res.getString(3) + "\n\n"); // Use index 3 for dob
				}


				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setCancelable(true);
				builder.setTitle("User Entries");
				builder.setMessage(buffer.toString());
				builder.show();
			}        });
	}}