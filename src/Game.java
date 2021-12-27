import java.util.Scanner;

public class Game {
    protected String info;
    private Scanner scn = new Scanner(System.in);

    public void Start() {
        System.out.println("Issız adaya hoşgeldiniz.\n" +
                "Hayatta kalmak için 3 eşyayı toplamanız gerekmektedir.");
        System.out.print("Lütfen adınızı girin: ");
        info = scn.nextLine();
        Player gamer = new Player(info);
        System.out.println(gamer.getName() + " lütfen oynamak istediğin sınıf tercihini yap:");
        gamer.select();
        gamer.setMhealth(gamer.getHealth());
        gamer.setWeapon("Yumruk");
        gamer.setArmor("Yok");
        gamer.Victory();
        gamer.where();


    }
}