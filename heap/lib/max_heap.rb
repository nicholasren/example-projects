class MaxHeap
  def initialize 
    @items = []
  end

  def insert x
    @items << x
    heapify 0
  end

  def root
    @items[0] 
  end


  private

  def heapify i
    largest = find_largest i
    if largest != i
      swap largest, i
      heapify largest
    end
  end

  def swap x, y
      tmp = @items[x]
      @items[x] = @items[y]
      @items[y] = tmp
  end

  def find_largest i
    left = 2 * i
    right = 2 * i + 1
    largest = i

    if left < @items.length && @items[left] > @items[largest]
      largest = left
    elsif right < @items.length && @items[right] > @items[largest]
      largest = right 
    end
    largest
  end
end

