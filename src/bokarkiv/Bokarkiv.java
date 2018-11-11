//Bokarkiv.java
package bokarkiv;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bokarkiv extends JFrame
{
  private JTextField forfatterfelt, tittelfelt, sideantallsfelt, prisfelt, fagfelt, skolefagfelt,
            klassetrinnfelt, sjangerfelt, målformfelt, språkfelt;
  private JButton regFagbok, regSkolebok, regNRoman, regURoman, visReg;
  private JTextArea utskriftsområde;
  private Bokregister register = new Bokregister();

  public Bokarkiv()
  {
    super( "Bokarkiv" );

    forfatterfelt = new JTextField( 18 );
    tittelfelt = new JTextField( 18 );
    sideantallsfelt = new JTextField( 4 );
    prisfelt = new JTextField( 6 );
    fagfelt = new JTextField( 18 );
    skolefagfelt = new JTextField( 15 );
    klassetrinnfelt = new JTextField( 3 );
    sjangerfelt = new JTextField( 10 );
    målformfelt = new JTextField( 2 );
    språkfelt = new JTextField( 10 );
    regFagbok = new JButton( "Registrer fagbok" );
    regSkolebok = new JButton( "Registrer skolebok" );
    regNRoman = new JButton( "Registrer norsk roman" );
    regURoman = new JButton( "Registrer utenlandsk roman" );
    visReg = new JButton( "Vis bokregister" );
    utskriftsområde = new JTextArea( 15, 45 );
    utskriftsområde.setEditable( false );

    Container c = getContentPane();
    c.setLayout( new FlowLayout() );
    c.add( new JLabel( "Forfatter:" ) );
    c.add( forfatterfelt );
    c.add( new JLabel( "Tittel:" ) );
    c.add( tittelfelt );
    c.add( new JLabel( "Sideantall:" ) );
    c.add( sideantallsfelt );
    c.add( new JLabel( "Pris:" ) );
    c.add( prisfelt );
    c.add( new JLabel( "Fagområde:" ) );
    c.add( fagfelt );
    c.add( new JLabel( "Skolefag:" ) );
    c.add( skolefagfelt );
    c.add( new JLabel( "Klassetrinn:" ) );
    c.add( klassetrinnfelt );
    c.add( new JLabel( "Sjanger:" ) );
    c.add( sjangerfelt );
    c.add( new JLabel( "Målform (b = bokmål, n = nynorsk):" ) );
    c.add( målformfelt );
    c.add( new JLabel( "Språk:" ) );
    c.add( språkfelt );
    c.add(new JLabel("             "));
    c.add( regFagbok );
    c.add( regSkolebok );
    c.add( regNRoman );
    c.add( regURoman );
    c.add( visReg );
    c.add( new JScrollPane( utskriftsområde ) );

    Knappelytter lytter = new Knappelytter();

    regFagbok.addActionListener( lytter );
    regSkolebok.addActionListener( lytter );
    regNRoman.addActionListener( lytter );
    regURoman.addActionListener( lytter );
    visReg.addActionListener( lytter );
    setSize( 550, 500 );
    setVisible( true );
  }

  public void nyFagbok()
  {
		String forfatter = forfatterfelt.getText();
		String tittel = tittelfelt.getText();
		String fag = fagfelt.getText();
		if(forfatter.length() == 0 || tittel.length() == 0
		|| fag.length() == 0 )
		{
			visMelding("Fyll ut nødvindige tekstfelt");
			return;
		}
		try
		{
			int sideantall = Integer.parseInt(sideantallsfelt.getText());
			double pris = Double.parseDouble(prisfelt.getText());
			register.settInn(new Fagbok(forfatter, tittel, sideantall, pris, fag));
			visMelding( "Ny fagbok registrert." );
			slettFelter();
		}
		catch(NumberFormatException nfe)
		{
			visMelding("Feil i tallformat");
		}
	}


  public void nySkolebok()
  {
    String forfatter = forfatterfelt.getText();
    String tittel = tittelfelt.getText();
    String skolefag = skolefagfelt.getText();
    if ( forfatter.length() == 0 ||
         tittel.length() == 0 || skolefag.length() == 0 )
    {
      visMelding( "Fyll ut nødvendige tekstfelter!" );
      return;
    }
    try {
      int sideantall = Integer.parseInt( sideantallsfelt.getText() );
      double pris = Double.parseDouble( prisfelt.getText() );
      int trinn = Integer.parseInt( klassetrinnfelt.getText() );
      register.settInn(
          new Skolebok( forfatter, tittel, sideantall, pris, trinn, skolefag ) );
      visMelding( "Ny skolebok registrert." );
      slettFelter();
    }
    catch ( NumberFormatException e ) {
      visMelding( "Ingen registrering pga. feil i tallformat!" );
    }
  }

  public void nyNorskRoman()
  {
    String forfatter = forfatterfelt.getText();
    String tittel = tittelfelt.getText();
    String sjanger = sjangerfelt.getText();
    String målform = målformfelt.getText();
    if ( forfatter.length() == 0 || tittel.length() == 0
         || sjanger.length() == 0 || målform.length() == 0 )
    {
      visMelding( "Fyll ut nødvendige tekstfelter!" );
      return;
    }
    try {
      int sideantall = Integer.parseInt( sideantallsfelt.getText() );
      double pris = Double.parseDouble( prisfelt.getText() );
      char målkode = målform.charAt( 0 );
      if ( målkode == 'b' )
        målform = "bokmål";
      else
        målform = "nynorsk";
      register.settInn(
          new NorskRoman( forfatter, tittel, sideantall, pris, sjanger, målform ) );
      visMelding( "Ny norsk roman registrert." );
      slettFelter();
    }
    catch ( NumberFormatException e ) {
      visMelding( "Ingen registrering pga. feil i tallformat!" );
    }
  }

  public void nyUtenlandskRoman()
  {
    String forfatter = forfatterfelt.getText();
    String tittel = tittelfelt.getText();
    String sjanger = sjangerfelt.getText();
    String språk = språkfelt.getText();
    if ( forfatter.length() == 0 || tittel.length() == 0 ||
         sjanger.length() == 0 || språk.length() == 0 )
    {
      visMelding( "Fyll ut nødvendige tekstfelter!" );
      return;
    }
    try
    {
      int sideantall = Integer.parseInt( sideantallsfelt.getText() );
      double pris = Double.parseDouble( prisfelt.getText() );
      register.settInn(
          new UtenlandskRoman( forfatter, tittel, sideantall, pris, sjanger, språk ) );
      visMelding( "Ny utenlandsk roman registrert." );
      slettFelter();
    }
    catch ( NumberFormatException e ) {
      visMelding( "Ingen registrering pga. feil i tallformat!" );
    }
  }

  public void visRegister()
  {
    register.skrivListe( utskriftsområde );
  }

  private void visMelding( String melding )
  {
    JOptionPane.showMessageDialog( this, melding );
  }
  
  public void lesObjektFraFil()
  {
	  register.lesObjektFraFil();
  }
  public void skrivObjektTilFil()
  {
	  register.skrivObjektTilFil();
  }

  private void slettFelter()
  {
    forfatterfelt.setText( "" );
    tittelfelt.setText( "" );
    sideantallsfelt.setText( "" );
    prisfelt.setText( "" );
    fagfelt.setText( "" );
    skolefagfelt.setText( "" );
    klassetrinnfelt.setText( "" );
    sjangerfelt.setText( "" );
    målformfelt.setText( "" );
    språkfelt.setText( "" );
  }

  private class Knappelytter implements ActionListener
  {
    public void actionPerformed( ActionEvent e )
    {
      if ( e.getSource() == regFagbok )
        nyFagbok();
      else if ( e.getSource() == regSkolebok )
        nySkolebok();
      else if ( e.getSource() == regNRoman )
        nyNorskRoman();
      else if ( e.getSource() == regURoman )
        nyUtenlandskRoman();
      else if ( e.getSource() == visReg )
        visRegister();
    }
  }
}