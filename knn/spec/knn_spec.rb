require "spec_helper"
describe KNN do

  it "should load test data" do
    knn = KNN.new
    a = Datum.new(1, 0, true)
    knn.add_data(a)

    knn.result_of(a).should be_true
  end

  it "should return value same with nearest neighbor" do
    knn = KNN.new 1

    a = Datum.new(1,0, true)
    b = Datum.new(2, 0, true)
    c = Datum.new(10, 0, false)

    knn.add_data(a)
    knn.add_data(b)
    knn.add_data(c)

    d = Datum.new(9)
    e = Datum.new(1.5)

    knn.result_of(d).should be_false
    knn.result_of(e).should be_true
  end

  it "should return value same with neighbor groups which has more member" do
    knn = KNN.new

    a = Datum.new(1, 0, true)
    b = Datum.new(0, 1, true)
    c = Datum.new(2, 1, false)
    knn.add_data(a)
    knn.add_data(b)
    knn.add_data(c)
    
    d = Datum.new(1, 1)
    knn.result_of(d).should be_true
  end

end
