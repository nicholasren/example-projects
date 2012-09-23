import xml.{Source, XML}
import java.util.regex.Pattern
import java.io._


object Convertor
{
  def parseFromM8Format():List[Contact] =
  {
    val path = "/Users/reaapi/Desktop/Contact.xml";
    val xml = XML.load(Source.fromFile(path))

      val contactsNodes = xml \\ "Contact"

      contactsNodes.toList.map{
        contactNode  => {
          val name = new String((contactNode \\ "FileAs").text.getBytes("UTF-8"))
          val phones = ((contactNode \\ "PhoneElement") \\ "@Value").map(x => x.text).toList

          new Contact(name, phones)
        }
      }.toList
  }

  def parseFromCSV():List[Contact]= {
    val pattern = Pattern.compile("(.*),(.*),(.*)")
    val path = "G:\\M8\\backup\\all.csv"

    val lines = scala.io.Source.fromFile(path).getLines

     lines.map {
       line => {
         val matcher = pattern.matcher(line);
         if(matcher.matches) {
           val name = matcher.group(1);
           val phone1 = matcher.group(2)
           val phone2 = matcher.group(3);
           new Contact(name, List(phone1, phone2))
         } else {
           new Contact("", List())
         }
       }
     }.toList
  }


  def writeToTarget(contacts: List[Contact]) {

    val writer = new PrintWriter(new File("/tmp/m8-contacts.vcard" ))

    try {
      contacts.map( x => buildVCard(x)).foreach {
        line => writer.write(line)
      }
    }finally {
      writer.close
    }
  }

  def buildVCard(contact : Contact):String = {
    if(contact.phones.length > 0)
      "BEGIN:VCARD\r\n" +"VERSION:3.0\r\n" + "FN:"+contact.name +"\r\n" +"TEL;TYPE=CELL:" + contact.phones()(0) +"\r\nEND:VCARD\r\n"
    else
      ""
  }

  def run() {
    val contacts = parseFromM8Format;
    writeToTarget(contacts)
  }
}

class Contact(_name : String, _phones : List[String])
{
  def phones() = _phones
    def name() = _name
}

Convertor.run
