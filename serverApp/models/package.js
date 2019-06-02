var sequelize = require('../common/sqlite');;
var Sequelize = require('sequelize');
var User = require('../models/user');
var Package = sequelize.define('package', {
	id: {
		type: Sequelize.INTEGER,
		allowNull: false,
		primaryKey: true,
		autoIncrement: true
	},
	idOwner: {
		type: Sequelize.INTEGER,
		allowNull: false,
		references: {
	      model: User,
	      key: 'id'
	    }
	},
	shipCost: {
		type: Sequelize.STRING,
		allowNull: false
	},
	advanceMoney: {
		type: Sequelize.STRING,
		allowNull: true
	},
	sendAddress: {
		type: Sequelize.STRING,
		allowNull: false
	},
	recieveAddress: {
		type: Sequelize.STRING,
		allowNull: false
	},
	description: {
		type: Sequelize.STRING,
		allowNull: true
	},
	createdAt: {
		type: Sequelize.DATE,
		allowNull: true
	},
	updatedAt: {
		type: Sequelize.DATE,
		allowNull: true
	}
});
Package.sync().then(function () {
	console.log("Package is synced")
});

module.exports = Package;
