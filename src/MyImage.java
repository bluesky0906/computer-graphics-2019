
import java.awt.Color;

public class MyImage {

	int width, height;
	
	int pixelarray[];
	
	
	public MyImage(int w, int h) {
		width = w;   height = h;
		pixelarray = new int[w * h];
	}
	
	public MyImage(int w, int h, int array[]) {
		width = w;   height = h;
		pixelarray = array;
	}
	

	public void setColor(int j, int i, Color color) {
		int id = i * width + j;
		int value = color.getRGB();
		pixelarray[id] = value;
	}
	
	public Color getColor(int j, int i) {
		int id = i * width + j;
		Color color = new Color(pixelarray[id]);
		return color;
	}
	
}
