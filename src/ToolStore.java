public class ToolStore extends NormalLocation {
    private Weapons weapon;
    private Armors armor;
    private Player player;


    public ToolStore(Player player) {
        super("Mağaza", 2, player,"Eşya satın alabilirsiniz.");
        this.player = player;
    }

    public Armors getArmor() {
        return armor;
    }

    public void setArmor(Armors armor) {
        this.armor = armor;
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
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
        setPlayer(this.getPlayer());
        this.balanceCheck();
        System.out.println("Mağazaya hoşgeldiniz. Ne satın almak istersiniz? " +
                "\n 1 - Silah " +
                "\n 2 - Zırh " +
                "\n 3 - Para Kontrolü" +
                "\n 4 - Envanter Kontrolü" +
                "\n 0 - Çıkış");
        int buy = scn.nextInt();
        switch (buy) {
            case 1:
                weaponList();
                buyWeapon();
                onLocation();
                break;
            case 2:
                armorList();
                buyArmor();
                onLocation();
                break;
            case 0:
                System.out.println("Çıkış yaptınız.");
                getPlayer().Victory();
                player.where();
                break;
            case 4:
                player.status();
                System.out.println("Ne yapmak istersiniz?\n" +
                        " 1 - Mağazaya dön\n" +
                        " 2 - Başlangıca dön");
                int a = scn.nextInt();
                if (a == 1) {
                    onLocation();
                    break;
                } else if (a == 2) {
                    getPlayer().Victory();
                    getPlayer().where();
                    break;
                }
            case 3: this.balanceCheck();
            break;
            default:
                System.out.println("Geçersiz seçim");
                getPlayer().Victory();
                player.where();
        }


        return false;
    }

    public void weaponList() {
        Weapons[] wep = {new Weapons(1, "Tabanca", 25, 2),
                new Weapons(2, "Kılıç", 35, 3),
                new Weapons(3, "Tüfek", 45, 7)};
        System.out.println("ID\tSilah\t\tHasar\tBedel");
        System.out.println("*******************************");
        for (Weapons x : wep
        ) {
            System.out.println(x.getWeaponId() + "\t" + x.getItemName() + "\t\t" + x.getWeaponDmg() + "\t\t" + x.getItemCost());
            System.out.println("-------------------------------");
        }
        System.out.println("0 \tÇıkış");
    }

    public void armorList() {
        Armors[] arm = {new Armors(1, "Hafif", 15, 1),
                new Armors(2, "Orta", 25, 3),
                new Armors(3, "Ağır", 40, 5)};
        System.out.println("ID\tZırh\t\tBlok\tBedel");
        System.out.println("*******************************");
        for (Armors x : arm
        ) {
            System.out.println(x.getArmorId() + "\t" + x.getItemName() + "\t\t" + x.getArmorBlk() + "\t\t" + x.getItemCost());
            System.out.println("-------------------------------");
        }
        System.out.println("0 \tÇıkış");
    }

    public void buyWeapon() {
        System.out.println("*******************************");
        System.out.print("Almak istediğiniz silahı seçiniz: ");
        int bought = scn.nextInt();
        switch (bought) {
            case 1:
                player.setDamage(player.getDefaultDamage());
                setWeapon(new Weapons(1, "Tabanca", 25, 2));
                if (player.getMoney() > weapon.getItemCost()) {
                    System.out.println("Satın aldığınız silah ----> " + weapon.getItemName());
                    player.setDamage(player.getDamage() + weapon.getWeaponDmg());
                    player.setMoney(player.getMoney() - weapon.getItemCost());
                    System.out.println("Mevcut hasarınız: " + player.getDamage() +
                            "\nKalan paranız: " + player.getMoney());
                    player.setWeapon("Tabanca");
                } else {
                    System.out.println("Paranız yeterli değil. İhtiyacınız olan miktar: "+(this.getWeapon().getItemCost()-this.getPlayer().getMoney())+"\n" +
                            "Geri yönlendiriliyorsunuz.");
                }
                break;
            case 2:
                player.setDamage(player.getDefaultDamage());
                setWeapon(new Weapons(2, "Kılıç", 35, 3));
                if (player.getMoney() > weapon.getItemCost()) {
                    System.out.println("Satın aldığınız silah ----> " + weapon.getItemName());
                    player.setDamage(player.getDamage() + weapon.getWeaponDmg());
                    player.setMoney(player.getMoney() - weapon.getItemCost());
                    System.out.println("Mevcut hasarınız: " + player.getDamage());
                    player.setWeapon("Kılıç");
                } else {
                    System.out.println("Paranız yeterli değil. İhtiyacınız olan miktar: "+(this.getWeapon().getItemCost()-this.getPlayer().getMoney())+"\n" +
                            "Geri yönlendiriliyorsunuz.");
                }
                break;
            case 3:
                player.setDamage(player.getDefaultDamage());
                setWeapon(new Weapons(3, "Tüfek", 45, 7));
                if (player.getMoney() > weapon.getItemCost()) {
                    System.out.println("Satın aldığınız silah ----> " + weapon.getItemName());
                    player.setDamage(player.getDamage() + weapon.getWeaponDmg());
                    player.setMoney(player.getMoney() - weapon.getItemCost());
                    System.out.println("Mevcut hasarınız: " + player.getDamage());
                    player.setWeapon("Tüfek");
                } else {
                    System.out.println("Paranız yeterli değil. İhtiyacınız olan miktar: "+(this.getArmor().getItemCost()-this.getPlayer().getMoney())+"\n" +
                            "Geri yönlendiriliyorsunuz.");
                }
                break;
            case 0:
                System.out.println("Çıkış yaptınız. Üst menüye yönlendiriliyorsunuz.");
                onLocation();
                break;
            default:
                System.out.println("Geçersiz seçim.");
                buyWeapon();
        }
    }

    public void buyArmor() {
        System.out.println("*******************************");
        System.out.print("Almak istediğiniz zırhı seçiniz: ");
        int bought = scn.nextInt();
        switch (bought) {
            case 1:
                player.setBlock(0);
                setArmor(new Armors(1, "Hafif Zırh", 15, 1));
                if (player.getMoney() > armor.getItemCost()) {
                    System.out.println("Satın aldığınız zırh ----> " + armor.getItemName());
                    player.setBlock(armor.getArmorBlk());
                    player.setMoney(player.getMoney() - armor.getItemCost());
                    System.out.println("Mevcut blok değeriniz: " + player.getBlock());
                    player.setArmor("Hafif Zırh");
                    this.getPlayer().setBlock(1);
                } else {
                    System.out.println("Paranız yeterli değil. İhtiyacınız olan miktar: "+(this.getArmor().getItemCost()-this.getPlayer().getMoney())+"\n" +
                            "Geri yönlendiriliyorsunuz.");

                }
                break;
            case 2:
                player.setBlock(0);
                setArmor(new Armors(2, "Orta Zırh", 25, 3));
                if (player.getMoney() > armor.getItemCost()) {
                    System.out.println("Satın aldığınız zırh ----> " + armor.getItemName());
                    player.setBlock(armor.getArmorBlk());
                    player.setMoney(player.getMoney() - armor.getItemCost());
                    System.out.println("Mevcut blok değeriniz: " + player.getBlock());
                    player.setArmor("Orta Zırh");
                    this.getPlayer().setBlock(3);
                } else {
                    System.out.println("Paranız yeterli değil. İhtiyacınız olan miktar: "+(this.getArmor().getItemCost()-this.getPlayer().getMoney())+"\n" +
                            "Geri yönlendiriliyorsunuz.");
                }
                break;
            case 3:
                player.setBlock(0);
                setArmor(new Armors(3, "Ağır Zırh", 3, 5));
                if (player.getMoney() > armor.getItemCost()) {
                    System.out.println("Satın aldığınız zırh ----> " + armor.getItemName());
                    player.setBlock(armor.getArmorBlk());
                    player.setMoney(player.getMoney() - armor.getItemCost());
                    System.out.println("Mevcut blok değeriniz: " + player.getBlock());
                    player.setArmor("Ağır Zırh");
                    this.getPlayer().setBlock(5);
                } else {
                    System.out.println("Paranız yeterli değil. İhtiyacınız olan miktar: "+(this.getArmor().getItemCost()-this.getPlayer().getMoney())+"\n" +
                            "Geri yönlendiriliyorsunuz.");
                }
                break;
            case 0:
                System.out.println("Çıkış yaptınız. Üst menüye yönlendiriliyorsunuz.");
                onLocation();
                break;
            default:
                System.out.println("Geçersiz seçim.");
                buyArmor();

        }

    }
    public void balanceCheck(){
        System.out.println("Şu anki paranız: "+getPlayer().getMoney());
    }
}
