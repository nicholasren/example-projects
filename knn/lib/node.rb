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
