function Animal(name)
{
	this.name = name;

	this.run = function (){
	  console.log(this.name + " is runing")	
	}
}

function Wolf(name)
{
	this.name = name;
	this.hunt = function ()
	{
	  console.log(this.name + " is hunting")	
	}
}

//the inheritance magic occured here
Wolf.prototype = new Animal();

var someone = new Animal("Some one");
var sam = new Wolf("Sam");

someone.run();
sam.run();
sam.hunt();
//should got exception here
//someone.hunt(); 
