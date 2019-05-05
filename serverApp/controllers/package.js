var Package = require('../models/package');

exports.list = (req, res, next) => {
	Package.findAll().then(package => {
		res.send(package)
	});
}