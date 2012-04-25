package org.nicholasren.m8ContactConvertor

import xml.{Source, XML}
import java.util.regex.Pattern

/**
 * Created by IntelliJ IDEA.
 * User: nicholasren
 * Date: 11-7-14
 * Time: 下午8:36
 */

object Convertor
{
  def parseFromM8Format():List[Contact] =
  {
     val path = "G:\\M8\\backup\\Contact.xml";
     val xml = XML.load(Source.fromFile(path))

     val contactsNodes = xml \\ "Contact"

     contactsNodes.toList.map{
       contactNode  => {
         val name = (contactNode \\ "FileAs").text
         val phones = ((contactNode \\ "PhoneElement") \\ "@Value").map(x => x.text).toList

         new Contact(name, phones)
       }
     }.toList
  }

  def parseFromCSV():List[Contact]=
  {
    val pattern = Pattern.compile("(.*),(.*),(.*)")
    val path = "G:\\M8\\backup\\all.csv"

    val lines = scala.io.Source.fromFile(path).getLines

    lines.map
    {
       line =>
       {
           val matcher = pattern.matcher(line);
           if(matcher.matches)
           {
             val name = matcher.group(1);
           val phone1 = matcher.group(2)
           val phone2 = matcher.group(3);
           new Contact(name, List(phone1, phone2))
           }
         else
           {
            new Contact("", List())
           }

       }
    }.toList
  }

  def main(args:Array[String])
  {

    val contacts = parseFromCSV;
    println(contacts.length)
    contacts foreach( x => println(buildVCard(x)))
  }

  def buildVCard(contact : Contact):String =
    {
          if(contact.phones.length > 0)
            "BEGIN:VCARD\r\n" +"VERSION:3.0\r\n" + "FN:"+contact.name +"\r\n" +"TEL;TYPE=CELL:" + contact.phones()(0) +"\r\nEND:VCARD"
          else
            ""
    }
}

class Contact(_name : String, _phones : List[String])
{
  def phones() = _phones
  def name() = _name
}