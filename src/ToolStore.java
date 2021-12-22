public class ToolStore extends NormalLocation {
    private Weapons weapon;

    public ToolStore(Player player) {
        super(player, "Mağaza", 2);
    }

    @Override
    public boolean onLocation() {
        System.out.println("Mağazaya hoşgeldiniz. Ne satın almak istersiniz? " +
                "\n 1 - Silah " +
                "\n 2 - Zırh");
        int buy = scn.nextInt();
        switch (buy) {
            case 1:
                weaponList();
                break;
            case 2:
                armorList();
                break;
            default:
                System.out.println("komikmi piç");
        }


        return false;
    }

    public void weaponList() {
        Weapons[] wep = {new Weapons(1, "Tabanca", 25, 2),
                new Weapons(2, "Kılıç", 35, 3),
                new Weapons(3, "Tüfek", 45, 7)};
        System.out.println("ID\tSilah\t\tHasar\tBedel");
        System.out.println("*******************************");
        for (Weapons x:wep
             ) {
            System.out.println(x.getWeaponId()+"\t"+x.getItemName()+"\t\t"+x.getWeaponDmg()+"\t\t"+x.getItemCost());
        System.out.println("-------------------------------");
        }
    }
    public void armorList(){
        Armors[] arm = {new Armors(1,"Hafif",15,1),
        new Armors(2,"Orta",25,3),
        new Armors(3,"Ağır",40,5)};
        System.out.println("ID\tZırh\t\tBlok\tBedel");
        System.out.println("*******************************");
        for (Armors x:arm
        ) {
            System.out.println(x.getArmorId()+"\t"+x.getItemName()+"\t\t"+x.getArmorBlk()+"\t\t"+x.getItemCost());
            System.out.println("-------------------------------");
        }
    }


}
