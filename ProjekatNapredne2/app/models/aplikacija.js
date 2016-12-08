var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var AplikacijaSchema = new Schema({
    naziv: String,
    opis: String,
    verzija: String,
    link: String,
    domen: {
        type: String,
        unique: true
    },
    odgovorni: [{
        idOdgovornog: {
            type: String,
            unique: true
        }
    }]

});

module.exports = mongoose.model('Aplikacija', AplikacijaSchema);
