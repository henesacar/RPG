public abstract class Template{
    private int id;
    private int damage;
    private int health;
    private int money;
    private int block;
    private String sinif;

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public Template(int id, String sinif, int damage, int health, int money, int block) {
        this.damage = damage;
        this.health = health;
        this.money = money;
        this.sinif = sinif;
        this.id=id;
        this.block=block;
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

    public String getSinif() {
        return sinif;
    }

    public void setSinif(String sinif) {
        this.sinif = sinif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
