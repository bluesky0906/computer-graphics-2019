import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Collections;

public class Character {

  static MyImage execute(List<MyImage> images, String chara, MyImage frame) {
    //　frameの縦横サイズ
    int width = frame.width;
    int height = frame.height;

    MyImage board = Board.make(width, height, chara);

    //黒の数と座標
    List<int[]> coordinate = new ArrayList<int[]>();
    int[] num = new int[2];

    //　coordinateのcount番目に、黒の座標を入れる
    for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
        Color color = board.getColor(j, i);
        if(color.equals(Color.BLACK)) {
          num[0] = i;
          num[1] = j;
          coordinate.add(num.clone());
        }
      }
    }
    // imagesのサイズを統一
    for(int i=0; i<images.size(); i++){
      images.set(i, Scale.execute(images.get(i)));
    }
    // 座標をランダムに
    Collections.shuffle(coordinate);
    // 書き込む
    MyImage img = VirtualStudio.execute(images, frame, coordinate);

    return img;
  }
}
