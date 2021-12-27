import java.util.Scanner;

public abstract class Location {
    protected Player player;
    private String name;
    private  int id;
    private String info;
    protected Scanner scn = new Scanner(System.in);

    public Location(String name,int id,Player player,String info) {
        this.name = name;
        this.id = id;
        this.player=player;
        this.info=info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract boolean onLocation();

    }
