import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public  class GamStMenu extends GamSt implements interState, ImageObserver {
					
	


	public void Render(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("Menu render");
		
		    
			Graphics2D g2 = (Graphics2D) g;

		    Image img1 = Toolkit.getDefaultToolkit().getImage("resources\\forest_town.png");
		    Image img2 = Toolkit.getDefaultToolkit().getImage("resources\\town_forest_tiles.png");
		    g2.drawImage(img1, 0, 0, this);
		    g2.drawImage(img2, 200, 200, this);
		    g2.finalize();
	}

	
	public void gsEnter() {
		// TODO Auto-generated method stub
		System.out.println("Menu enter");
	}

	public void gsExit() {
		// TODO Auto-generated method stub
		System.out.println("Menu exit");
	}

	
	public void Update(float elapT) {
		// TODO Auto-generated method stub
		System.out.println("Menu Update");
		
	}


	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

}

