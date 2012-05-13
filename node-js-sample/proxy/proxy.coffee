http = require('http')
util = require('util')
url = require('url')

start = () ->
  server = http.createServer(transfer)
  server.listen 8999
  console.log(util.format('Proxy server is running at \n\t\n\thttp://localhost:%d\n', server.address().port))

transfer = (request, response) ->
  option = url.parse(request.url)
  option['method'] ||= 'GET'

  http.request(option, (targetResponse) ->
    response.writeHead(targetResponse.statusCode, targetResponse.headers)
    console.log(option.method + ' ' + targetResponse.statusCode + ' ' + option.path)

    targetResponse.on('data', (chunk) ->
      response.write chunk, 'binary'
    )

    targetResponse.on('end', () ->
      response.end()
    )
  ).end()

start()
