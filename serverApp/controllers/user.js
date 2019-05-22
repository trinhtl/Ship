var User = require('../models/user');

exports.list = (req, res, next) => {
	User.findAll().then(user => {
		res.send(user)
	});
}
exports.insert = (req, res, next) => {
	User.create({
	    role: req.role,
	    phone: req.phone,
	    name: req.name,
	    email: req.email,
	    avatar: req.avatar
	  })
	  .then(user => {
	    console.log(user.toJSON());
	  });
}
// exports.update = (req, res, next) => {
// 	User.update
// }
// exports.delete = 
// exports.getUserById =  