package ferma.service;

import ferma.domain.Acct;
import ferma.domain.Garden;
import ferma.domain.Plant;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Menu {
    private static final String ERRORINPUT = "Не правильно ввели цифру, попробуйте еще раз..";
    private static boolean checkInput = false;
    private Acct acct;

    public Menu() {
    }

    public Acct getAcct() {
        return acct;
    }

    public void startMenu(List<Acct> acctList) {
        System.out.println("Добрый день, пора поиграть. Введите в концоль цифру соотвецтвующею меню");
        System.out.println("1.Создать нового пользователя");
        System.out.println("2.Зайти в игру использую логин и пароль");
        do {
            int numberMenu = InputUtil.getInputNumber();
            if (numberMenu > 0 && numberMenu < 3) {
                switch (numberMenu) {
                    case 1:
                        Acct acct = createUserMenu(acctList);
                        if (acct != null) {
                            gameMenu(acct);
                        }else {
                            startMenu(acctList);
                        }

                        break;
                    case 2:
                        Acct user = loginPlayGame(acctList);
                        int check = 0;
                        for (; user == null; ) {
                            if (check > 2) {
                                System.out.println("Меню создание нового пользователя:");
                                user = createUserMenu(acctList);
                                if (user != null) {
                                    gameMenu(user);
                                }else {
                                    startMenu(acctList);
                                }

                                break;

                            }
                            check += 1;
                            System.out.println("не правильно введены данные, попробуйте еще.." +
                                    "после 3 не правельных попыток перебросит в меню создания нового пользователя");
                            user = loginPlayGame(acctList);
                        }
                        gameMenu(user);
                        break;


                }
                if (acct == null) {
                    return;
                }
                checkInput = true;
            } else {
                System.out.println(ERRORINPUT);
            }
        } while (!checkInput);
        checkInput = false;
    }


    private Acct createUserMenu(List<Acct> list) {
        Set<Garden> gardens = new TreeSet<>(new GardenComporatorUtil());
        for (int i = 1; i < 9; i++) {
            Garden garden = new Garden(i, true);
            gardens.add(garden);
        }
        String name = InputUtil.getNameLogin();
        String pass = InputUtil.getNamePass();
        if (name.isEmpty()|| pass.isEmpty()){
            System.out.println("Данные нового пользователя введены не коректно");
            return null;
        }

        for (Acct us : list) {
            if (us.getName().equals(name)) {
                System.out.println("Такой пользователь уже существует");
                return null;
            }
        }
        return new Acct(name, pass, 1000L, false, gardens);
    }

    private Acct loginPlayGame(List<Acct> acctList) {
        String name = InputUtil.getNameLogin();
        String pass = InputUtil.getNamePass();
        for (Acct user : acctList) {
            if (user.getName().equals(name) && user.getPass().equals(pass)) {
                user.setCheckup(false);
                return user;
            }
        }
        return null;
    }

    private void gameMenu(Acct user) {
        Set<Garden> gardens = new TreeSet<>(new GardenComporatorUtil());
        gardens.addAll(user.getGardens());
        user.setGardens(gardens);
        printGardens(gardens);
        List<Plant> plants = PlantService.loadJakson();
        System.out.println("Ваши деньги= " + user.getMoney());
        System.out.println();
        System.out.println("Выберете действие:");
        System.out.println("1.Посадить");
        System.out.println("2.Собрать урожай");
        System.out.println("3.Выйти");
        int checkNumber = 0;
        for (; (checkNumber < 1) || (checkNumber >= 4); ) {
            System.out.println("Введите значаение");
            checkNumber = InputUtil.getInputNumber();
        }
        if (checkNumber == 1) {
            setPlants(user, plants);
        } else if (checkNumber == 2) {
            getPlants(user);
        } else {
            this.acct = user;
            System.out.println("Выход, приходи еще");
        }


    }

    private void setPlants(Acct user, List<Plant> plants) {
        printGardens(user.getGardens());
        int number = -1;
        for (; number < 1 || number > 8; ) {
            System.out.println("Введите цифру.В какое поле хотите посадить расстение?");
            number = InputUtil.getInputNumber();
        }

        if (setPlant(user, number, plants)) {
            System.out.println("_____ПОСАЖЕНО_____");
        } else {
            System.out.println("______Не вышло посадить расстение. Возможно у вас не хватило денег" +
                    " или поле уже посажено______");
        }
        gameMenu(user);
    }

    private boolean setPlant(Acct user, int number, List<Plant> plants) {
        int plant = -1;
        for (; plant < 1 || plant > plants.size(); ) {

            for (Plant p : plants) {
                int n = 1;
                System.out.println(n + ". " + p.toString());
                n+=1;
            }
            System.out.println("Введите цифру, какого расстение хотите посадить");
            plant = InputUtil.getInputNumber();

        }
        Plant myPlant = plants.get(plant - 1);
        if (user.getMoney() - myPlant.getPriceBy() < 0) {

            return false;
        }
        LocalDateTime time = LocalDateTime.now().plusMinutes(myPlant.getTime());
        for (Garden garden : user.getGardens()) {
            if (garden.getPole() == number && garden.getCheckarea()) {
                garden.setSbor(time);
                garden.setCheckarea(false);
                garden.setPriceShell(myPlant.getPriceShell());
                user.setMoney(user.getMoney() - myPlant.getPriceBy());
                return true;
            }
        }
        return false;
    }

    private void getPlants(Acct user) {
        printGardens(user.getGardens());
        int number = -1;
        for (; number < 1 || number > 8; ) {
            System.out.println("Введите цифру.В какое поле хотите собрать урожай?");
            number = InputUtil.getInputNumber();
        }
        if (getPlant(user, number)) {
            System.out.println("______Поздравляю вы получили деньги за собраный урожай______");
        } else {
            System.out.println("____Собрать урожай пока не получилось, попробуй когда созреет_____");
        }
        gameMenu(user);
    }

    private boolean getPlant(Acct user, int number) {
        for (Garden garden : user.getGardens()) {

            if (!garden.getCheckarea() && !isCheckPole(garden) && garden.getPole() == number) {
                garden.setCheckarea(true);
                user.setMoney(user.getMoney() + garden.getPriceShell());
                garden.setPriceShell(0L);
                return true;
            }
        }
        return false;
    }

    private void printGardens(Set<Garden> gardens) {
        System.out.println("_______________________");
        for (Garden garden : gardens) {
            String s = "Поле #" + garden.getPole();

            if (garden.getSbor() != null) {
                if (isCheckPole(garden)) {
                    LocalDateTime now = LocalDateTime.now();
                    Duration duration = Duration.between(now, garden.getSbor());
                    s = s + ". Пасажено растение, созреет через " + duration.toMinutes() + " минут. ";
                } else if (!garden.getCheckarea()) {
                    s = s + ". Урожай готов, собирай";
                } else {
                    s = s + " пустое и готов к посадке растений";
                }
            } else {
                s = s + " поле пустое, еще не разу не садили";
            }
            System.out.println(s);
        }
        System.out.println("___________________________");

    }

    private boolean isCheckPole(Garden garden) {
        LocalDateTime dateTime = LocalDateTime.now();
        if (garden.getSbor().isAfter(dateTime)) {
            return true;
        }
        return false;
    }


}
