require "spec_helper"

describe KNN do
  before(:each) do
    @knn = KNN.new
  end

  it "should load test data" do
    set_test_data "1,0|1"
    @knn.load_data(TEST_DATA_FILE_PATH)

    @knn.result_for([1.0, 0.0]).should == 1
  end

  it "should return value same with nearest neighbor" do
    set_test_data %w{ 1,0|1  2,0|1 10,0|0 8.5,0|0 11,0|0}
    @knn.load_data(TEST_DATA_FILE_PATH)

    @knn.result_for([9, 0]).should == 0
    @knn.result_for([1.5, 0]).should == 1
  end

  it "should return value same with neighbor groups which has more member" do
    set_test_data %w{ 1,0|1 0,1|1 2,1|0}
    @knn.load_data(TEST_DATA_FILE_PATH)
    
    @knn.result_for([1, 1]).should == 1 
  end

  it "should return value same with nearest neighbor" do
    set_test_data %w{ 1,1,0|1 1,0,1|1 2,5,10|0 2,5,5|0}
    @knn.load_data(TEST_DATA_FILE_PATH)
    
    @knn.result_for([1, 1, 1]).should == 1 
  end
end
