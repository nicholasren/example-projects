var app = require('express').createServer();
app.configure(function(){
  app.use(express.bodyParser());
  app.use(express.cookieParser());
  app.use(express.session({ secret: "keyboard cat"}));
});

app.post('/add-to-cart', function(req, res){
     var items = req.body.items;
     req.session.items = items;
     res.redirect('back');
});

app.get('/add-to-cart', function(req, res){
  if (req.session.items && req.session.items.length) {
     req.flash('info', 'You have %s items in your cart', req.session.items.length);
   }
  res.render('shopping-cart');
});

app.listen(3000);
