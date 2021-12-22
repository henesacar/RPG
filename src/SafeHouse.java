public class SafeHouse extends NormalLocation{
    public SafeHouse(Player player) {
        super(player, "Sığınak",1);
    }

    @Override
    public boolean onLocation() {
        System.out.println("Canınız yenilenmiştir.");

        return true;
    }
}
