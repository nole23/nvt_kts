var express = require('express');
var bodyParser = require('body-parser');

var router = express.Router();

var Aplikacija = require('./models/aplikacija.js');
var Korisnik = require('./models/korisnik.js');
var Odgovorni = require('./models/odgovorni.js');
var Izuzetak = require('./models/izuzetak.js');

router

    //pocetna stranica
    .get('/', function(req, res) {
        if(req.session.user === null){
            console.log('greska')
        }
        //console.log('korisnik '+req.session.user);
        res.json({message1: 'Ako zelite da se ulogujete: www.localhost:8080/api/login',
                  message2: 'Ako zelite da se registrujete: www.localhost:8080/api/registration'});
        //res.json({message: 'Ako zelite da se registrujete: www.localhost:8080/api/registration'});
    })

    //Dodavanje novog korisnika
    .post('/user/', function(req, res){
        var fname = req.body.ime;
        var lname = req.body.prezime;
        var pass = req.body.sifra;
        var mail = req.body.mail;

        var user = new Korisnik();
        user.ime = fname;
        user.prezime = lname;
        user.sifra = pass;
        user.mail = mail;
        user.save(function(err, saveUser){
            if(err) {
                console.log(err);
                return res.status(500).send();
                
            }
            console.log('uspjelo je ');
            res.json({message: 'Uspjesno ste se registrovali sada se ulogujete'})      
            return res.status(200).send();  
        })
    })
    //login korisnika na sistem i cuvanje njegove sesije 
    //radi autenticnosti korisnika
    .post('/login/', function(req, res) {
        var pass = req.body.sifra;
        var mail = req.body.mail;

        Korisnik.findOne({mail: mail, sifra: pass}, function(err, korisnik) {
            if(err) {
                console.log(err);
                res.redirect('/login');
                return res.status(500).send();
            }
            if(!korisnik) {
                console.log('ne postojo korisnik');
                res.redirect('/login');
                return res.status(404).send();
            } else {
                console.log('uspjesno logovanje');
                req.session.user = korisnik;
                
                res.json({message: 'Ulogovani ste mozete videti za koje ste aplikacije odgovorni'});
                
            }
        })
    })
    //dodavanje nove aplikacije, moze samo registrovan korisnik
    //i on automatski postaje odgovoran za tu aplikaciju
    .post('/aplication', function(req, res) {
        if (!req.session.user) {
            res.send('Morate se se prijaviti da bi nastavili sa radom');
            res.redirect('/login');
        } else {
            var proba = new Aplikacija({

                naziv: req.body.naziv,
                opis: req.body.opis,
                verzija: req.body.verzija,
                link: req.body.link,
                domen: req.body.domen,
                odgovorni: [{
                    idOdgovornog: req.session.user
                }]
            });
            proba.save( function(err, document){
                if(err) {
                    console.log('Greska na serveru pri cuvanju');
                    //moze se iskorititi za gresku aplikacije
                }
                res.json({"message":"Sacuvana je nova aplikacija "+req.body.naziv});
            });
        }
    })

    /*
    "izuzetak":"neki",
	"verzija":"7.2",
	"opis":"ne postoji korisnik",
	"fragment":"baza"
     */
    //dodavanje novog odgovornog za datu aplikaciju
    .put('/:domen/user', function(req, res, next) {
        if(!req.session.user) {

        } else {
            Korisnik.findOne({mail:req.body.mail}, function(err, kor) {
                if(err) {
                    console.log('ne valja1');
                }
                if(!kor) {
                    console.log('ne valja2');

                } else {
                    Aplikacija.update({"domen":req.params.domen}, {$push: {"odgovorni": {"idOdgovornog": kor._id}}}, function(err, proba) {
                        if(err) {
                            console.log('ne valja3');
                        }
                        if(!proba) {
                            console.log('ne valja4');
                            //iskoristiti za gresku
                        } else {
                            console.log('sacuvano');
                            res.json({message:"Sacuvan je "+kor.ime+" je dodat kao odgovoran za apliaciju "+req.params.domen});
                        }
                    })
                }
            })
        }
    })

    //Prikaz aplikacije
    .get('/:domen', function(req, res) {
        Aplikacija.find({domen:req.params.domen}, function(err, proba) {
            
            if(err) {
                console.log('ne valja1');
            }
            if(!proba) {
                console.log('ne valja2');
            } else {
                
                
                proba.forEach(function(nesto) {
                    nesto.odgovorni.forEach(function(item) {
                        
                            console.log(item.idOdgovornog);

                            Korisnik.findById(item.idOdgovornog, function(err, kori) {
                                if(err){
                                    console.log("greska")
                                }
                                if(!kori) {
                                    console.log("greska2")
                                }else{
                                    console.log({odgovorni: kori.ime})
                                    
                                }
                            })
                        
                    })
                })
                res.json(proba);
            }
        })
    })

    //svi korisnici
    .get('/users/', function(req, res) {
        Korisnik.find({}, function(err, kori) {
            res.json(kori);
        })
    })

    //Prijava greske i prvjera svih odgovornih
    .post('/:domen/greska', function(req, res) {
        Aplikacija.find({domen:req.params.domen}, function(err, kor) {
            if(err) {
                console.log('greska na serveru');
            }
            if(!kor){
                console.log('greska sa bazom');
            } else {
                var id = [];
                kor.forEach(function(nesto){
                    nesto.odgovorni.forEach(function(item) {
                        id.push(item.idOdgovornog);
                    })
                })

                var dome = "localhost:8080/"+domen;

                var izuzetak = new Izuzetak();
                izuzetak.izuzetak = req.body.izuzetak;
                izuzetak.verzija = req.body.verzija;
                izuzetak.vreme = dateDisplayed(Date.now());
                izuzetak.opis = req.body.opis;
                izuzetak.fragment = req.body.fragment;
                izuzetak.domen = req.params.domen;
                izuzetak.korisnik = id;
                izuzetak.link = dome;

                izuzetak.save(function(err, saveErr) {
                    if(err) {
                        res.status(500).send();
                    }
                    res.json({message1: "desila se greska na serveru"});
                    res.status(200).send();
                })      
            }
        })
    })
    .delete('user/:id', function(req, res) {
        if(!session.req.user){
            res.json('Niste prijavljeni');
        } else {
            var mojId = req.session.user._id;
            if(req.params.id != mojId) {
                Korisnik.remove({ _id: id}, function(err, mess) {
                    if(err) {
                        console.log('greska na serveru');
                    } else {
                        res.json('korisnik je obrisan');
                    }
                })
            } else {
                res.json('ne mozete sami sebe obrisati, mozete samo deaktivirati profil');
            }
        }
    })



/*
	"mail":"nole0223@gmail.com",
	"sifra":"123"
*/
module.exports = router;

function dateDisplayed(timestamp) {
    var date = new Date(timestamp);
    return (date.getMonth() + 1 + '/' + date.getDate() + '/' + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
}