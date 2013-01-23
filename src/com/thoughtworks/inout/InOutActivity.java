package com.thoughtworks.inout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.thoughtworks.inout.db.SQLLiteTimeCardDAO;

public class InOutActivity extends Activity implements Confirmable{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out);
        addListeners();
    }

    private void addListeners() {
    	Button buttonIn = (Button) findViewById(R.id.button_in);
    	Button buttonOut = (Button) findViewById(R.id.button_out);
    	
    	buttonIn.setOnClickListener(new RegisterTimeListener(this, this));
    	buttonOut.setOnClickListener(new RegisterTimeListener(this, this));
    	buttonOut.setBackgroundColor(Color.CYAN);
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_in_out, menu);
        return true;
    }
    
	public void onConfirm(View view) {
		TimeCard timeCard = new TimeCard(new SQLLiteTimeCardDAO(this));
    	PunchType punchType = view.getId() == R.id.button_in ? PunchType.IN : PunchType.OUT;
    	timeCard.punch(punchType);
	}
    
    
}
