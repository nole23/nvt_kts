var express = require('express');
var router = express.Router();

var Korisnik = require('./models/korisnik.js');

/**
 * @author XX
 * Login korisnika na sistem
 * kreiranje sessije koju koristiti kao notifikaciju
 */

router

    .post('/login/', function(req, res) {
        var pass = req.body.sifra;
        var mail = req.body.mail;

        Korisnik.findOne({mail: mail, sifra: pass}, function(err, korisnik) {
            if(err) {
                res.redirect('/login');
                return res.status(500).send(err);
            }
            if(!korisnik) {
                return res.status(404).send('Korisnik nije pronadjen u bazi');
            } else {
                console.log('uspjesno logovanje');
                req.session.user = korisnik;
                return res.status(200).send('Ulogovali ste se na sistem!');
                
            }
        })
    })


module.exports = router;