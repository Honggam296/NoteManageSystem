package n17dtha4.notemanagesystem.model.password;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import n17dtha4.notemanagesystem.DBHelper;

public class changepass_db extends DBHelper {
    public changepass_db(Context context) {
        super(context);
    }

    public boolean  updatePass(changepassOJ changePassOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Password",changePassOJ.getPassword());
        long update = db.update(TABLE_ACCOUNT, contentValues, " ID = " + changePassOJ.getId(), null);
        if(update == -1 )
            return false;
        else
            return true;
    }
}
