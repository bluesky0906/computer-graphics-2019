
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class VirtualStudio {
	static MyImage execute(List<MyImage> images, MyImage frame, List<int[]> coordinate) {

		MyImage output;

    int csize = coordinate.size();
    int isize = images.size();
    Random random = new Random();

		// クラスタリング
		List<MyImage> images0 = new ArrayList<MyImage>();
		KMeans kmeans = new KMeans();
		for(int i=0; i<isize; i++){
			MyImage image = images.get(i);
			kmeans.clustering(image, 6);
	    images0.add(Chromakey.execute(image, kmeans, 3));
		}

    for(int k=0; k<csize; k+=1500){
      int randomValue = random.nextInt(isize);
      MyImage image = images.get(randomValue);
      int width = image.width;
      int height = image.height;

      int h = coordinate.get(k)[0];
      int w = coordinate.get(k)[1];

			MyImage image0 = images0.get(randomValue);


      for(int i = h, y = 0; i < height+h && y < height; i++, y++) {
        for(int j = w, x = 0; j < width+w && x < width; j++, x++) {

					Color color = image.getColor(x, y);

					Color color0 = image0.getColor(x, y);
					if(color0.getRed() > 0) {
						frame.setColor(j, i, color);
					}
        }
      }
    }
    return frame;
	}
}
