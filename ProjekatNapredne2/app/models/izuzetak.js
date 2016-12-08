var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var IzuzetakSchema = new Schema({
    izuzetak: String,
    verzija: String,
    vreme: String,
    opis: String,
    fragment: String,
    odgovorni: [String],
    domen: String
});

module.exports = mongoose.model('Greska', IzuzetakSchema);