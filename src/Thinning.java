import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class Thinning {
  // NWG法
  public static MyImage execute (MyImage input, int width, int height) {
    boolean bFlag = true;

    int k = 1;

    while(bFlag){
      bFlag = false;
      k = 1-k;
      // 除去リスト
      List<int[]> coordinate = new ArrayList<int[]>();
      int[] num = new int[2];

      for(int i = 1; i < height-1; i++) {
        for(int j = 1; j < width-1; j++) {
          if(worb(input,j,i) == 1){
            // [p7 p0 p1]
            // [p6    p2]
            // [p5 p4 p3]
            int[] p = new int[8];
            // 黒なら1,白なら0を入れる
            p[0] = worb(input,j  ,i-1);
            p[1] = worb(input,j+1,i-1);
            p[2] = worb(input,j+1,i  );
            p[3] = worb(input,j+1,i+1);
            p[4] = worb(input,j  ,i+1);
            p[5] = worb(input,j-1,i+1);
            p[6] = worb(input,j-1,i  );
            p[7] = worb(input,j-1,i-1);

            int a,b,c,d,e,f;
            // 0->1,1->2と見ていき、w->bとなっている数
            a = counta(p);
            // 外周のうち、黒の数
            b = countb(p);

            if(2<=b && b<=6) {
              c = c(p);

              if (a == 1 || c == 1) {
                e = (p[2]+p[4]) * p[0] * p[6];
                f = (p[0]+p[6]) * p[2] * p[4];

                if((k == 1 && e == 0 ) || (k == 0 && f == 0)) {
                  num[0] = i;
                  num[1] = j;
                  coordinate.add(num.clone());
                }
              }
            }
          }
        }
      }

      if(coordinate != null && coordinate.size() != 0) {
        //System.out.println("if");
        for(int i=0; i<coordinate.size(); i++){
          //System.out.println("black");
          input.setColor(coordinate.get(i)[1],coordinate.get(i)[0],Color.WHITE);
        }
        bFlag = true;
      }
    }
    return input;
  }
  static int countb (int[] p){
    int count=0;
    for(int i=0; i<8; i++) {
      count += p[i];
    }
    return count;
  }
  static int counta (int[] p){
    int count=0;
    for(int i=0; i<7; i++) {
      if((p[i] == 0) && (p[i+1] == 1)) {
        count++;
      }
    }
    if(p[7] == 0 && p[0] == 1){
      count++;
    }
    return count;
  }
  static int c(int[] p) {
    if (((p[0]+p[1]+p[2]+p[5]) == 0 && (p[4]+p[6]) == 2) || ((p[2]+p[3]+p[4]+p[7]) == 0 && (p[0]+p[6] == 2))) {
      return 1;
    }
    else {
      return 0;
    }
  }
  //黒なら１、白なら０を返す
  static int worb (MyImage image, int x, int y) {
    if(image.getColor(x,y).equals(Color.BLACK)) {
      return 1;
    }
    else {
      return 0;
    }
  }
  //Zhang-Suen法
  public static MyImage execute2 (MyImage input, int width, int height) {
    boolean bFlag = true;

    int k = 1;
    while(bFlag){
      bFlag = false;
      k = 1 - k;
      //除去リスト
      List<int[]> coordinate = new ArrayList<int[]>();
      int[] num = new int[2];

      for(int i = 1; i < height-1; i++) {
        for(int j = 1; j < width-1; j++) {
          if(worb(input,j,i) == 1){
            // [p7 p0 p1]
            // [p6    p2]
            // [p5 p4 p3]
            int[] p = new int[8];
            //黒なら1,白なら0を入れる
            p[0] = worb(input,j  ,i-1);
            p[1] = worb(input,j+1,i-1);
            p[2] = worb(input,j+1,i  );
            p[3] = worb(input,j+1,i+1);
            p[4] = worb(input,j  ,i+1);
            p[5] = worb(input,j-1,i+1);
            p[6] = worb(input,j-1,i  );
            p[7] = worb(input,j-1,i-1);

            int a,b,c,d,e,f;
            a = counta(p);
            b = countb(p);
            if(a == 1){
              if(2<=b && b<=6){
                //条件3-1
                if((k == 1) && p[0] * p[2] * p[4] == 0 && p[2] * p[4] * p[6] == 0){
                  num[0] = i;
                  num[1] = j;
                  coordinate.add(num.clone());
                }
                //条件3-2
                if((k != 1) && p[0] * p[2] * p[6] == 0 && p[0] * p[4] * p[6] == 0) {
                  num[0] = i;
                  num[1] = j;
                  coordinate.add(num.clone());
                }
              }
            }
          }
        }
      }
      if(coordinate != null && coordinate.size() != 0) {
        for(int i=0; i<coordinate.size(); i++){
          //System.out.println("black");
          input.setColor(coordinate.get(i)[1],coordinate.get(i)[0],Color.WHITE);
        }
        bFlag = true;
      }
    }
    return input;
  }
}
