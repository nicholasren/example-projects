require "spec_helper"

describe "MaxHeap" do
  it "should put max value on root" do
    max_heap = MaxHeap.new
    max_heap.insert(1);
    max_heap.insert(10);

    max_heap.root.should == 10
  end
end
