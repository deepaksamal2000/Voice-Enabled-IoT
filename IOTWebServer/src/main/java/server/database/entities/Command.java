package server.database.entities;

public class Command {
    private Integer id;
    private String name;

    public Command(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
