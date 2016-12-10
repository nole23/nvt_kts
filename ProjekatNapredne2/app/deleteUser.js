var express = require('express');
var bodyParser = require('body-parser');

var router = express.Router();

var Aplikacija = require('./models/aplikacija.js');
var Korisnik = require('./models/korisnik.js');
var Izuzetak = require('./models/izuzetak.js');

router


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

module.exports = router;