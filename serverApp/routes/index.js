var express = require('express');

var router = express.Router();

var user = require('../controllers/user');
var package = require('../controllers/package');
var item = require('../controllers/item');

router.get('/user/list', user.list);
router.get('/package/list', package.list);

module.exports = router;