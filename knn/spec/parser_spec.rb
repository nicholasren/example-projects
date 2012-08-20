require "spec_helper"

describe Parser do
  before(:each) do
    @parser = Parser.new
  end

  it "should parse formatted data to a Datum instance" do

    formated_data = "1,1|10"

    datum = @parser.parse formated_data

    datum.vector.should == [1,1] 
    datum.result.should == 10
  end

  it "should parse formated data from file and get list of Datum instances" do
    set_test_data %w{2,2|2 2,8|10 4,9|12 5,10|22}

    data = @parser.from_file  TEST_DATA_FILE_PATH
    {
      0 => [[2,2],2], 
      1 => [[2,8],10],
      2 => [[4,9],12],
      3 => [[5,10],22]
    }.each do |i, v|
      datum = data[i]
      datum.vector.should == v[0]
      datum.result.should == v[1]

    end

  end

end
