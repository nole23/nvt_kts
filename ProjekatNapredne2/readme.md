# MongoDB i Mongoose

Aplikacija je jednostavan REST back end za projekat

## API

* `GET api/` - pocetna stranica sajta
* `POST api/login/` - logovanje vec postojec korisnika
* `POST api/user/` - registracija novog korisnika
* `DELETE api/user/:id` - brisanje odredjenog korisnika po id
* `GET api/users/` - prikaz svih korisnika
* `POST api/aplication` - dodavanje nove aplikacije
* `GET api/:domen` - prikaz podataka odredjene aplikacije
* `PUT api/:domen/user` - dodavanje novog odgovornog korisnika za datu aplikaciju
* `POST api/:domen/greska` - hvatanje greske sa odredjene aplikacije





* `PUT api/odgovorni/:domen` - Dodavanje novog odgovornog za odredjen domen
* `GET api/:domen` - prizak svih informacija o apliakciji
* `GET api/korisnik` - prikaz svih registrovanih korisnika


## Struktura aplikacije

U folderu `app/model` nalaze se modeli - blogEntry i comment.


## Pokretanje aplikacije

1. pokrenuti `npm install`
2. pokrenuti `npm install express`
3. pokrenuti `npm install mongoose`
4. pokrenuti MongoDB u lokalu
5. pokretati aplikacije pomoću `node app.js`
