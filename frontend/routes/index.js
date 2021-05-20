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

router.get('/recetas/filtros', function(req, res, next) {
  res.render('filtros', { title: 'Recetas' });
});

router.get('/nutricionistas', function(req, res, next) {
  res.render('nutricionistas', { title: 'Nutricionistas' });
});

router.get('/login', function(req, res, next) {
  res.render('login', { title: 'Iniciar sesión' });
});

router.get('/panelUsuario', function(req, res, next) {
  res.render('panelUsuario', { title: 'Modificar datos' });
});

router.get('/misRecetas', function(req, res, next) {
  res.render('misRecetas', { title: 'Mis recetas' });
});

router.get('/crearReceta', function(req, res, next) {
  res.render('crearReceta', { title: 'Crear receta' });
});

router.get('/panelNutri', function(req, res, next) {
  res.render('panelNutri', { title: 'Nutricionistas' });
});

router.get('/recetas/ficha', function(req, res, next) {
  res.render('ficha', { title: 'Ficha receta' });
});

router.get('/fichaNutri', function(req, res, next) {
  res.render('fichaNutri', { title: 'Ficha nutricionista' });
});

router.get('/politica-privacidad', function(req, res, next) {
  res.render('politica-privacidad', { title: 'Política de privacidad' });
});

router.get('/contacto', function(req, res, next) {
  res.render('contacto', { title: 'Contacto' });
});


module.exports = router;
