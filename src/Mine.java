public class Mine extends DangerZone{

    public Mine(Player player) {
        super("Maden", 6,new Monsters(4,10,10,10,"Yılan"),player, "Karşınıza herşey çıkabilir!");

    }



    @Override
    public boolean onLocation() {
        if (combat()) {
            return true;
        } else {

            return false;
        }
    }
}
