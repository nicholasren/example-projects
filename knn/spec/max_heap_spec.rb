require "spec_helper"

describe "MaxHeap" do
  before(:each) do
    @max_heap = MaxHeap.new
  end

  it "should put max value on root" do
    @max_heap.insert(1);
    @max_heap.insert(10);

    @max_heap.peek.should == 10
  end

  it "should shift second largest value to top after top is removed" do
    @max_heap.insert(8);
    @max_heap.insert(1);
    @max_heap.insert(7);
    @max_heap.insert(10);

    @max_heap.take.should == 10
    @max_heap.take.should == 8
    @max_heap.take.should == 7
    @max_heap.take.should == 1
  end
end
