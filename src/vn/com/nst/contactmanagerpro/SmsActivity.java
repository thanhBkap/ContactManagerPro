package vn.com.nst.contactmanagerpro;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import vn.com.nst.model.Contact;

public class SmsActivity extends Activity {
	ImageButton btnSend;
	TextView txtNguoiDen;
	EditText txtNoiDung;
	Intent intent;
	Contact contact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		addControls();
		addEvents();
	}

	private void addControls() {
		btnSend = (ImageButton) findViewById(R.id.btnSend);
		txtNguoiDen = (TextView) findViewById(R.id.txtTenNguoiDen);
		txtNoiDung = (EditText) findViewById(R.id.txtNoiDung);
		intent = getIntent();
		contact = (Contact) intent.getSerializableExtra("CONTACT");
		txtNguoiDen.setText(contact.getName().toString());
	}

	private void addEvents() {
		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				xuLySend();
			}
		});
	}

	private void xuLySend() {
		SmsManager sms = SmsManager.getDefault();
		Intent smsIntent = new Intent("ACTION_MSG_SENT");
		PendingIntent pendingIntent = PendingIntent.getBroadcast(SmsActivity.this, 0, smsIntent, 0);
		registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				int result = getResultCode();
				if(result==Activity.RESULT_OK){
					Toast.makeText(SmsActivity.this, "Gửi tin nhắn thành công", Toast.LENGTH_LONG).show();
					txtNoiDung.setText("");
					finish();
				}else{
					Toast.makeText(SmsActivity.this, "Gửi tin nhắn thất bại", Toast.LENGTH_LONG).show();
				}
			}
		}, new IntentFilter("ACTION_MSG_SENT"));
		sms.sendTextMessage(contact.getPhone().toString(),
				null, txtNoiDung.getText().toString(),
				pendingIntent, null);
	}

}
