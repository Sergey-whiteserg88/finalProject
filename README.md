# finalProject
Финальный проект обучения сделан с использованием Spring Framework;
запуск на порту по умолчанию (8080);
база данных PostgresSql c именем bankApi содержит одну таблицу client;
в таблице client две колоонки (id, balance);
настройки для подключения к базе данных можно изменить через файл  "src/main/resources/dataBaseSettings.xlsx";
В MainController реализованы три функции (getBalance, takeMoney, putMoney);
для проверки функциональности getBalance нужно выполнить запрос в формате http://localhost:8080/getBalance/id;
для проверки функциональности takeMoney нужно выполнить запрос в формате http://localhost:8080/takeMoney/id/value;
для проверки функциональности putMoney нужно выполнить запрос в формате http://localhost:8080/putMoney/id/value;
формат ответа - json
![ScreenShot_DB](https://github.com/Sergey-whiteserg88/finalProject/assets/110084425/960ec767-ec1c-4fb9-b71b-4f632e9003c4)
