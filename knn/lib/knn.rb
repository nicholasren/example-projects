class KNN

  def initialize k = 3
    @k = k
  end

  def add_data datum
    @data ||= [] 
    @data << datum
  end

  def result_of x
    return x.v if @data.include? x
    
    heap = MaxHeap.new

    @data.each do |datum|
      distance = distance_between(x, datum)
      heap.insert Node.new(distance, datum.v)
    end

    top_k = heap.take_top @k
    value_with_max_vote top_k
  end

  private

  def distance_between a, b
    (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)
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

class Datum
  attr_reader :x, :y, :v
  def initialize x = 0, y = 0, v = nil
    @x = x
    @y = y
    @v = v
  end
end
