var express = require('express');
var router = express.Router();

var Korisnik = require('./models/korisnik.js');

/**
 * @author
 * Prikaz svih registrovanih korisnika
 */

router

    
    .get('/users/', function(req, res) {
        Korisnik.find({}, function(err, kori) {
            if(err) {
                console.log('greska1');
            }
            if(!kori) {
                console.log('greska2');
            }
            console.log(kori)
            res.json(kori);
        })
    })


module.exports = router;