class MaxHeap
  def initialize 
    @items = []
  end

  def insert x
    @items << x
    fix_up
  end

  def peek
    @items[0] 
  end

  def take
    largest = @items.delete_at 0 
    fix_down
    largest
  end

  def take_top n
    top_n = []
    n.times do
      top_n << take
    end
    top_n
  end


  private

  def fix_down i = 0
    largest = find_largest i
    if largest != i
      swap largest, i
      fix_down largest
    end
  end

  def fix_up i = @items.length - 1
    return if i == 0 
    parent = (i - i % 2) / 2

    if @items[i] > @items[parent]
      swap i, parent
      fix_up parent
    end
  end

  def swap x, y
      tmp = @items[x]
      @items[x] = @items[y]
      @items[y] = tmp
  end

  def find_largest i
    left = 2 * i + 1
    right = 2 * i + 2
    largest = i

    if left < @items.length && @items[left] > @items[largest]
      largest = left
    elsif right < @items.length && @items[right] > @items[largest]
      largest = right 
    end
    largest
  end
end
