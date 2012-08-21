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
    a.zip(b).map {|x| x[0] - x[1]}.inject(0){|sum, x| sum += x*x}
  end

  def value_with_max_vote xs
    votes = xs.group_by{ |x| x }.map{ |element, group| [element, group.length]}
    max_voted = votes.max_by { |x| x[1] }
    max_voted[0].result
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
