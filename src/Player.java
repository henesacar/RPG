import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int money;
    private int block;
    private int mhealth;
    private int victory;
    private String name;
    private String sinif;
    private String weapon;
    private String armor;
    private int choice;
    private Location loc;
    private boolean firewood;
    private boolean food;
    private boolean water;
    private Scanner scn = new Scanner(System.in);

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public int getMhealth() {
        return mhealth;
    }

    public void setMhealth(int mhealth) {
        this.mhealth = mhealth;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

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
        if (health < 0) {
            health = 0;
        }
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        if (money < 0) {
            money = 0;
        }
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
        ;
    }

    public String getSinif() {
        return sinif;
    }

    public void setSinif(String sinif) {
        this.sinif = sinif;
    }

    public void select() {
        Template[] liste = {new Samurai(), new Archer(), new Knight()};

        System.out.println("#############################################");
        for (Template x : liste) {
            System.out.println(x.getId() +
                    "\t" + x.getSinif() +
                    "\t Hasar: " + x.getDamage() +
                    "\t Sağlık: " + x.getHealth() +
                    "\t Para: " + x.getMoney());
            System.out.println("#############################################");
        }

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

    public int getDefaultDamage() {
        if (this.getSinif().equalsIgnoreCase("Okçu")) {
            return 7;
        } else if (this.getSinif().equalsIgnoreCase("Samuray")) {
            return 5;
        } else if (this.getSinif().equalsIgnoreCase("Şövalye")) {
            return 8;
        }
        return 0;
    }

    public void choose(Template tempo) {
        this.setDamage(tempo.getDamage());
        this.setHealth(tempo.getHealth());
        this.setMoney(tempo.getMoney());
        this.setSinif(tempo.getSinif());
        this.setBlock(tempo.getBlock());

    }

    public void where() {
        System.out.println("Ziyaret etmek istediğiniz bölgeyi seçiniz: ");
        Location[] lo = {new SafeHouse(this), new ToolStore(this), new Forest(this), new Cave(this), new River(this),new Mine(this)};
        for (Location x : lo) {
            System.out.println(x.getId() + " --->\t" + x.getName() + "\t---\t" + x.getInfo());
        }
        System.out.println("9 --->\tDurum kontrolü");
        System.out.println("0 --->\tÇıkış");
        int zone = scn.nextInt();
        switch (zone) {
            case 1:
                this.setLoc(new SafeHouse(this));
                this.loc.onLocation();
                break;
            case 2:
                this.setLoc(new ToolStore(this));
                this.loc.onLocation();
                break;

            case 3:
                if (!isFirewood()) {
                    this.setLoc(new Forest(this));
                    if (this.loc.onLocation() == true) {
                        this.setFirewood(true);
                        this.setLoc(new SafeHouse(this));
                        this.loc.onLocation();
                    }
                } else {
                    System.out.println("Bu bölgeyi zaten temizlediniz.");
                    this.where();
                }
                break;
            case 4:
                if(!isFood()){
                this.setLoc(new Cave(this));
                if (this.loc.onLocation() == true) {
                    this.setFood(true);
                    this.setLoc(new SafeHouse(this));
                    this.loc.onLocation();
                }}else{
                    System.out.println("Bu bölgeyi zaten temizlediniz.");
                    this.where();
                }
                break;
            case 5:
                if(!isWater()){
                this.setLoc(new River(this));
                if (this.loc.onLocation() == true) {
                    this.setWater(true);
                    this.setLoc(new SafeHouse(this));
                    this.loc.onLocation();
                }}else{
                    System.out.println("Bu bölgeyi zaten temizlediniz.");
                    this.where();
                }
                break;
            case 6:
                this.setLoc(new Mine(this));
                this.loc.onLocation();
            case 9:
                this.status();
                this.Victory();
                this.where();
                break;
            case 0:
                System.out.println("Korktuğunu biliyordum!!");
                System.exit(0);
                break;
        }

    }

    public void status() {
        System.out.println("Durumunuz");
        System.out.println("---------------------------------");
        System.out.println("Sağlık: " + this.getHealth());
        System.out.println("Hasar: " + this.getDamage());
        System.out.println("Para: " + this.getMoney());
        System.out.println("Silahınız: " + this.getWeapon());
        System.out.println("Zırhınız: " + this.getArmor());
        if (isFirewood() == true) {
            System.out.println("Odun bulundu.");
        } else {
            System.out.println("Odun bulunamadı.");
        }
        if (isFood() == true) {
            System.out.println("Yemek bulundu.");
        } else {
            System.out.println("Yemek bulunamadı.");
        }
        if (isWater() == true) {
            System.out.println("Su bulundu.");
        } else {
            System.out.println("Su bulunamadı.");
        }

        System.out.println("---------------------------------");
    }

    public void Victory() {
        if (isFirewood() && isFood() && isWater()) {
            this.setVictory(1);
        }

        if (this.getVictory() == 1) {
            System.out.println("Tebrikler yaşamınızı sürderebilmeniz için gerekli her eşyayı buldunuz.\n" +
                    "Hayatınızın geri kalanında başarılar.\n" +
                    "ZAFER!!!!!");
            System.exit(0);

        }
    }

}


