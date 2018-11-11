package bokarkiv;
import java.awt.event.*;

public class Bokprogram
{
  public static void main( String[] args )
  {
    Bokarkiv vindu = new Bokarkiv();
    vindu.lesObjektFraFil();
    vindu.addWindowListener
    (
		new WindowAdapter()
		{
			public void windowClosing( WindowEvent e )
			{
				vindu.skrivObjektTilFil();
			    System.exit( 0 );
			}
		}
	);
  }
}