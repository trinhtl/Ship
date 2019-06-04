var express = require("express");
var http = require("http");
var app = express();
var server = http.createServer(app);
var io = require("socket.io").listen(server);


var bodyParser = require('body-parser');
var multer = require('multer');
var User = require('./models/user');
var Package = require('./models/package');

var status = ["wait", "recieved", "shipped"];
User.hasMany(Package, {foreignKey: 'idOwner'});
Package.belongsTo(User, {foreignKey: 'id'});

app.use(express.static('images'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

var Sequelize = require('sequelize');

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
    socket.on('user/validate', (phone, password) => {
    	User.findOne({
    		where: {phone: phone, password: password}
    	}).then(user => {
    		console.log(user);
    		socket.emit('user/validate', user);
    	});
    });
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
	   		description: p6,
	   		idShipper: null,
	   		status: status[0],
	   		shippedAt: null
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
    	Package.destroy({
    		where: {id: id},
    		truncate: true
    	}).then(package => {
    		console.log("delete package done: ", package);
    	});
    });//delete a package: if it has a ship, delete that ship first==========================================
   socket.on('package/getByIdShipper', idShipper => {
   		console.log("find package belong to this person id: " + idShipper);
   		Package.findAll({
   			where: {idShipper: idShipper, status: status[1]},
		    include: [{
		        model: User,
		        where: { idOwner: Sequelize.col('user.id')}
		    }]
		}).then(packages => {
   			console.log(packages);
   			socket.emit('package/getByIdShipper', packages);
   		});
   });
   socket.on('package/getByIdShop', idShop => {
   		console.log("find package belong to this person id: " + idShop);
   		Package.findAll({
   			where: {idOwner: idShop, status: status[0]}
		}).then(packages => {
   			console.log(packages);
   			socket.emit('package/getByIdShop', packages);
   		});
   });
//item---------------------------------------------------------------------------------------------------------------------------
   socket.on('item/list', function (req, res) {
   		console.log("items list");
		User.findAll({
		  include: [{
		    model: Package,
		    where: {status: "wait"},
		    required: true
		   }]
		}).then(users => {
			console.log(users);
		  	socket.emit('item/list', users);
		});
   });//list all packages belonging to all users=============================================================
//ship---------------------------------------------------------------------------------------------------------------------------
  
    socket.on('ship/shipped', (idPackage, shippedAt) => {
   		console.log("update ship status" + idPackage +" " + status[2] + " " + shippedAt);
   		Ship.update({
	   		status: status[2],
	   		shippedAt: shippedAt
   		},{
   			where: {id: idPackage}
   		}).then(package => {
   			console.log(package)
   		});
   });//update status of a package=============================================================================
    socket.on('ship/shipper/cancel', (idPackage) => {
    	Package.update({
	   		idShipper: null,
	   		status: status[0]
   		},{
   			where: {id: idPackage}
   		}).then(package => {
   			console.log(package)
   		});
    });//when a shipper want to cancel a package, set idShipper of that package is null, status is wait========================
    socket.on('ship/recieve', (idPackage, idShipper) => {
   		console.log("update ship status" + idPackage +" " + idShipper + " " + status[1]);
   		Package.update({
   			idShipper: idShipper,
	   		status: status[1]
   		},{
   			where: {id: idPackage}
   		}).then(package => {
   			console.log(package)
   		});
   	});//=============================================================================================

});

server.listen(3000, ()=>{
	console.log("Node app is running on port 3000");
});