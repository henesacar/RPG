public class Monsters {
    private int mobId;
    private int mobDamage;
    private int mobHealth;
    private int mobLoot;
    private String mobName;


    public int getMobId() {
        return mobId;
    }

    public void setMobId(int mobId) {
        this.mobId = mobId;
    }

    public int getMobDamage() {
        return mobDamage;
    }

    public void setMobDamage(int mobDamage) {
        this.mobDamage = mobDamage;
    }

    public int getMobHealth() {
        return mobHealth;
    }

    public void setMobHealth(int mobHealth) {
        this.mobHealth = mobHealth;
    }

    public int getMobLoot() {
        return mobLoot;
    }

    public void setMobLoot(int mobLoot) {
        this.mobLoot = mobLoot;
    }

    public String getMobName() {
        return mobName;
    }

    public void setMobName(String mobName) {
        this.mobName = mobName;
    }

    public Monsters(int mobId, int mobDamage, int mobHealth, int mobLoot, String mobName) {
        this.mobId = mobId;
        this.mobDamage = mobDamage;
        this.mobHealth = mobHealth;
        this.mobLoot = mobLoot;
        this.mobName = mobName;
    }

    public int mobCount() {
        int mobNo = (int) (Math.random() * 3) + 1;
        return mobNo;

    }
}


