var express = require('express');

var router = express.Router();

var user = require('../controllers/user');
var package = require('../controllers/package');
var item = require('../controllers/item');
var ship = require('../controllers/ship');

router.get('/user/list', user.list);
router.get('/package/list', package.list);
router.get('/', user.list);
module.exports = router;