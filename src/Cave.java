public class Cave extends DangerZone{
    Player player;
    public Cave(Player player) {
        super("Mağara", 4, new Monsters(1,3,10,4,"Zombi"),player,"Yemek burada bulunur.");
        this.player=player;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
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
