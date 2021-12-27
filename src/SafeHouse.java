public class SafeHouse extends NormalLocation{
    public SafeHouse(Player player) {
        super( "Sığınak", 1,player,"Canınızı yenileyebilirsiniz");
        this.player = player;
    }

    @Override
    public boolean onLocation() {
        getPlayer().Victory();
        System.out.println("Canınız yenilenmiştir.");
        player.setHealth(getPlayer().getMhealth());
        this.getPlayer().status();
        getPlayer().where();
        return false;
    }
}
