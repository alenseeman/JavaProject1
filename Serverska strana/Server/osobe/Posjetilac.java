package osobe;
import java.util.*;
import java.io.*;

public class Posjetilac extends Osoba
{
  protected String adresa;
  protected String telefon;
  protected String mail;
  
  public Posjetilac()
  {
    super();
  }
  public Posjetilac(String i,String p,String adr,String tel,String mai)
  {
    super(i,p);
    adresa=adr;
    telefon=tel;
    mail=mai;
  }
  
  public String getAdresa()
  {
    return adresa;
  }
  public void setAdresa(String a)
  {
    adresa=a;
  }
  public void setTelefon(String tel)
  {
    telefon=tel;
  }
  public void setMail(String m)
  {
    mail=m;
  }
  
  
  public String getTelefon()
  {
    return telefon;
  }
  public String getMail()
  {
    return mail;
  }
  public String toString()
  {
    return ime+" "+prezime+" "+telefon;
  }
  public String csvToString()
  {
    return super.csvToString()+","+adresa+","+telefon+","+mail;
  }
}