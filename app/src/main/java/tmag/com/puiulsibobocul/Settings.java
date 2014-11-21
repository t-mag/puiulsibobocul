package tmag.com.puiulsibobocul;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import tmag.com.puiulsibobocul.R;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }


    public void btnSound_onClick(View v)
    {
        Button btnSound = (Button)v;//findViewById(R.id.btnSound);
        if(btnSound.getTag().toString()=="ON") {
            btnSound.setTag("OFF");
            btnSound.setBackgroundResource(R.drawable.speaker_not);

        }
        else {
            btnSound.setTag("ON");
            btnSound.setBackgroundResource(R.drawable.speaker);
        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
