class Parser
  def parse formated_data
    splited = formated_data.split '|'
    vector = splited[0].split(',').map {|e| e.to_f}
    Datum.new vector, splited[1].to_f
  end

  def from_file file_path
    data = []
    File.open(file_path, 'r') do |file|
      while(line = file.gets)
        data << parse(line)
      end
    end
    data
  end
end
