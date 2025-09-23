# Aplikacja E-commerce "Perfumery" w Spring Boot

## O Projekcie 📖

**Perfumery** to w pełni funkcjonalna, nowoczesna aplikacja webowa typu e-commerce, zbudowana od zera w Javie z wykorzystaniem frameworka Spring Boot. Projekt demonstruje kompleksowe podejście do tworzenia aplikacji backendowych, od modelowania danych i logiki biznesowej, przez zaawansowane zabezpieczenia, aż po konteneryzację z użyciem Dockera.

Aplikacja symuluje działanie niszowej perfumerii, implementując kluczowe procesy biznesowe, takie jak zarządzanie katalogiem produktów, obsługa koszyka, składanie zamówień i panel administracyjny.

---

## Stos Technologiczny 🛠️

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)

* **Backend:** Java 17+, Spring Boot 3
* **Dane:** Spring Data JPA, Hibernate, PostgreSQL, H2 (dla testów)
* **Bezpieczeństwo:** Spring Security 6
* **Frontend:** Thymeleaf, Bootstrap 5
* **API:** Spring Web (REST Controllers)
* **Wdrożenie:** Docker, Docker Compose
* **Narzędzia:** Maven, Lombok

---

## Kluczowe Funkcjonalności ✨

Projekt implementuje szereg zaawansowanych funkcjonalności kluczowych dla nowoczesnych aplikacji webowych:

* **Architektura Warstwowa:** Zachowano czysty podział na warstwy Controller, Service i Repository.
* **Bezpieczeństwo Oparte na Rolach (RBAC):**
    * Implementacja **Spring Security** od zera.
    * Rozróżnienie ról: `Gość`, `ROLE_USER`, `ROLE_ADMIN`.
    * Bezpieczna rejestracja i logowanie z haszowaniem haseł **BCrypt**.
    * Ochrona endpointów na poziomie metod i adresów URL.
    * Funkcjonalność **"Zapamiętaj Mnie"** oparta na trwałych tokenach w bazie danych.
* **Panel Administratora:**
    * Pełna obsługa **CRUD** (Create, Read, Update, Delete) dla kluczowych encji (Produkty, Marki).
    * Możliwość przeglądania wszystkich zamówień i zarządzania ich statusami.
    * **Dynamiczny Dashboard** pobierający dane asynchronicznie z wewnętrznego API REST.
* **Logika E-commerce:**
    * Koszyk na zakupy oparty o **sesję HTTP**.
    * Transakcyjny proces składania zamówień (`@Transactional`).
    * **Powiadomienia e-mail** po złożeniu zamówienia (integracja ze Spring Mail).
* **Zaawansowana Walidacja:**
    * Walidacja danych przychodzących po stronie serwera (`@Valid`).
    * Stworzenie i implementacja **własnej adnotacji walidacyjnej** do sprawdzania zgodności haseł.
* **API REST:**
    * Publiczne i zabezpieczone endpointy REST do udostępniania danych w formacie JSON.
* **Konteneryzacja:**
    * Aplikacja i baza danych w pełni skonfigurowane do uruchomienia za pomocą **Docker Compose**, co zapewnia spójność środowisk.

---

## Uruchomienie Projektu 🚀

Do uruchomienia aplikacji lokalnie wymagany jest zainstalowany **Docker** i **Docker Compose**.

1.  Sklonuj repozytorium na swój komputer:
    ```bash
    git clone [https://github.com/adamdobrogowski/spring-boot-perfumery-shop.git](https://github.com/adamdobrogowski/spring-boot-perfumery-shop.git)
    ```
2.  Przejdź do głównego folderu projektu:
    ```bash
    cd spring-boot-perfumery-shop
    ```
3.  Uruchom całe środowisko za pomocą jednej komendy:
    ```bash
    docker-compose up --build
    ```
4.  Aplikacja będzie dostępna pod adresem `http://localhost:8080`.

**Dane logowania administratora:**
* **E-mail:** `admin@gmail.com`
* **Hasło:** `admin123`

**Dane logowania przykładowego użytkownika:**
* **E-mail:** `user@gmail.com`
* **Hasło:** `haslo123`

---