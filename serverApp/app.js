var express = require("express");
var http = require("http");
var app = express();
var server = http.createServer(app);
var io = require("socket.io").listen(server);

var index = require('./routes/index');
var bodyParser = require('body-parser');
var multer = require('multer');
var User = require('./models/user');
var Package = require('./models/package');
app.use(express.static('images'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

app.use('/', index);

io.on('connection', socket => {
    console.log('connection');
    socket.on('user/list', function (req, res){
    	console.log("list users");
    	User.findAll().then(users => {
    		console.log(users)
    		socket.emit('user/list', users)
    	});
    });
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
    });
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
    });
    socket.on('user/delete', (id) => {
    	console.log('delete user');
    	User.destroy({
    		where: {id: id},
    		truncate: true
    	}).then(user => {
    		console.log(user);
    	})
    });
   socket.on('package/list', function (req, res){
    	console.log("list packages");
    	User.findAll().then(packages => {
    		console.log(packages)
    		socket.emit('package/list', packages)
    	});
    });
   socket.on('package/add', (p1, p2, p3, p4, p5, p6) => {
   		console.log("add package" + p1 + " " + p2 + " " + p3 + " " + p4  + " " + p5 + " " + p6 );
   		Package.create({
   			idOwner: p1,
	   		idShipper: p2,
	   		shipCost: p3,
	   		advanceMoney: p4,
	   		sendAddress: p5,
	   		recieveAddress: p6
   		}).then(package => {
   			console.log(package)
   		});
   });
   socket.on('package/update', (id, p1, p2, p3, p4, p5, p6) => {
   		console.log("update package" + id +" " + p1 + " " + p2 + " " + p3 + " " + p4  + " " + p5 + " " + p6 );
   		Package.update({
   			idOwner: p1,
	   		idShipper: p2,
	   		shipCost: p3,
	   		advanceMoney: p4,
	   		sendAddress: p5,
	   		recieveAddress: p6
   		},{
   			where: {id: id}
   		}).then(package => {
   			console.log(package)
   		});
   });
   socket.on('package/delete', (id) => {
    	console.log('delete package');
    	User.destroy({
    		where: {id: id},
    		truncate: true
    	}).then(package => {
    		console.log(package);
    	})
    });
});
server.listen(3000, ()=>{
	console.log("Node app is running on port 3000");
});