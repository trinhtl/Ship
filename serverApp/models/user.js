var sequelize = require('../common/sqlite');
var Sequelize = require('sequelize');

var User = sequelize.define('user',{
	id: {
		type: Sequelize.INTEGER,
		autoIncrement: true,
		allowNull: false,
		primaryKey: true
	},
	name: {
		type: Sequelize.STRING,
		allowNull: true
	},
	phone: {
		type: Sequelize.STRING,
		allowNull: false,
		comment: '0000000000'
	},
	password: {
		type: Sequelize.STRING,
		allowNull: false
	},
	avatar: {
		type: Sequelize.STRING,
		allowNull: true,
		comment: './images/default.png',
		defaultValue: "a"
	},
	createdAt: {
		type: Sequelize.DATE,
		allowNull: true,
		comment: '2019-29-4 8:38:25'
	},
	updatedAt: {
		type: Sequelize.DATE,
		allowNull: true,
		comment: '2019-29-4 8:38:25'
	}
});
User.sync().then(function () {
	console.log('User is synced')
});
module.exports = User;