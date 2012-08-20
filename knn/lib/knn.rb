class KNN

  def initialize k = 3
    @k = k
    @parser = Parser.new
    @data = {}
  end

  def load_data file_path
    @parser.from_file(file_path).each do |e|
      @data[e.vector]= e.result
    end
  end

  def result_for vector 
    return @data[vector] if @data[vector]

    heap = MaxHeap.new

    @data.each do |k, v|
      distance = distance_between(k, vector)
      heap.insert Node.new(distance, v)
    end

    top_k = heap.take_top @k
    value_with_max_vote top_k
  end

  private

  def distance_between a, b
    distance = 0
    a.each_index do |idx|
      distance += square(a[idx] - b[idx])
    end
    distance
  end


  def square x
    x * x
  end


  def value_with_max_vote xs
    votes = {}
    xs.each do |x|
      if votes[x.result].nil?
        votes[x.result] = 1
      else
        votes[x.result] = votes[x.result] + 1 
      end
    end

    max_vote = 0
    result = nil
    votes.each do |k, v|
      if v > max_vote 
        max_vote = v
        result =k
      end
    end

    result 
  end
end

class Node
  include Comparable
  
  attr_accessor :distance, :result
  def initialize distance, result
    @distance = distance
    @result = result
  end

  def <=>(other)
    other.distance <=> self.distance
  end

end
