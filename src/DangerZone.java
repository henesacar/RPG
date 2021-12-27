public abstract class DangerZone extends Location {
    private Monsters mob;
    private int chance;

    public DangerZone(String name, int id, Monsters mob, Player player, String info) {
        super(name, id, player, info);
        this.mob = mob;
    }

    public int getChance() {
        this.chance = (int) (Math.random() * 100);
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public Monsters getMob() {
        return mob;
    }

    public void setMob(Monsters mob) {
        this.mob = mob;
    }

    @Override
    public abstract boolean onLocation();

    public boolean combat() {
        int crowd;
        if (this.getMob().getMobId() == 4) {
            crowd=(int) (Math.random() * 5) + 1;
        }else{
            crowd=this.getMob().mobCount();
        }
        if (this.getMob().getMobId() == 4) {
            adjustSnake();
        }
        System.out.println(this.getName() + " bölgesine ulaştınız.");
        System.out.println(crowd + " adet " + this.getMob().getMobName() + " tarafından tuzağa düşürüldünüz!!!");
        beforeCombat();
        System.out.println("Kararınız nedir?\n" +
                "1 - Savaş\n" +
                "2 - Geri çekil");
        int choice = scn.nextInt();
        scn.nextLine();
        switch (choice) {
            case 1:
                int mhealth = getMob().getMobHealth();
                int luck = this.getChance();
                while (crowd != 0) {
                    int playerDamage = (getMob().getMobHealth() - getPlayer().getDamage() < 0) ? 0 : getMob().getMobHealth() - getPlayer().getDamage();
                    int blockedDamage = getMob().getMobDamage() - getPlayer().getBlock();
                    if (blockedDamage < 0) {
                        blockedDamage = 0;
                    }
                    int mobDamage = (this.getPlayer().getHealth() - blockedDamage < 0) ? 0 : this.getPlayer().getHealth() - blockedDamage;
                    if (firstHit(luck) == 1) {
                        getPlayer().setHealth(mobDamage);
                        getMob().setMobHealth(playerDamage);
                    } else {
                        getMob().setMobHealth(playerDamage);
                        getPlayer().setHealth(mobDamage);
                    }
                    duringCombat(this.getPlayer().getHealth(), this.getMob().getMobHealth(), this.getPlayer().getBlock(), crowd);
                    if (this.getPlayer().getHealth() == 0) {
                        System.out.println("Hayatınızı kaybettiniz!\n" +
                                "GAME OVER!!!");
                        System.exit(0);
                    }
                    if (getMob().getMobHealth() == 0) {
                        crowd--;
                        getMob().setMobHealth(mhealth);
                        luck = this.getChance();
                        loot();
                        if (crowd == 0) {
                            System.out.println("Tüm rakiplerinizi alt ettiniz. Zaferinizin ardından sığınağa dönüyorsunuz.");
                            return true;
                        }
                    }

                    System.out.println("Ne yapmak istersiniz ?\n" +
                            "1 - <Savaş> \n" +
                            "2 - <Geri Çekil> ?");
                    int asd = scn.nextInt();
                    if (asd == 2) {
                        System.out.println("Sığınağa geri çekiliyorsunuz.");
                        getPlayer().setLoc(new SafeHouse(getPlayer()));
                        getPlayer().getLoc().onLocation();
                        break;
                    }
                    if (asd == 1) {
                        continue;

                    }
                }
                break;
            case 2:
                System.out.println("Güvenli eve doğru geri çekiliyorsunuz.");
                getPlayer().setLoc(new SafeHouse(getPlayer()));
                getPlayer().getLoc().onLocation();
                break;
            default:
                System.out.println("Geçersiz seçim\n");
                getPlayer().Victory();
                getPlayer().where();
                break;

        }
        return false;
    }


    public void beforeCombat() {
        int phealth = getPlayer().getHealth();
        System.out.println(this.getMob().getMobName() + " Durumu");
        System.out.println("---------------------------------");
        System.out.println(this.getMob().getMobName() + " can: " + this.getMob().getMobHealth());
        System.out.println(this.getMob().getMobName() + " hasarı: " + this.getMob().getMobDamage());
        if(this.getMob().getMobId() ==4){
            System.out.println(this.getMob().getMobName()+ "ganimet listesi:\n" +
                    "Silah bulma ihtimaliniz:\t %15\n" +
                    "Zırh bulma ihtimaliniz:\t\t %15\n" +
                    "Para bulma ihtimaliniz:\t\t %25\n" +
                    "Birşey bulamama ihtimaliniz: %45");
        }else {
            System.out.println(this.getMob().getMobName() + " ganimeti: " + this.getMob().getMobLoot());
        }
        System.out.println("#################################");
        System.out.println("Durumunuz");
        System.out.println("---------------------------------");
        System.out.println("Sağlık: " + phealth);
        System.out.println("Hasar: " + this.getPlayer().getDamage());
        System.out.println("Para: " + this.getPlayer().getMoney());
        System.out.println("Silahınız: " + this.getPlayer().getWeapon());
        System.out.println("Zırhınız: " + this.getPlayer().getArmor());
        System.out.println("---------------------------------");
    }

    public void duringCombat(int p, int m, int b, int c) {
        int phealth = getPlayer().getHealth();
        System.out.println(this.getMob().getMobName() + " Durumu");
        System.out.println("---------------------------------");
        System.out.println(this.getMob().getMobName() + " aldığı hasar: " + this.getPlayer().getDamage());
        System.out.println(this.getMob().getMobName() + " kalan sağlık: " + m);
        if (m == 0) {
            System.out.println(this.getMob().getMobName() + " sayısı: " + (c - 1));
        } else {
            System.out.println(this.getMob().getMobName() + " sayısı: " + c);
        }
        System.out.println("#################################");
        System.out.println("Durumunuz");
        System.out.println("---------------------------------");
        if (b > this.getMob().getMobDamage()) {
            System.out.println("Aldığınız hasar: 0");
        } else {
            System.out.println("Aldığınız hasar: " + this.getMob().getMobDamage());
        }
        if (getPlayer().getHealth() < phealth / 2) {
            System.out.println("Sağlığınızın yarısından fazlasını kaybettiniz.\n");
        }
        if (b > 0) {
            System.out.println(b + " hasar zırhınız sayesinde engellendi.");
        }
        System.out.println("Kalan Sağlık: " + p);
        System.out.println("---------------------------------");
    }

    public void loot() {
        if (this.getMob().getMobId() == 4) {
            this.getPlayer().Victory();
            this.snakeLoot();
        } else {
            getPlayer().Victory();
            getPlayer().setMoney(getPlayer().getMoney() + getMob().getMobLoot());
            System.out.println(getMob().getMobLoot() + " altın buldunuz.\nMevcut paranız: " + getPlayer().getMoney());
        }
    }

    public int firstHit(int a) {
        if (a % 2 == 0) {
            return 1;
        } else {
            return 0;
        }

    }

    public void adjustSnake() {
        int randDmg = 2 + (int) (Math.random() * 4) + 1;
        this.getMob().setMobDamage(randDmg);
    }

    public void snakeLoot() {
        int gRoll = (int) (Math.random() * 100) + 1;
        if (gRoll <= 15) {
            int sRoll = (int) (Math.random() * 100) + 1;
            if (sRoll <= 20) {
                if (this.getPlayer().getDamage() < this.getPlayer().getDefaultDamage() + 7) {
                    this.getPlayer().setDamage(this.getPlayer().getDefaultDamage() + 7);
                    this.getPlayer().setWeapon("Tüfek");
                    System.out.println("Bir tüfek buldunuz.");
                } else if (this.getPlayer().getDamage() == this.getPlayer().getDefaultDamage() + 7) {
                    System.out.println("Tüfek buldunuz. Elinizde zaten bulunduğu için yerine 7 altın kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 7);
                }
            } else if (sRoll > 20 && sRoll <= 50) {
                if (this.getPlayer().getDamage() < (this.getPlayer().getDefaultDamage() + 5)) {
                    this.getPlayer().setDamage(this.getPlayer().getDefaultDamage() + 5);
                    this.getPlayer().setWeapon("Kılıç");
                    System.out.println("Kılıç buldunuz.");
                } else if (this.getPlayer().getDamage() == this.getPlayer().getDefaultDamage() + 5) {
                    System.out.println("Kılıç buldunuz. Elinizde zaten bulunduğu için yerine 5 altın kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                } else {
                    System.out.println("Kılıç buldunuz. Elinizdeki silah daha güçlü olduğu için yerine 5 altın kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                }

            } else {
                if (this.getPlayer().getDamage() < (this.getPlayer().getDefaultDamage() + 2)) {
                    this.getPlayer().setDamage(this.getPlayer().getDefaultDamage() + 2);
                    this.getPlayer().setWeapon("Tabanca");
                    System.out.println("Tabanca buldunuz.");
                } else if (this.getPlayer().getDamage() == this.getPlayer().getDefaultDamage() + 2) {
                    System.out.println("Tabanca buldunuz. Elinizde zaten bulunduğu için yerine 2 altın kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 2);
                } else {
                    System.out.println("Tabanca buldunuz. Elinizdeki silah daha güçlü olduğu için yerine 2 altın kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 2);
                }
            }

        } else if (gRoll > 15 && gRoll <= 30) {
            int sRoll = (int) (Math.random() * 100) + 1;
            if (sRoll <= 20) {
                if (this.getPlayer().getBlock() < 5) {
                    this.getPlayer().setBlock(5);
                    this.getPlayer().setArmor("Ağır Zırh");
                    System.out.println("Ağır zırh buldunuz.");
                } else if (this.getPlayer().getBlock() == 5) {
                    System.out.println("Ağır zırh buldunuz. Elinizde zaten bulunduğu için yerine 5 altın kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                }
            } else if (sRoll > 20 && sRoll <= 50) {
                if (this.getPlayer().getBlock() < 3) {
                    this.getPlayer().setBlock(3);
                    this.getPlayer().setArmor("Orta Zırh");
                    System.out.println("Orta zırh buldunuz.");
                } else if (this.getPlayer().getBlock() == 3) {
                    System.out.println("Ağır zırh buldunuz. Elinizde zaten bulunduğu için yerine 3 altın kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 3);
                } else {
                    System.out.println("Orta zırh buldunuz. Elinizdeki zırh daha dayanıklı olduğu için yerine 3 altın kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 3);
                }

            } else {
                if (this.getPlayer().getBlock() < 1) {
                    this.getPlayer().setBlock(1);
                    this.getPlayer().setArmor("Hafif Zırh");
                    System.out.println("Hafif zırh buldunuz.");
                } else if (this.getPlayer().getBlock() == 1) {
                    System.out.println("Ağır zırh buldunuz. Elinizde zaten bulunduğu için yerine 1 altın kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                } else {
                    System.out.println("Hafif zırh buldunuz. Elinizdeki zırh daha dayanıklı olduğu için yerine 3 altın kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                }
            }
        }else if(gRoll > 30 && gRoll <= 55){
            int sRoll = (int) (Math.random() * 100) + 1;
            if(sRoll <= 20){
                System.out.println("10 altın buldunuz.");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+10);
            }else if(sRoll >20 && sRoll <= 50){
                System.out.println("5 altın buldunuz.");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+5);
            }else if(sRoll > 50 && sRoll <= 100){
                System.out.println("1 altın buldunuz.");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+1);
            }
        }else{
            System.out.println("Hiçbirşey bulamadınız.");
        }


    }

}












