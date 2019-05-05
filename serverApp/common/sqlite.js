var Sequelize = require('sequelize');
var path = require('path');
var dbPath = path.resolve(__dirname, '../database/shipdb.db')
var sequelize = new Sequelize(`sqlite:${dbPath}`, {
	'dialect': 'sqlite',
	'storage': dbPath
});

module.exports = sequelize;