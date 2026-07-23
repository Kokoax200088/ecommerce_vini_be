# E-Commerce Vini & Alcolici - Backend

Documentazione del backend per il progetto E-Commerce dedicato alla vendita di vini e alcolici.

---

##  Descrizione del Progetto
Il progetto consiste nello sviluppo di una piattaforma e-commerce focalizzata sulla vendita di vini ed alcolici di altro tipo. Il sistema gestisce il catalogo prodotti, gli ordini e le interazioni principali tipiche di un negozio online.

##  Tecnologie & Strumenti
* **Linguaggio:** Java
* **Framework:** Spring Boot
* **Database Principale:** PostgreSQL (`db_ecommerce_vini`)
* **Database di Test/Sviluppo:** H2 (In-Memory)
* **Controllo Versione:** Git (gestito tramite l'interfaccia grafica **Sourcetree** per una migliore organizzazione dei branch e dei commit)

---

##  Dipendenze del Progetto (Maven)

Il progetto utilizza i seguenti moduli e librerie principali, configurati nel file `pom.xml`:

### Core & Web
* `spring-boot-starter-webmvc`: Per la creazione di API REST e la gestione del pattern MVC.
* `spring-boot-starter-webclient`: Per effettuare chiamate HTTP asincrone/sincrone verso servizi esterni.
* `spring-boot-starter-validation`: Per la validazione dei dati in ingresso (es. `@NotNull`, `@Size`).
* `lombok`: Per ridurre il codice boilerplate (generazione automatica di Getter, Setter, Costruttori).
* `spring-boot-devtools`: Strumenti di sviluppo per il ricaricamento automatico dell'applicazione (LiveReload).

### Data & Database
* `spring-boot-starter-data-jpa`: Per l'integrazione con l'ORM Hibernate e la gestione dei database relazionali.
* `spring-boot-starter-data-jdbc`: Per l'accesso ai dati tramite JDBC.
* `postgresql`: Driver per la connessione al database di produzione/sviluppo locale PostgreSQL.
* `h2` & `spring-boot-h2console`: Database in-memory e relativa console web, ideale per prototipazione rapida e test.

### IMPOSTAZIONI Environment
* db_pwd=(vostra pwd)
* db_url=jdbc:postgresql://localhost:5432/db_ecommerce_vini
* db_user=postgres

### Testing
Il progetto include i moduli di test specifici per garantire la solidità di ogni livello dell'applicazione:
* `spring-boot-starter-data-jpa-test` & `spring-boot-starter-data-jdbc-test` (Test del livello di persistenza)
* `spring-boot-starter-validation-test` (Test delle regole di validazione)
* `spring-boot-starter-webclient-test` & `spring-boot-starter-webmvc-test` (Test dei controller e dei client HTTP)
