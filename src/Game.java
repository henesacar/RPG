import java.util.Scanner;

public class Game {
    private Player player;
    private String info;
    private Location loc;
    private Scanner scn = new Scanner(System.in);

    public void Start() {
        System.out.println("Oyunumuza hoşgeldiniz.");
        System.out.print("Lütfen adınızı girin: ");
        info = scn.nextLine();
        Player gamer = new Player(info);
        System.out.println(gamer.getName() +" lütfen oynamak istediğin sınıf tercihini yap:");
        System.out.println("*******************************");
        gamer.select();
        gamer.where();






    }}