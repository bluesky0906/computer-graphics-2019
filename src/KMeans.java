
import java.awt.*;


public class KMeans {
	int SIZE_RGB = 256 * 256 * 256;
	int idarray[] = null;
	int width, height, num;

	MyImage clustering(MyImage input, int n) {
		width = input.width;
		height = input.height;
		num = n;

		int counter;
		double distance;
		Color centers[] = new Color[num];
		Color pcenters[] = new Color[num];


		MyImage output = new MyImage(width, height);
		idarray = new int[width * height];

		initCenters(centers, num);

		for(counter = 1; ; counter++) {
			assignPixels(input, output, centers);
			distance = calcNewCenter(input, output, centers, pcenters);
			//System.out.println("### clustering: counter=" + counter + " distance=" + distance);
			if(distance <= num * 1.0 || counter > 100) break;
			for(int i = 0; i < num; i++)
				centers[i] = pcenters[i];
		}
		return output;
	}
	void initCenters(Color[] centers, int num) {
		int i, r, g, b, value;

		for(i = 0; i < num; i++) {
			value = SIZE_RGB * i / (num + 1);
			r = value / (256 * 256);
			value -= r * 256 * 256;
			g = value / 256;
			value -= g * 256;
			b = value;
			centers[i] = new Color(r, g, b);
		}
	}
	void assignPixels(MyImage input, MyImage output, Color centers[]) {
		int r1, g1, b1, r2, g2, b2;
		double dist, mind;


		for(int i = 0, ii = 0; i < height; i++) {
			for(int j = 0; j < width; j++, ii++) {

				Color color1 = input.getColor(j, i);
				r1 = color1.getRed();
				g1 = color1.getGreen();
				b1 = color1.getBlue();


				int id = -1;
				mind = 1.0e+30;
				for(int k = 0; k < num; k++) {

					r2 = centers[k].getRed();
					g2 = centers[k].getGreen();
					b2 = centers[k].getBlue();

					dist = (double)(r2 - r1) * (double)(r2 - r1)
							+ (double)(g2 - g1) * (double)(g2 - g1)
							+ (double)(b2 - b1) * (double)(b2 - b1);
					if(dist < mind) {
						mind = dist;   id = k;
					}
				}
				idarray[ii] = id;
				output.setColor(j,  i, centers[id]);
			}
		}
	}
double calcNewCenter(MyImage input, MyImage output, Color[] centers, Color[] pcenters) {
		int[] rgbsum = new int[3 * num];
		int[] rgbcount = new int[num];
		int r1, g1, b1, r2, g2, b2;
		double dist;

		for(int i = 0; i < num; i++) {
			rgbsum[3 * i] = rgbsum[3 * i + 1] = rgbsum[3 * i + 2] = rgbcount[i] = 0;
		}

		for(int i = 0, ii = 0; i < height; i++) {
			for(int j = 0; j < width; j++, ii++) {

				int id = idarray[ii];

				Color color1 = input.getColor(j, i);
				rgbcount[id] += 1;
				rgbsum[id * 3 + 0] += color1.getRed();
				rgbsum[id * 3 + 1] += color1.getGreen();
				rgbsum[id * 3 + 2] += color1.getBlue();
			}
		}


		dist = 0.0;
		for(int i = 0, ii = 0; i < num; i++, ii += 3) {

			if(rgbcount[i] > 0) {
				rgbsum[ii + 0] /= rgbcount[i];
				rgbsum[ii + 1] /= rgbcount[i];
				rgbsum[ii + 2] /= rgbcount[i];
			}
			r1 = rgbsum[ii + 0];
			g1 = rgbsum[ii + 1];
			b1 = rgbsum[ii + 2];
			pcenters[i] = new Color(r1, g1, b1);

			r2 = centers[i].getRed();
			g2 = centers[i].getGreen();
			b2 = centers[i].getBlue();

			dist += (double)(r2 - r1) * (double)(r2 - r1)
			     +  (double)(g2 - g1) * (double)(g2 - g1)
			     +  (double)(b2 - b1) * (double)(b2 - b1);
		}

		return dist;
	}
}
