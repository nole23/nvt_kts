
var express = require('express');
var app  = express();
var bodyParser = require('body-parser');
var router = require('./app/app.js');

var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost:27017/Napredne');

var session = require('express-session');

//konfiguracija aplikacije da koristi bodyParser
//sto ce nam omoguciti da dobijamo podatke iz POST zahteva
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.use(session({
    secret: '2C44-4D44-WqpQ38s',
    resave: true,
    saveUninitialized: true
}))
//Port na kome se slusa server
var port = process.env.PORT || 8080;

app.use('/api', router);


app.listen(port);
console.log('Server se slusa na portu ' + port);