
import java.awt.*;


public class Chromakey {

	static MyImage execute(MyImage input, KMeans kmeans, int id) {
		int width = input.width;
		int height = input.height;
		MyImage output = new MyImage(width, height);

		for(int i = 0, ii = 0; i < height; i++) {
			for(int j = 0; j < width; j++, ii++) {

				if(kmeans.idarray[ii] == id)
					output.setColor(j, i, Color.black);
				else
					output.setColor(j, i, Color.white);
			}
		}
		return output;
	}
}
