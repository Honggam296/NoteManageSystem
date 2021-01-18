package n17dtha4.notemanagesystem.model.profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import n17dtha4.notemanagesystem.DBHelper;

public class profile_db extends DBHelper {
    public profile_db(Context context) {
        super(context);
    }

    public boolean  updateProfile(profileOJ profileOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstName",profileOJ.getFirstName());
        contentValues.put("LastName",profileOJ.getLastName());
        contentValues.put("Email",profileOJ.getEmail());
        long update = db.update(TABLE_ACCOUNT, contentValues, " ID = " + profileOJ.getId(), null);
        if(update == -1 )
            return false;
        else
            return true;
    }


}
