package api.payload;

public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    String name;
    String job;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
}
