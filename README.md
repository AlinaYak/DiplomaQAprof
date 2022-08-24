## Документация

+ [План по автоматизации тестирования](./documentation/Plan.md)

+ [Отчет по итогам тестированию](./documentation/Report.md)

+ [Отчет по итогам автоматизации](./documentation/Summary.md)

## Инструкция запуска тестов для приложения с поддержкой БД

1. Клонировать проект из репозитория командой: ```git clone```

2. Запустить контейнеры с MySql, PostgreSQL и Node.js в Docker командой: ```docker-compose up```

### Запуск с поддержкой MySQL

1. Запустить SUT с поддержкой MySQL командой:
   ```java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar```
1. Запустить тесты с MySQL командой:
   ```gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app allureReport```
1. Построить отчёт Allure командой:
   ```gradlew allureServe```

### Запуск с поддержкой PostgreSQL

1. Запустить SUT с поддержкой PostgreSQL командой:
   ```java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar```
2. Запустить тесты с PostgreSQL командой:
   ```gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app allureReport```
3. Построить отчёт Allure командой:
   ```gradlew allureServe```

После завершения тестирования закончить работу приложения (Ctrl+C), остановить контейнеры в Docker командой: ```docker-compose down```