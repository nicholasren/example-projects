def next (n:Float, x:Float):Float={
	(x + n/x)/2
}

def repeat(f:(Float => Float), a:Float):List[Float]={
	a :: repeat (f, f(a))
}

def within(eps:Float, xs:List[Float]):Float={
	xs match{
		case x::y::ys => 
			if (math.abs(x - y) < eps) {
				y
			}
			else {
				within(eps, ys)
			}

		case _ => 0
	}
}
val result = repeat (next(2, _:Float), 3)