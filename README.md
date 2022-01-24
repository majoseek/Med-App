# PAP21Z-Z14

## Temat

Aplikacja webowa - przychodnia lekarska

## Opis

Aplikacja webowa obsługująca przychodnię lekarską, która ma na celu zautomatyzowanie procesu umawiania wizyt oraz wystawiania recept. Dostępny również panel dla wszystkich pacjentów pozwalający na przeglądanie wypisanych recept oraz nadchodzących wizyt.

### Jak to działa?
Użytkownik rejestruje się do systemu podając swoje dane osobowe oraz e-mail i hasło. Stworzony jest osoby panel dla lekarzy i pacjentów. Każdy z nich posiada inne funkcjonalności.

### Prezentacja projektu


W celu przetestowania projektu należy sklonować repozyotrium np. przy użyciu polecenia `git clone`. Następnie należy zbudować i uruchomić kontenery odpowiedzialne za wszystkie serwisy
projektu. Aby to zrobić należy w głownym katalogu użyć polecenia `docker-compose up --build -d` (należy posiadać zainstalowany pakiet docker-compose). Po pomyślnym pobraniu wymaganych
obrazów i ich zbudowaniu, kontenery uruchomią się automatycznie. Projekt został podzielony na 4 kontnery:
-   postgres - zawiera bazę danych wymaganą przez menedżer uwierzytelniający Keycloak
-   keycloak - menedżer uwierzytelniający pozwalający na autoryzacje i uwierzytelnienie użytkowników aplikacji
-   springAPI - serwer będący interfejsem programistycznym dostarczającym dane do aplikacji
-   web - część aplikacji odpowiedzialna za interakcję z użytkownikiem

Aby sprawdzić działanie aplikacji należy wejść przez przeglądarkę na adres `http://localhost/`, na którym powinna powitać nas strona domowa. Z jej poziomu możemy przejść do różnych części strony, w tym także do logowania i rejestracji. 

## Funkcjonalnosci

### Pacjent

-   wyświetlanie i edycja danych osobistych
-   umawianie wizyty
-   dostęp do historii medycznej (diagnozy oraz przebyte choroby)
-   powiadomienia przypominające o nadchodzących wizytach
-   dostęp do wystawionych e-recept
-   edycja terminu wizyt

### Lekarz

-   wgląd do historii medycznej pacjentów
-   edycja historii medycznej pacjentów
-   harmonogram wizyt
-   ustalanie harmonogramów wizyt
-   wystawianie recept

## Model bazy danych

<img src="./SQL_MODEL.png"/>

## Stack technologiczny

### Front-end

-   React.js
-   Material-UI
-   Bootstrap 5

### Back-end

-   Java
-   Spring Boot 2
-   Hibernate

### Baza danych

-   Oracle Database

### Narzędzia

-   SQL Developer
-   Docker
-   Keycloak

## UWAGA 
Baza danych aplikacji jest połączona z zewnętrznym serwerem bazy danych, więc każda rejestracja doda zarówno rekord w niej oraz w wewnętrznej bazie używanej do identyfikacji oraz uwierzytelniania, która przy każdym tworzeniu kontenera jest resetowana. W takim przypadku dodane rekordy użytkowników zostają w zewnętrznej bazie danych jednak przy kolejnym zbudowaniu kontenera nie pozwolą na zalogowanie się. Do bazy danych zostali dodani przykładowi użytkownicy, którzy przy pierwszym uruchomieniu zostają automatycznie zaimportowani również do wewnętrznej bazy danych Keycloaka. W pliku `testUsers.txt` znajdują się dane losowych użytkowników potrzebne do zalogowania i przestestowania działania aplikacji.
