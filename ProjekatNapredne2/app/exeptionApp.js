var express = require('express');
var bodyParser = require('body-parser');
var mailer = require("nodemailer");

var router = express.Router();

var Aplikacija = require('./models/aplikacija.js');
var Korisnik = require('./models/korisnik.js');
var Izuzetak = require('./models/izuzetak.js');


router

    //Prijava greske i prvjera svih odgovornih
    //notifikacija za email
    .post('/:domen/greska', function(req, res) {
        Aplikacija.find({domen:req.params.domen}, function(err, kor) {
            if(err) {
                return res.status(404).send('Stranica je izgubljena probajte ponovo');
            }
            if(!kor){
                return res.status(500).send('Aplikacija ne postoji ili je izbirisana u medjuvremenu');
            } else {
                
                var dome = "localhost:8080/"+req.params.domen;

                var izuzetak = new Izuzetak({
                    izuzetak: req.body.izuzetak,
                    verzija: req.body.verzija,
                    vreme: dateDisplayed(Date.now()),
                    opis: req.body.opis,
                    fragment: req.body.fragment,
                    domen: req.params.domen,
                    aplikacija: [
                        kor._id
                    ]
                });

                var transporter = mailer.createTransport({
                    service: 'Gmail',
                    auth: {
                        user: 'nole0223@gmail.com',
                        pass: 'NoViCaNiKoLiC23'
                    }
                });

                var text = 'Desila se greska na '+kor.naziv;

                kor.forEach(function(mail) {
                    mail.odgovorni.forEach(function(item) {
                        //console.log(item);
                        Korisnik.findById(item, function(err, kori) {
                            if(err) {
                                console.log('greska');
                            }
                            if(!kori) {
                                console.log('greska');
                            } else {
                                console.log(kori.mail);
                                var email = kori.mail;
                                var mailOptions = {
                                    from: email,
                                    subject: 'Greska na sistemu',
                                    text: text
                                };

                                transporter.sendMail(mailOptions, function(error, info){
                                    if(error){
                                        console.log(error);
                                        res.json({yo: 'error'});
                                    }else{
                                        console.log('Message sent: ' + info.response);
                                        res.json({yo: info.response});
                                    };
                                });
                            }
                        })
                    })
                })
                

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

module.exports = router;

function dateDisplayed(timestamp) {
    var date = new Date(timestamp);
    return (date.getMonth() + 1 + '/' + date.getDate() + '/' + date.getFullYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
}