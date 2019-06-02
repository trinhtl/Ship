var sequelize = require('../common/sqlite');
var Sequelize = require('sequelize');
var Package = require('../models/package');
var User = require('../models/user');
var Ship = sequelize.define('ship',{
	id: {
		type: Sequelize.INTEGER,
		autoIncrement: true,
		allowNull: false,
		primaryKey: true
	},
	idPackage: {
		type: Sequelize.INTEGER,
		allowNull: true,
		references: {
	      model: Package,
	      key: 'id'
	    }
	},
	idShipper: {
		type: Sequelize.INTEGER,
		allowNull: true,
		references: {
			model: User,
			key: 'id'
		}
	},
	status: {
		type: Sequelize.STRING,
		allowNull: false
	},
	shippedAt: {
		type: Sequelize.DATE,
		allowNull: true
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
Ship.sync().then(function () {
	console.log('Ship is synced')
});
module.exports = Ship;