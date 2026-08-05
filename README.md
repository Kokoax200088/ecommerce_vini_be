# E-Commerce Vini & Alcolici - Backend

Documentazione del backend per il progetto E-Commerce dedicato alla vendita di vini e alcolici chiamato FineWine.

---

## Descrizione del Progetto

Il progetto consiste nello sviluppo di una piattaforma e-commerce focalizzata sulla vendita di vini ed alcolici di altro tipo. Il sistema gestisce il catalogo prodotti, gli ordini e le interazioni principali tipiche di un negozio online. Abbiamo un utendte admin che gestisce l eliminazione delle recensioni e l eliminazione utenti. L'utente cliente può aggiungere i prodotti al carrello e visualizzare sia i suoi ordini che lo stato delle spedizioni, può anche aggiungere una recensione o ad un alcolico oppure alla cantina. Il venditore può creare le cantine e i prodotti modificandoli ed eliminandoli.
L'utente non loggato può registrarsi ed effettuare il login e può visualizzare le cantine ed i prodotti senza poter aggiungere al carrello od aggiungere recensioni.

## Tecnologie & Strumenti

* **Linguaggio:** Java
* **Framework:** Spring Boot
* **Database Principale:** PostgreSQL (`db_ecommerce_vini`)
* **Database di Test/Sviluppo:** H2 (In-Memory)
* **Controllo Versione:** Git (gestito tramite l'interfaccia grafica **Sourcetree** per una migliore organizzazione dei branch e dei commit)

---

## Dipendenze del Progetto (Maven)

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

---

## Configurazione (`com.betacom.ec.configuration`)

Il package `configuration` raccoglie le classi `@Configuration` che impostano il comportamento trasversale dell'applicazione:

* **`SecurityConfig`**: definisce la `SecurityFilterChain` di Spring Security — quali endpoint sono pubblici (whitelist) e quali richiedono autenticazione, il filtro JWT nella catena, la policy di sessione (stateless) e l'`AuthenticationManager`.
* **`JwtConfiguration`**: contiene i bean/utility legati alla generazione, validazione e parsing dei token JWT (chiave di firma, scadenza, estrazione claims) usati dal filtro di sicurezza e dall'`AuthController`.
* **`WebConfiguration`**: configura il resource handler che espone la cartella locale delle immagini caricate (vini/alcolici/degustazioni) sul path `/images/**`, così da poterle servire come risorse statiche.
* **`OpenApiConfig`**: configura la generazione della documentazione OpenAPI/Swagger (info dell'API, eventuale schema di sicurezza Bearer per testare gli endpoint autenticati da `/swagger-ui.html`).

## Autenticazione (`AuthController`)

L'`AuthController` espone gli endpoint per la gestione della sessione utente basata su JWT:

* **Login**: valida le credenziali (email/password) tramite l'`AuthenticationManager` e restituisce access token (e refresh token).
* **Logout**: invalida/pulisce il token lato server o client, terminando la sessione autenticata.
* **Refresh**: dato un refresh token valido, emette un nuovo access token senza richiedere nuovamente le credenziali.

### `CustomUserDetailsService`

Classe di sicurezza (`com.betacom.ec.security.CustomUserDetailsService`) che implementa `UserDetailsService` di Spring Security ed è usata dall'`AuthenticationManager` durante il login:

1. Riceve l'email dell'utente (`loadUserByUsername`, dove "username" in questo progetto corrisponde all'email).
2. Cerca l'utente nel database tramite `IUtenteRepository.findByEmail(email)`; se non lo trova lancia `UsernameNotFoundException("login_invalid")`.
3. Costruisce e restituisce un oggetto `User` di Spring Security con username (email), password (hash) e il ruolo dell'utente convertito in maiuscolo (es. `ADMIN`, `USER`, `SELLER`), necessario perché Spring Security si aspetta i ruoli/authority in questo formato.

Questo servizio è il punto in cui Spring Security "sa" come recuperare e validare un utente durante l'autenticazione.
