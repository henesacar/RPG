public class Armors extends Items{
    private int armorBlk;
    private int armorId;

    public int getArmorId() {
        return armorId;
    }

    public void setArmorId(int armorId) {
        this.armorId = armorId;
    }

    public int getArmorBlk() {
        return armorBlk;
    }

    public void setArmorBlk(int armorBlk) {
        this.armorBlk = armorBlk;
    }

    public Armors(int armorId,String itemName, int itemCost, int armorBlk) {
        super(itemName, itemCost);
        this.armorBlk = armorBlk;
        this.armorId =armorId;
    }

}
