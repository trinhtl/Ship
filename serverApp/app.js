var express = require("express");
var http = require("http");
var app = express();
var server = http.createServer(app);
var io = require("socket.io").listen(server);


var bodyParser = require('body-parser');
var multer = require('multer');
var User = require('./models/user');
var Package = require('./models/package');
var Ship = require('./models/ship');

var status = ["wait", "recieved", "shipped"];
User.hasMany(Package, {foreignKey: 'idOwner'});
Package.belongsTo(User, {foreignKey: 'id'});
Ship.belongsTo(User, {foreignKey: 'idOwner'});
Ship.belongsTo(Package, {foreignKey: 'id'});

app.use(express.static('images'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

var index = require('./routes/index');
app.use('/', index);

io.on('connection', socket => {
    console.log('connection');
//user----------------------------------------------------------------------------------------------------------------------------
    socket.on('user/list', function (req, res){
    	console.log("list users");
    	User.findAll().then(users => {
    		console.log(users)
    		socket.emit('user/list', users)
    	});
    });//list user==================================================================================
    socket.on('user/add', (p1, p2, p3, p4, p5) => {
    	console.log("add user" + p1 + " " + p2 + " " + p3 + " " + p4  + " " + p5 );
    	User.create({
    		role: p1,
		    phone: p2,
		    name: p3,
		    email: p4,
		    avatar: p5
    	}).then(user => {
		    console.log(user.toJSON());
		});
    });//add user===================================================================================
    socket.on('user/update', (id, p1, p2, p3, p4, p5) => {
    	console.log('update user' + id + " " + p1 + " " + p2 + " " + p3 + " " + p4  + " " + p5)
    	User.update({
    		role: p1,
		    phone: p2,
		    name: p3,
		    email: p4,
		    avatar: p5
    	}, {
    		where: {id: id}
    	}).then(user => {
	    	console.log(user);
	    });
    });//update user==================================================================================
//package------------------------------------------------------------------------------------------------------------------------
   socket.on('package/list', function (req, res){
    	console.log("list packages");
    	Package.findAll().then(packages => {
    		console.log(packages)
    		socket.emit('package/list', packages)
    	});
    });//list package==================================================================================
   socket.on('package/add', (p1, p2, p3, p4, p5, p6) => {
   		console.log("add package " + p1 + " " + p2 + " " + p3 + " " + p4  + " " + p5 + " " + p6);
   		Package.create({
   			idOwner: p1,
	   		shipCost: p2,
	   		advanceMoney: p3,
	   		sendAddress: p4,
	   		recieveAddress: p5,
	   		description: p6
   		}).then(package => {
   			console.log("package add done: ", package)
   			Ship.create({
   				idPackage: package.id,
   				idShipper: null,
   				status: status[0],
   				shippedAt: null
   			}).then(ship => {
   				console.log("ship add done: ", ship)
   			})
   		});
   });//add package then ship===========================================================================
   socket.on('package/update/shipCost', (id, shipCost) => {
   		console.log("update package shipCost" + id +" " + shipCost);
   		Package.update({
	   		shipCost: shipCost
   		},{
   			where: {id: id}
   		}).then(package => {
   			console.log(package)
   		});
   });//update shipCost in a package=====================================================================
   socket.on('package/update/advanceMoney', (id, advanceMoney) => {
   		console.log("update package advanceMoney" + id +" " + advanceMoney);
   		Package.update({
	   		advanceMoney: advanceMoney
   		},{
   			where: {id: id}
   		}).then(package => {
   			console.log(package)
   		});
   });//update advanceMoney in a package==================================================================
   socket.on('package/update/sendAddress', (id, sendAddress) => {
   		console.log("update package sendAddress" + id +" " + sendAddress);
   		Package.update({
	   		sendAddress: sendAddress
   		},{
   			where: {id: id}
   		}).then(package => {
   			console.log(package)
   		});
   });//update sendAddress in a package===================================================================
   socket.on('package/update/recieveAddress', (id, recieveAddress) => {
   		console.log("update package recieveAddress" + id +" " + recieveAddress);
   		Package.update({
	   		recieveAddress: recieveAddress
   		},{
   			where: {id: id}
   		}).then(package => {
   			console.log(package)
   		});
   });//update recieveAddress in a package=================================================================
   socket.on('package/update/description', (id, description) => {
   		console.log("update package description" + id +" " + description);
   		Package.update({
	   		description: description
   		},{
   			where: {id: id}
   		}).then(package => {
   			console.log(package)
   		});
   });//update description in a package====================================================================
   socket.on('package/delete', (id) => {
    	console.log('delete package');
    	Ship.destroy({
    		where: {idShipper: id},
    		truncate: true
    	}).then(ship => {
    		console.log("delete ship done: ", ship)
    	});
    	Package.destroy({
    		where: {id: id},
    		truncate: true
    	}).then(package => {
    		console.log("delete package done: ", package);
    	});
    });//delete a package: if it has a ship, delete that ship first
//item---------------------------------------------------------------------------------------------------------------------------
   socket.on('item/list', function (req, res) {
   		console.log("items list");
		User.findAll({
		  include: [{
		    model: Package,
		    required: true
		   }]
		}).then(users => {
			console.log(users);
		  	socket.emit('item/list', users);
		});
   });//list all packages belonging to all users
//ship---------------------------------------------------------------------------------------------------------------------------
   socket.on('ship/update/idShipper', (id, idShipper) => {
   		console.log("update ship idShipper" + id +" " + idShipper);
   		Ship.update({
	   		idShipper: idShipper
   		},{
   			where: {id: id}
   		}).then(ship => {
   			console.log(ship)
   		});
   });//update idShipper when he/she  recieve that package
    socket.on('ship/update/status', (id, status) => {
   		console.log("update ship status" + id +" " + status);
   		Ship.update({
	   		status: status
   		},{
   			where: {id: id}
   		}).then(ship => {
   			console.log(ship)
   		});
   });//update status of a ship
    socket.on('ship/shipper/cancel', (id) => {
    	Ship.update({
	   		idShipper: null
   		},{
   			where: {id: id}
   		}).then(ship => {
   			console.log(ship)
   		});
    });//when a shipper want to cancel a package, set idShipper of that ship is null
});
server.listen(3000, ()=>{
	console.log("Node app is running on port 3000");
});