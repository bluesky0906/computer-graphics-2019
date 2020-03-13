import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class PngToJpeg {
  static void execute (String path, String path2) {
    List<String> names = read(path);
    for(int i=0; i<names.size(); i++) {
      try {
        BufferedImage image = ImageIO.read(new File(names.get(i)));
        BufferedImage tmp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D off = tmp.createGraphics();
        off.drawImage(image, 0, 0, Color.WHITE, null);
        ImageIO.write(tmp, "jpg", new File(path2+ "/" + i + ".jpg"));
      } catch (Exception e) {
        System.out.println("error");
      }
    }
  }

  //pngを読む
  static List<String> read(String path) {
    File dir = new File(path);
    File files[] = dir.listFiles();
    String extension = ".png";
    List<String> names = new ArrayList<String>();

    for(int i=0; i<files.length; i++){
      String file_name = files[i].getName();
      if(files[i].isDirectory()){  //ディレクトリなら再帰を行う
        read(path + "/" + file_name);
      }else{
        if(file_name.endsWith(extension)) { //file_nameの最後尾(拡張子)が指定のものならば出力
          names.add(path + "/" + file_name);
          //images.add(file_name);
          //System.out.println(file_name);
        }
      }
    }
    return names;
  }

}
