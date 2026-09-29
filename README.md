# SortingApp
Учебное приложение (проект Aston) для сортировки объектов класса Student (Номер группы, Средний балл, Номер зачётной книжки)

## Команда
- Гринчук Кирилл ([@LeeryLis](https://github.com/LeeryLis), тимлид) - архитектура приложения, работа с файлами (FileService), code review
- Литовченко Сергей ([@silver14056](https://github.com/silver14056)) - основной цикл работы приложения, связь логики с пользовательским взаимодействием, code review
- Беляев Михаил ([@Maslina](https://github.com/trigunpichita-design)) - добавление паттерна Builder для класса Student, случайное заполнение (RandomFiller)
- Исаева Алёна ([@isaeva-alyona](https://github.com/isaeva-alyona)) - реализация трёх разных вариантов сортировки, выполнение дополнительного задания 1 (особая сортировка EvenOddRecordBookSort)
- Фоменко Евгений ([@homenkoevgeniy](https://github.com/homenkoevgeniy)) - выполнение дополнительного задания 4 (ThreadCounter)

## Ветки
- `master` - итоговая ветка
- `dev` - промежуточная ветка для активной разработки

Разработка велась в отдельных ветках, которые затем
сливались в ветку `dev` через Pull Request
(https://github.com/LeeryLis/SortingApp/pulls?q=is%3Apr+is%3Aclosed)

## Стек
Java 25, Maven (сборка), JUnit 5 (тесты), JaCoCo (покрытие)

## Архитектура
### Паттерн Builder
Класс `Student` создаётся только через `StudentBuilder`
(добавлен статический метод Student.builder для создания экземпляра StudentBuilder):
```
Student s = Student.builder()
    .groupNumber("...")
    .averageScore(...)
    .recordBookNumber(...)
    .build();
```

### Паттерн Strategy
Интерфейс `SortStrategy` с методом `sort` и реализациями:
- `BubbleSort`
- `MergeSort`
- `QuickSort`
- `EvenOddRecordBookSort`

### Паттерн Decorator
`EvenOddRecordBookSort` принимает в конструкторе `SortStrategy`.
Метод `sort` реализует только новый функционал для `EvenOddRecordBookSort`,
обычную сортировку производит переданная стратегия сортировки (inner)

## Сортировка
Поддерживается сортировка по всем трём полям:
- по номеру группы
- по среднему баллу
- по номеру зачётной книжки

## Заполнение данных
- из файла - `FileService`
- случайными значениями - `RandomFiller`
- вручную - `ConsoleService`

## Валидация
Валидация выполняется для всех источников данных (файл, ручной ввод)

## Дополнительные задания
### Доп. 1 - сортировка чётных/нечётных
`EvenOddRecordBookSort` - сортировка по номеру зачётной книжки.
Чётные значения сортируются в натуральном порядке, нечётные остаются на исходных позициях

### Доп. 2 - запись в файл (append)
`FileService` - результаты сортировки дописываются в файл без перезаписи

### Доп. 3 - стримы
Используется кастомная коллекция `StudentList`.
Заполнение коллекций реализовано через Stream API

### Доп. 4 - многопоточный подсчёт
`ThreadCounter` - количество вхождений элемента N (конкретного студента)
считается в несколько потоков, результат выводится в консоль. Сравнение выполняется
по equals

## Запуск
### Требования
- JDK 25
- Maven 3.9+

### Сборка
```bash
git clone https://github.com/LeeryLis/SortingApp
cd SortingApp
mvn clean package
```

### Запуск проекта
```bash
java -jar target/SortingApp-<version>.jar
```

## Тесты
Тесты написаны на JUnit 5

### Запуск тестов
```bash
mvn test
```

### Отчёт о покрытии
Отчёт о покрытии JaCoCo (HTML): `target/site/jacoco/index.html`