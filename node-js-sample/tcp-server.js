var tcp = require("net");

var server = tcp.createServer();

server.addListener("connection", function(socket) {
  socket.write("Echo server\r\n");
  socket.pipe(socket);
});

server.listen(8000);
