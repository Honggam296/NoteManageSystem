package n17dtha4.notemanagesystem.model.category;

import androidx.annotation.NonNull;

public class categoryOJ {
    String name;
    String Createdate;
    int id ;


    public  categoryOJ(int id , String name, String Createdate){
        this.name = name;
        this.Createdate = Createdate;
        this.id=id;
    }
    public categoryOJ(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedate() {
        return Createdate;
    }

    public void setCreatedate(String createdate) {
        Createdate = createdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return (getName());
    }
}
