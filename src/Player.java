import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int money;
    private String name;
    private String sinif;
    private int choice;
    private Location loc;
    private Scanner scn = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public String getSinif() {
        return sinif;
    }

    public void setSinif(String sinif) {
        this.sinif = sinif;
    }

    public void select() {
        Samurai smr = new Samurai();
        Archer arc = new Archer();
        Knight nht = new Knight();

        Template[] liste = {new Samurai(), new Archer(), new Knight()};

        for (Template x : liste) {
            System.out.println(x.getId() +
                    "\t" + x.getSinif() +
                    "\t Hasar: " + x.getDamage() +
                    "\t Sağlık: " + x.getHealth() +
                    "\t Para: " + x.getMoney());
        }

        System.out.println("*******************************");
        System.out.print("Sınıf tercihiniz: ");
        choice = scn.nextInt();
        switch (choice) {
            case 1:
                choose(new Samurai());
                break;
            case 2:
                choose(new Archer());
                break;
            case 3:
                choose(new Knight());
                break;
            default:
                choose(new Samurai());
                System.out.println("Geçersiz seçim! Samuray atandı.");
        }


    }

    public void choose(Template tempo) {
        this.setDamage(tempo.getDamage());
        this.setHealth(tempo.getHealth());
        this.setMoney(tempo.getMoney());
        this.setSinif(tempo.getSinif());
    }

    public boolean where() {
        System.out.println("Ziyaret etmek istediğiniz bölgeyi seçiniz: ");
        Location[] lo = {new SafeHouse(this), new ToolStore(this)};
        for (Location x : lo) {
            System.out.println(x.getId()+ "\t" + x.getName());
        }
        int zone = scn.nextInt();
        switch (zone) {
            case 1:choiceLoc(new SafeHouse(this));
            break;
            case 2: choiceLoc(new ToolStore(this));
        }
        return false;
    }

    public void choiceLoc(Location lol) {
        this.setLoc(lol);
        lol.onLocation();
    }
}


