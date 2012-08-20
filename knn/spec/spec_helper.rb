Dir["lib/*.rb"].each {|f| require f}

TEST_DATA_FILE_PATH="spec/test_data.txt"

def set_test_data data
  File.open(TEST_DATA_FILE_PATH, 'w+') do |f|
    data.each do |line|
      f.puts line
    end
  end
end
