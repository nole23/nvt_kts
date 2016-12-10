
var express = require('express');
var app  = express();
var bodyParser = require('body-parser');
var router1 = require('./app/addUserApp.js');
var router2 = require('./app/aplication.js');
var router3 = require('./app/app.js');
var router4 = require('./app/deleteUser.js');
var router5 = require('./app/exeptionApp.js');
var router6 = require('./app/index.js');
var router7 = require('./app/login.js');
var router8 = require('./app/user.js');
var router9 = require('./app/users.js');

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


app.use('/api', router1);
app.use('/api', router2);
app.use('/api', router3);
app.use('/api', router4);
app.use('/api', router5);
app.use('/api', router6);
app.use('/api', router7);
app.use('/api', router8);
app.use('/api', router9);

app.listen(port);
console.log('Server se slusa na portu ' + port);