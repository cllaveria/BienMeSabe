var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'BienMeSabe' });
});

router.get('/registro', function(req, res, next) {
  res.render('registro', { title: 'Registro' });
});

router.get('/recetas', function(req, res, next) {
  res.render('recetas', { title: 'Recetas' });
});

router.get('/login', function(req, res, next) {
  res.render('login', { title: 'Iniciar sesión' });
});

module.exports = router;
