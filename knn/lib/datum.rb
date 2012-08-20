class Datum
  attr_reader :vector, :result
  def initialize vector = [], result = nil
    @vector = vector 
    @result = result
  end
end
