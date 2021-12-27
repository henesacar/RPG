public class Forest extends DangerZone {
    Player player;

    public Forest(Player player) {
        super("Orman", 3, new Monsters(2, 4, 14, 7, "Vampir"), player,"Odun burada bulunur.");
        this.player = player;
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


