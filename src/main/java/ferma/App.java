package ferma;

import ferma.service.UtilService;



/*
 * Консольная игра Ферма
 * 
 * Настройки:
 * конфигураци hibernate and connect bd  храняться в файле /resources/META-INF/persistence.xml
 * для старта игры:
  * запустить по очереди sql скрипты по адресу /resources/db/migration/
  * файл откуда беруться данные о расстениях называеться plans.json, изменить название и путь можно
  * в классе PlantService(в отдельный конфиг только для этого файла решил не создавать).
  * Для загрузки файла json испольовал библиотеку ДЖексон.
  *
  * Доменые модели в пакете domain, BaseEntety - базовый абстракный класс(для того чтоб уменьшить код
   * и не писать  ади в каждом классе); Acct и Garden - классы сущностей пользовотеля и его полей для расстений(
   * связь односторонняя один ко многим); Plant - класс расстений.
   *
   * Сервестные классы, AcctService для работы с бд, к нему еще интерфейс AcctRepo( для остальных интерфесные классы решил не делать,
   * так как программа не большая и не планирую маштабировать); GardenComporatorUtil - использовал для сортировки полей;
   * InputUtil - отдельный сервисный класс ввода; PlantService - класс для работі с json; Menu основной класс логики прилоения;
   * UtilService - есервисный класс для запуска приложения.
  *
  * (c)Konovalov Anton, tohak199@gmil.com
  *
 */
public class App {


    public static void main (String[] args) {
         UtilService util = new UtilService();
        util.close();
    }
}
