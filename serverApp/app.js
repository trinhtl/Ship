var express = require("express");
var http = require("http");
var app = express();
var server = http.createServer(app);
var io = require("socket.io").listen(server);

var index = require('./routes/index');
var bodyParser = require('body-parser');
var multer = require('multer');

app.use(express.static('images'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

app.use('/', index);

server.listen(3000, ()=>{
	console.log("Node app is running on port 3000");
});

