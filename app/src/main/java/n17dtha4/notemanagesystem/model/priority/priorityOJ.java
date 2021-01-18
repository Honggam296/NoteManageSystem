package n17dtha4.notemanagesystem.model.priority;


import androidx.annotation.NonNull;

public class priorityOJ {
    private int Id;
    private String name;
    private String createDate;

    public priorityOJ(int id, String name, String createDate) {
        Id = id;
        this.name = name;
        this.createDate = createDate;
    }

    public priorityOJ() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PriorityOJ{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
