http = require('http')
start = (host, port) ->
  delegate = (req, rsp) ->
    fetchTarget({
      host: host,
      port: port || 80,
      path: req.url || 'url',
      method: req.method || 'GET'
    }, rsp)

  server = http.createServer(delegate)
  server.listen 0
  console.log 'Server running at http://0.0.0.0:' + server.address().port

fetchTarget = (option, response) ->
  transfer = (targetResponse) ->
    response.writeHead(targetResponse.statusCode, targetResponse.headers)
    console.log(option.method + ' ' + targetResponse.statusCode + ' ' + option.path)

    targetResponse.on('data', (chunk) ->
      response.write chunk, 'binary'
    )
    targetResponse.on('end', () ->)

  http.request(option, transfer).end()

start "www.baidu.com", '80'
