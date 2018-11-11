package bokarkiv;
import java.io.*;

import javax.swing.JTextArea;

public class Bokregister
{
  private Bok første;

  //registrerer et bokobjekt
  public void settInnForrest( Bok ny )  // sortert
  { if(ny == null) return;
    ny.neste = første;
    første = ny;
  }
  public void settInn( Bok ny )
	{
    if( ny == null ) return;

    if( første == null ) // tom liste:
    {
      første = ny;
      return;
    }
					// objektet skal inn forrest:
    if( ( ny.getForfatter().compareToIgnoreCase( første.getForfatter() ) == 0 &&
          ny.getTittel().compareTo( første.getTittel() ) < 0 )
		 || ( ny.getForfatter().compareTo( første.getForfatter() ) < 0 ) )
    {
	    settInnForrest( ny );
      return;
    }

    Bok løper = første;
    while( løper.neste != null )
    {
      if( ( ny.getForfatter().compareTo(løper.neste.getForfatter() ) == 0 &&
            ny.getTittel().compareTo(løper.neste.getTittel() ) < 0 )
       || ( ny.getForfatter().compareTo(løper.neste.getForfatter() ) < 0 ) )
      {
        ny.neste = løper.neste;
        løper.neste = ny;
					  return;
      }
      else
        løper = løper.neste;
    }
   // setter inn boka sist i lista.
    løper.neste = ny;
  }

  public void skrivObjektTilFil()
  {
	  try( DataOutputStream ut = new DataOutputStream( new FileOutputStream( "oblig1.dta" )))
	  {
		  Bok ny = første;	
		  while (ny != null)
		  {
			  ny.skrivObjektTilFil(ut);
			  ny = ny.neste;
		  }
	  }
	  catch(IOException s)
	  {
	  }
  }
  
  public boolean lesObjektFraFil()
	{
	  try (DataInputStream inn = new DataInputStream(new FileInputStream("oblig1.dta")))
	  {
		  while(true)
		  {
			  Bok ny;
			  String test = inn.readUTF();
			  if(test.equals("Skolebok"))
				  ny = new Skolebok();
			  else if(test.equals("Fagbok"))
			  	  ny = new Fagbok();
			  else if(test.equals("NorskRoman"))
			  	  ny = new NorskRoman();
			  else
				  ny = new UtenlandskRoman();  
			  ny.lesObjektFraFil(inn);
			  settInn(ny);
		  }
	  }
	  catch(FileNotFoundException fnf)
	  {
		  
	  }
	  catch(EOFException eof)
	  {
		  
	  }
	  catch(IOException ioe)
	  {
		  
	  }
	  return true;
	}

  //utskrift av innhold i bokliste
  public void skrivListe( JTextArea bøker )
  {
    if ( første == null )
      bøker.append( "Ingen bøker registrert." );
    else
    {
      bøker.setText( "" );
      Bok løper = første;
      while ( løper != null )
      {
        bøker.append( løper.toString() + "\n" );
        løper = løper.neste;
      }
    }
  }
}