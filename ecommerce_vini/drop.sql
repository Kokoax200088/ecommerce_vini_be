
    set client_min_messages = WARNING;

    alter table if exists alcolico 
       drop constraint if exists fk_alcolico_colore;

    alter table if exists alcolico 
       drop constraint if exists fk_alcolico_tipologia;

    alter table if exists alcolico 
       drop constraint if exists fk_alcolico_venditore;

    alter table if exists alcolico_caratteristica 
       drop constraint if exists FKek22r3y74kgayspy92pfkcw0s;

    alter table if exists alcolico_caratteristica 
       drop constraint if exists FK4jw36v1f6q4369nphoka9o4vq;

    alter table if exists box 
       drop constraint if exists fk_box_cantina;

    alter table if exists box_alcolico 
       drop constraint if exists fk_boxAlcolico_alcolico;

    alter table if exists box_alcolico 
       drop constraint if exists fk_boxAlcolico_box;

    alter table if exists cantina 
       drop constraint if exists fk_cantina_venditore;

    alter table if exists cantina_alcolico 
       drop constraint if exists fk_cantina_alcolico_bottiglia;

    alter table if exists cantina_alcolico 
       drop constraint if exists fk_cantina_alcolico;

    alter table if exists carrello 
       drop constraint if exists fk_carrello_cliente;

    alter table if exists cliente 
       drop constraint if exists fk_cliente_carrello;

    alter table if exists cliente 
       drop constraint if exists fk_utente_cliente;

    alter table if exists degustazione 
       drop constraint if exists fk_degustazione_cantina;

    alter table if exists degustazione_alcolico 
       drop constraint if exists FKpwbgxed2q41fl12cxr1qh501c;

    alter table if exists degustazione_alcolico 
       drop constraint if exists FKglcsfyqrpma7jss24wbc9krmq;

    alter table if exists immagine_alcolico 
       drop constraint if exists fk_immagine_alcolico;

    alter table if exists immagine_box 
       drop constraint if exists fk_immagine_box;

    alter table if exists immagine_cantina 
       drop constraint if exists fk_immagine_cantina;

    alter table if exists immagine_degustazione 
       drop constraint if exists fk_immagine_degustazione;

    alter table if exists ordine 
       drop constraint if exists fk_ordine_status;

    alter table if exists ordine 
       drop constraint if exists fk_ordine_utente;

    alter table if exists ordine_alcolico 
       drop constraint if exists fk_ordine_alcolicoo;

    alter table if exists ordine_alcolico 
       drop constraint if exists fk_ordine_alcolico_cantina;

    alter table if exists ordine_alcolico 
       drop constraint if exists fk_ordine_alcolico_ordine;

    alter table if exists ordine_alcolico 
       drop constraint if exists fk_status_ordine_alcolico;

    alter table if exists ordine_box 
       drop constraint if exists fk_ordine_box;

    alter table if exists ordine_box 
       drop constraint if exists fk_ordine_boxo_cantina;

    alter table if exists ordine_box 
       drop constraint if exists fk_ordine_box_ordine;

    alter table if exists ordine_box 
       drop constraint if exists fk_status_ordine_box;

    alter table if exists ordine_degustazione 
       drop constraint if exists fk_ordine_degustazione_cantina;

    alter table if exists ordine_degustazione 
       drop constraint if exists fk_ordine_degustazione;

    alter table if exists ordine_degustazione 
       drop constraint if exists fk_ordine_degustazione_ordine;

    alter table if exists ordine_degustazione 
       drop constraint if exists fk_status_ordine_degustazione;

    alter table if exists prenotazione_degustazione 
       drop constraint if exists fk_prenotazione_degustazione_cantina;

    alter table if exists prenotazione_degustazione 
       drop constraint if exists fk_prenotazione_degustazione_degustazione;

    alter table if exists prenotazione_degustazione 
       drop constraint if exists fk_prenotazione_degustazione_ordine;

    alter table if exists prenotazione_degustazione 
       drop constraint if exists fk_prenotazione_degustazione_status;

    alter table if exists prodotto_alcolico 
       drop constraint if exists fk_prodotto_alcolico_alcolico;

    alter table if exists prodotto_alcolico 
       drop constraint if exists fk_prodotto_alcolico_cantina;

    alter table if exists prodotto_alcolico 
       drop constraint if exists fk_prodotto_alcolico_carrello;

    alter table if exists prodotto_box 
       drop constraint if exists fk_prodotto_box_box;

    alter table if exists prodotto_box 
       drop constraint if exists fk_prodotto_box_cantina;

    alter table if exists prodotto_box 
       drop constraint if exists fk_prodotto_box_carrello;

    alter table if exists prodotto_degustazione 
       drop constraint if exists fk_prodotto_degustazione_cantina;

    alter table if exists prodotto_degustazione 
       drop constraint if exists fk_prodotto_degustazione_carrello;

    alter table if exists prodotto_degustazione 
       drop constraint if exists fk_prodotto_degustazione_degustazione;

    alter table if exists rating_alcolico 
       drop constraint if exists fk_rating_alcolico;

    alter table if exists rating_alcolico 
       drop constraint if exists fk_rating_cantina;

    alter table if exists rating_alcolico 
       drop constraint if exists fk_rating_alcolico_utente;

    alter table if exists rating_cantina 
       drop constraint if exists fk_cantina_rating;

    alter table if exists rating_cantina 
       drop constraint if exists fk_rating_cantina_utente;

    alter table if exists spedizione_alcolico 
       drop constraint if exists FK4fumiy82ibpvyhlt5pf4x3237;

    alter table if exists spedizione_alcolico 
       drop constraint if exists FK3k9hot3ra92o46r4s26u89l8;

    alter table if exists spedizione_alcolico 
       drop constraint if exists fk_spedizione_ordine_alcolico;

    alter table if exists spedizione_alcolico 
       drop constraint if exists fk_status_ordine;

    alter table if exists spedizione_box 
       drop constraint if exists FKtm9ier84fy9iix8kn61eohk5l;

    alter table if exists spedizione_box 
       drop constraint if exists FKs1qmur4y1mdq2cxcfjx3wl5v0;

    alter table if exists spedizione_box 
       drop constraint if exists fk_spedizione_ordine_box;

    alter table if exists spedizione_box 
       drop constraint if exists fk_status_spedizione_box;

    alter table if exists utente 
       drop constraint if exists fk_utente_ruolo;

    alter table if exists venditore 
       drop constraint if exists fk_utente_venditore;

    drop table if exists alcolico cascade;

    drop table if exists alcolico_caratteristica cascade;

    drop table if exists box cascade;

    drop table if exists box_alcolico cascade;

    drop table if exists cantina cascade;

    drop table if exists cantina_alcolico cascade;

    drop table if exists caratteristica cascade;

    drop table if exists carrello cascade;

    drop table if exists cliente cascade;

    drop table if exists colore cascade;

    drop table if exists degustazione cascade;

    drop table if exists degustazione_alcolico cascade;

    drop table if exists immagine_alcolico cascade;

    drop table if exists immagine_box cascade;

    drop table if exists immagine_cantina cascade;

    drop table if exists immagine_degustazione cascade;

    drop table if exists messaggi_sistema cascade;

    drop table if exists ordine cascade;

    drop table if exists ordine_alcolico cascade;

    drop table if exists ordine_box cascade;

    drop table if exists ordine_degustazione cascade;

    drop table if exists prenotazione_degustazione cascade;

    drop table if exists prodotto_alcolico cascade;

    drop table if exists prodotto_box cascade;

    drop table if exists prodotto_degustazione cascade;

    drop table if exists rating_alcolico cascade;

    drop table if exists rating_cantina cascade;

    drop table if exists role cascade;

    drop table if exists spedizione_alcolico cascade;

    drop table if exists spedizione_box cascade;

    drop table if exists status cascade;

    drop table if exists tipologia_alcolico cascade;

    drop table if exists utente cascade;

    drop table if exists venditore cascade;
