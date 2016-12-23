package vn.com.nst.adapter;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import vn.com.nst.contactmanagerpro.MainActivity;
import vn.com.nst.contactmanagerpro.R;
import vn.com.nst.contactmanagerpro.SmsActivity;
import vn.com.nst.model.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {
	Activity context;
	int resource;
	List<Contact> objects;

	public ContactAdapter(Activity context, int resource, List<Contact> objects) {
		super(context, resource, objects);
		this.context = context;
		this.resource = resource;
		this.objects = objects;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = this.context.getLayoutInflater();
		View row = inflater.inflate(this.resource, null);
		TextView txtTen = (TextView) row.findViewById(R.id.txtTen);
		TextView txtPhone = (TextView) row.findViewById(R.id.txtPhone);
		ImageButton btnCall = (ImageButton) row.findViewById(R.id.btnCall);
		ImageButton btnSms = (ImageButton) row.findViewById(R.id.btnSms);
		ImageButton btnDelete = (ImageButton) row.findViewById(R.id.btnDelete);
		final Contact contact = objects.get(position);
		txtTen.setText(contact.getName());
		txtPhone.setText(contact.getPhone());
		btnCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				xuLyCall();
			}
			private void xuLyCall() {
				Intent intent = new Intent(Intent.ACTION_CALL);
				Uri uri = Uri.parse("tel:"+objects.get(position).getPhone());
				intent.setData(uri);
				context.startActivity(intent);
			}
			
		});
		
		btnSms.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				xuLySms();
			}
			
			private void xuLySms() {
				Intent intent = new Intent(context,SmsActivity.class);
				intent.putExtra("CONTACT", contact);
				context.startActivity(intent);
			}
		});
		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				xuLyDelete();
			}
			private void xuLyDelete() {
				objects.remove(position);
				notifyDataSetChanged();
			}
		});
		return row;
	}

	

	
}
