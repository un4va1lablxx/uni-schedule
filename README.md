# 🎓 Расписание университета ИТМО

Кросс-платформенное веб-приложение для отображения расписания университета.  
Бэкенд на Java + Spring Boot, фронтенд — HTML/CSS/JS (Thymeleaf).  
Проект создан для автоматизации доступа студентов и преподавателей к актуальному расписанию.

---

## ⚠️ Дисклеймер

Проект является учебным (pet-проектом). Название университета «ИТМО» использовано исключительно в качестве примера для демонстрации функциональности и не связано с реальным расписанием или официальными данными вуза.  
Все права на название принадлежат его правообладателю. Проект не преследует коммерческих целей и не нарушает авторские права.

---

## 🚀 Функциональность

- Просмотр расписания по группам, преподавателям, аудиториям.
- Навигация по неделям.
- Административная панель для редактирования расписания.

---

## 🛠 Технологии

- **Java 17**
- **Spring Boot** (Web, Data JPA, Security)
- **PostgreSQL**
- **Maven**
- **Thymeleaf**

---

## 📋 Требования

- Java 17+
- Maven 3.6+
- PostgreSQL

---

## 🔧 Установка и запуск

1. **Клонировать репозиторий**
   ```bash
   git clone https://github.com/un4va1lablxx/uni-schedule.git
   cd uni-schedule
   ```

2. **Создать базу PostgreSQL**  
   Обновить `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/schedule_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Инициализировать базу данных**  
   Файл `init_db.sql` находится в `src/main/resources/`.  
   Необходимо выполнить его в pgAdmin: правой кнопкой по созданной БД → *Restore...* и выбрать файл.

4. **Запустить приложение**
   ```bash
   mvn spring-boot:run
   ```

5. **Открыть в браузере**  
   [http://localhost:8080](http://localhost:8080)

> **Примечание:** для доступа к панели администрирования перейти по адресу `http://localhost:8080/admin`, логин и пароль: `admin` / `admin`.

---

## 🌄 Внешний вид

| Главная страница | Страница входа |
|------------------|----------------|
| <img width="1884" alt="Главная" src="https://github.com/user-attachments/assets/55fc7443-e602-4e4a-b91d-fc2131d6d9b0" /> | <img width="704" alt="Вход" src="https://github.com/user-attachments/assets/3d11d785-2458-42f6-80ee-db2093efe1ba" /> |

| Панель администрирования | Выбор таблицы |
|--------------------------|---------------|
| <img width="1909" alt="Админка" src="https://github.com/user-attachments/assets/6ed739d1-e4f2-421f-9632-8ef6d501d7cb" /> | <img width="1376" alt="Выбор таблицы" src="https://github.com/user-attachments/assets/b0fa66d4-531a-43e2-abbe-a2cbe4b01907" /> |

| Просмотр содержимого | Редактирование пары |
|----------------------|---------------------|
| <img width="1817" alt="Содержимое таблицы" src="https://github.com/user-attachments/assets/52e53527-01d1-44ab-9f66-ccfe41bbdc0c" /> | <img width="588" alt="Редактирование" src="https://github.com/user-attachments/assets/f0cf4136-c109-4a08-8a0c-a1ec931c2164" /> |
