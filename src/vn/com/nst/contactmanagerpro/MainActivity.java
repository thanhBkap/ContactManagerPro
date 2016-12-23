package vn.com.nst.contactmanagerpro;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import vn.com.nst.adapter.ContactAdapter;
import vn.com.nst.model.Contact;

public class MainActivity extends Activity {
	EditText txtNhapTen, txtNhapPhone;
	ListView lvDanhBa;
	Button btnLuu;
	ArrayList<Contact> arrContact;
	ContactAdapter contactAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addControls();
		addEvents();
	}

	private void addEvents() {
		btnLuu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				xuLiLuu();
			}

		});
	}

	private void addControls() {
		txtNhapPhone = (EditText) findViewById(R.id.txtNhapPhone);
		txtNhapTen = (EditText) findViewById(R.id.txtNhapTen);
		btnLuu = (Button) findViewById(R.id.btnLuu);
		lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
		arrContact = new ArrayList<Contact>();
		contactAdapter = new ContactAdapter(MainActivity.this, R.layout.item, arrContact);
		//arrContact.add(new Contact("Obama", "0986473643"));
		//arrContact.add(new Contact("D.Trump", "0978436583"));		
		lvDanhBa.setAdapter(contactAdapter);
	}

	private void xuLiLuu() {
		if((txtNhapPhone.getText().toString().compareTo(""))==0){
			if((txtNhapTen.getText().toString().compareTo(""))==0){
				Toast.makeText(MainActivity.this, "Hãy nhập thông tin", Toast.LENGTH_SHORT).show();;
			}else{
				Toast.makeText(MainActivity.this, "Hãy nhập số phone", Toast.LENGTH_SHORT).show();;
			}
		}else if((txtNhapTen.getText().toString().compareTo(""))==0){
			Toast.makeText(MainActivity.this, "Hãy nhập tên danh bạ", Toast.LENGTH_SHORT).show();;
		}else{
			arrContact.add(new Contact(txtNhapTen.getText().toString(),
					txtNhapPhone.getText().toString()));
			txtNhapPhone.setText("");
			txtNhapTen.setText("");
			contactAdapter.notifyDataSetChanged();
		}
	}
}
