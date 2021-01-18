package n17dtha4.notemanagesystem;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText fName= (EditText)findViewById(R.id.etUserFirstName);
        EditText lName= (EditText)findViewById(R.id.etUserLastName);
        EditText email= (EditText)findViewById(R.id.etEmail);
        EditText pass= (EditText)findViewById(R.id.etPassword);
        EditText confirm_Pass= (EditText)findViewById(R.id.etConfirmPass);
        AppCompatButton btnAddAcc = (AppCompatButton) findViewById(R.id.btnAddAcc);
        TextView tv= findViewById(R.id.textView3);
        btnAddAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(fName.getText().toString().length()>0&&lName.getText().toString().length()>0&&email.getText().toString().length()>0&&pass.getText().toString().length()>0){
                        if(isValidEmail(email.getText().toString())){
                            if(pass.getText().toString().equals(confirm_Pass.getText().toString())){
                                new SignUp_DB(getApplicationContext()).addAcc(fName.getText().toString(),lName.getText().toString(),email.getText().toString(),pass.getText().toString());
                                Toast.makeText(getApplicationContext(),R.string.Create_Success,Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(SignUp.this,Login.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),R.string.CheckYourPass,Toast.LENGTH_LONG).show();
                            }}else {
                            Toast.makeText(getApplicationContext(),"Incorrect Email",Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(getApplicationContext(),R.string.insert_full,Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"UnSuccess",Toast.LENGTH_LONG).show();
                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    class SignUp_DB extends DBHelper{
        public SignUp_DB(Context context) {
            super(context);
        }




        public boolean addAcc(String fName,String lName,String email,String passWord){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("FirstName",fName);
            contentValues.put("LastName",lName);
            contentValues.put("Email",email);
            contentValues.put("Password",passWord);
            long inserted = db.insert(TABLE_ACCOUNT,null,contentValues);
            if(inserted == -1 )
                return false;
            else
                return true;
        }
    }
}
class SignUp_DB extends DBHelper{
    public SignUp_DB(Context context) {
        super(context);
    }




    public boolean addAcc(String fName,String lName,String email,String passWord){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstName",fName);
        contentValues.put("LastName",lName);
        contentValues.put("Email",email);
        contentValues.put("Password",passWord);
        long inserted = db.insert(TABLE_ACCOUNT,null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }
}