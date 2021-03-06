package dogadjajii;
import osobe.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.text.*;
public class ServerThread extends Thread
{
  private Socket sock;
  private ObjectOutputStream out;
  private ObjectInputStream in;
  public static String obavjestenje="";
  private int broj;
  
  public ServerThread(Socket s,int br)
  {
    sock=s;
    broj=br;
    try{
      out = new ObjectOutputStream(sock.getOutputStream());
      in = new ObjectInputStream(sock.getInputStream());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    start();
  }
  
  
  @Override
  @SuppressWarnings("unchecked")
  public void run()
  {
    Sistem sistem=new Sistem();
    String klijent="";
    try{
      while((klijent=(String)in.readObject())!=null)
      {
        if(klijent.startsWith("UCITAJ"))
        {
          String vrsta=(String)klijent.split("#")[1];
          if("OSOBE".equals(vrsta))
          {
            out.writeObject(Sistem.deserijalizacijaPodataka(klijent.split("#")[2]));
            out.flush();
            out.reset();
          }
          else if("DOGADJAJE".equals(vrsta))
          {
            out.writeObject(Sistem.deserijalizacijaPodataka((String)klijent.split("#")[2]));
            out.flush();
            out.reset();
          } 
        }
        else if(klijent.startsWith("SLANJEFAJLA"))
        {
          String ime=klijent.split("#")[1];
          try{
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
            String datum=sdf.format(new Date());
            File putanja=new File(".\\..\\Podaci\\"+datum);
            if(!putanja.exists())
            {
              putanja.mkdir();
            }
            File f=new File(".\\..\\Podaci"+File.separator+datum+File.separator+ime);
            FileOutputStream outt = new FileOutputStream(f);
            byte[] bytes =(byte[]) in.readObject();     
            outt.write(bytes, 0, bytes.length);
            outt.close();
          }catch(Exception e)
          {
            e.printStackTrace();
          }
        }
        else if(klijent.startsWith("OBRISI"))
        {
          String vrsta=(String)klijent.split("#")[1];
          if("OSOBA".equals(vrsta))
          {
            Osoba os=(Osoba)in.readObject();
            System.out.println(klijent.split("#")[2]);
            ArrayList<? extends Osoba> oss=Sistem.deserijalizacijaPodataka((String)klijent.split("#")[2]);
            System.out.println(oss);
            for(int i=0;i<oss.size();i++)
            {
              if(os.getIme().equals(oss.get(i).getIme()) && os.getPrezime().equals(oss.get(i).getPrezime()))
                oss.remove(i);
            }
            
            
            System.out.println(oss);
            Sistem.serijalizacijaPodataka(oss,klijent.split("#")[2]);
          }
          else if("DOGADJAJ".equals(vrsta))
          {
            String dog=(String)klijent.split("#")[2];
            sistem.obrisiDogadjaj(dog);
          }
        }
        
        else if(klijent.startsWith("NOVO"))
        {
          String vrsta=(String)klijent.split("#")[1];
          System.out.println(vrsta);
          if("OSOBA".equals(vrsta))
          {
            String vrstaOs=klijent.split("#")[2];
            System.out.println(vrstaOs);
//IDEJAAAAAAAAAAAAAAAA
// sistem.novaOsoba(in.readObject(),vrsta);
            Osoba a=(Osoba)in.readObject();
            if(a instanceof Organizator)
            {
              ArrayList<Organizator> org=Sistem.deserijalizacijaPodataka(vrstaOs);
              org.add((Organizator)a);
              Sistem.serijalizacijaPodataka(org,vrstaOs);
            }
            else if(a instanceof Posjetilac)
            {
              ArrayList<Posjetilac> org=Sistem.deserijalizacijaPodataka(vrstaOs);
              org.add((Posjetilac)a);
              Sistem.serijalizacijaPodataka(org,vrstaOs);
            }
            else if(a instanceof Ucesnik)
            {
              ArrayList<Ucesnik> org=Sistem.deserijalizacijaPodataka(vrstaOs);
              org.add((Ucesnik)a);
              Sistem.serijalizacijaPodataka(org,vrstaOs);
            }
            else if(a instanceof Izvodjac)
            {
              ArrayList<Izvodjac> org=Sistem.deserijalizacijaPodataka(vrstaOs);
              org.add((Izvodjac)a);
              Sistem.serijalizacijaPodataka(org,vrstaOs);
            }
            else if(a instanceof Predavac)
            {
              ArrayList<Predavac> org=Sistem.deserijalizacijaPodataka(vrstaOs);
              org.add((Predavac)a);
              Sistem.serijalizacijaPodataka(org,vrstaOs);
            }
          }
          else if("DOGADJAJ".equals(vrsta))
          {
            ArrayList<Dogadjaj> dog=Sistem.deserijalizacijaPodataka("Dogadjaji");
            String vrstaD=klijent.split("#")[2];
            if("Promocije".equals(vrstaD))
            {
              Promocija a=(Promocija)in.readObject();
              dog.add(a);
            }
            else if("Koncerti".equals(vrstaD))
            {
              Koncert ko=(Koncert)in.readObject();
              dog.add(ko);
            }
            else if("Predavanja".equals(vrstaD))
            {
              Predavanja pr=(Predavanja)in.readObject();
              dog.add(pr);
            }
            else if("Izlozbe".equals(vrstaD))
            {
              Izlozba iz=(Izlozba)in.readObject();
              dog.add(iz);
            }
            else if("Ostali ...".equals(vrstaD))
            {
              Dogadjaj dogg=(Dogadjaj)in.readObject();
              dog.add(dogg);            
            }
            else if("Grupa dogadjaja".equals(vrstaD))
            {
              ListaDogadjaja grupaDog=(ListaDogadjaja)in.readObject();
              dog.add(grupaDog);
            }
            Collections.sort(dog);
            Sistem.serijalizacijaPodataka(dog,"Dogadjaji");
          }
        }
      }
    }
    catch(SocketException se){
      System.out.println("Klijent se odjavio");
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    
  }
}