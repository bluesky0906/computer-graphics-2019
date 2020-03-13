import java.awt.Color;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Board {

  /* 文字入り画像をMyImageとして返す */
  static MyImage make (int width, int height, String chara) {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
    Graphics g = image.getGraphics();

    // 背景をまっしろに
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, width, height);
    // 文字を書く
    drawStringCenter(g, chara, width, height);
    // 解放
    g.dispose();

    MyImage board = JpegFileReader.readBI(image);
    return board;
  }

  static void drawStringCenter(Graphics g,String text,int width,int height){
    g.setColor(Color.BLACK);

    int size = (int)(width/text.length()) + 300;
    Font font = new Font(Font.MONOSPACED,Font.PLAIN,size);
    g.setFont(font);
    FontMetrics fm = g.getFontMetrics();
    Rectangle rectText = fm.getStringBounds(text, g).getBounds();
    int x = (width - rectText.width) /2;
    int y = (height - rectText.height) /2 + fm.getMaxAscent();

    g.drawString(text, x, y);
  }
}
