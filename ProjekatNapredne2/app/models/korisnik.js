var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var KorisnikSchema = new Schema({
    ime: String,
    prezime: String,
    sifra: String,
    mail: String

});

module.exports = mongoose.model('Korisnik', KorisnikSchema);