public class Weapons extends Items{
    private int weaponDmg;
    private int weaponId;

    public int getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public int getWeaponDmg() {
        return weaponDmg;
    }

    public void setWeaponDmg(int weaponDmg) {
        this.weaponDmg = weaponDmg;
    }

    public Weapons(int weaponId,String itemName, int itemCost, int weaponDmg) {
        super(itemName, itemCost);
        this.weaponDmg=weaponDmg;
        this.weaponId=weaponId;
    }

}
