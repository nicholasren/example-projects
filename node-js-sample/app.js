(function() {
  var server;

  server = require('http').createServer(function(request, response) {});

  response.writeHead(200, {
    'Content-Type': 'text/plain'
  });

  response.end('Hello World');

  server.listen(8124);

  console.log('Server running at http://0.0.0.0:8124/');

}).call(this);
