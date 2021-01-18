package n17dtha4.notemanagesystem;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;

public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void DialogStatus(){
        Dialog digStatus = new Dialog(this);
        digStatus.setContentView(R.layout.custom_dialog);
        digStatus.show();
    }
}