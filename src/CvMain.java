import java.util.List;
import java.util.ArrayList;

public class CvMain {
  static void imageProcessing(String args[]) {
    String folderpath0 = "../png";
    String folderpath1 = "../jpg";

    // 写真でかく文字
    String chara = args[0];
    // frameのファイル名
    String framename = "../frame.jpg";
    // 出力ファイル名
    String filename = "../" + args[1] + ".jpg";

    //pngフォルダのpngをjpgに変換
    PngToJpeg.execute(folderpath0,folderpath1);

    List<MyImage> images = new ArrayList<MyImage>();
    images = FolderReader.execute(folderpath1);
    MyImage frame = JpegFileReader.read(framename);
    MyImage image = Character.execute(images,chara,frame);
    JpegFileWriter.write(filename,image);
  }

  public static void main(String args[]) {
    if (args.length != 2){
      System.out.println("The arguments are not correct.");
      System.out.println("CvMain [text] [filename]");
      System.exit(1);
    }

    imageProcessing(args);
  }
}
