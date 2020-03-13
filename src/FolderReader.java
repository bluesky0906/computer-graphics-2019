
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class FolderReader {
  // jpgを読む
  static List<MyImage> execute(String path) {
    // ディレクトリ
    File dir = new File(path);
    // ファイルの配列
    File files[] = dir.listFiles();
    // 拡張子
    String extension = ".jpg";
    List<MyImage> images = new ArrayList<MyImage>();

    for(int i=0; i<files.length; i++){
      String file_name = files[i].getName();

      // ディレクトリなら再帰を行う
      if(files[i].isDirectory()){
        execute(path + "/" + file_name);
      }else{
        if(file_name.endsWith(extension)) {
          // file_nameの最後尾(拡張子)が指定のものならば出力
          images.add(JpegFileReader.read(path + "/" + file_name));
        }
      }
    }
    return images;
  }
}
