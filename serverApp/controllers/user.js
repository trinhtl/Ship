var User = require('../models/user');

exports.list = (req, res, next) => {
	User.findAll().then(user => {
		res.send(user)
	});
}