public class River extends  DangerZone{
    private Player player;
    public River(Player player) {
        super("Nehir", 5, new Monsters(3,7,20,12,"Ayı"),player,"Su burada bulunur.");
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
