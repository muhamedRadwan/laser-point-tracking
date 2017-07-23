package test_2;


import java.awt.image.BufferedImage;
import java.io.*;
import java.io.ByteArrayInputStream;
//import java.io.IOException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.util.Scanner;

public class Test_2 {
    private static final int stander = 200;
    private static int counterforshooting = 0, isw = 1;
    public static boolean left = false, right = false, up = false, bootom = false, havegreen = false, AL = false, OP = true, EX = false, opn1 = false, opn2 = false, movecycle = false, option1 = false, option2 = false, clear = false, canAnalyze = true;
    private static ArrayList<org.opencv.core.Point> circel = new ArrayList<>(16);
    static org.opencv.core.Point mins = new org.opencv.core.Point();
    static org.opencv.core.Point high = new org.opencv.core.Point();
    public static double[] iscirect;
    public static final int sw = 10;
    private static double ma5X = 0, miniX = 0, maxY = 0, miniY = 0;
    public static ArrayList<double[]> templet = new ArrayList<>(3);
    public static ArrayList<double[]> gar = new ArrayList<>(3);
    public static  org.opencv.core.Point result;

    public static Mat points(Mat comat, Mat mat, int choise) throws FileNotFoundException, IOException {   //to remove an tempele           

        ArrayList<org.opencv.core.Point> color = new ArrayList<>();
        try {
            //   System.out.println(counterforshooting);
            //b7wl el sora l gray 
            
          /*  FileOutputStream swq=new FileOutputStream("files\\templet.txt");
            ObjectOutputStream wew=new ObjectOutputStream(swq);
            templet.remove(3);
            wew.writeObject(templet);
            wew.close();
            System.exit(0);*/
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
            int width = mat.width();
            int height = mat.height();
            MatOfByte m = new MatOfByte();

            Imgcodecs.imencode(".jpg", mat, m);

            InputStream in = new ByteArrayInputStream(m.toArray());
            Size t = new Size(640, 480);
            Scalar sca = new Scalar(1);
            Mat cycles = new Mat(t, CvType.CV_8UC1, sca);
            //kda m3aya el sora 
            BufferedImage buffer = ImageIO.read(in);
            //bgyb awl lwn fee elsora 
            java.awt.Color co;
            // int max = co.getRed(),Xmax=0,Ymax=0;
            //int wap=0;
            //b3dy 3la index index fe el mat 
            for (int i = 0; i < height; i++) {

                for (int j = 0; j < width; j++) {
                    co = new java.awt.Color(buffer.getRGB(j, i));
                    //bshof hna a3l ada2a fe el sora we hya btsawe 255
                    if (co.getRed() == 220) {
                        color.add(new org.opencv.core.Point(j, i));
                    }
                }
            }
            if (color.size() < 20) {
//                  counterforshooting=0;
                circel = new ArrayList<>();
                if (choise == 1) {
                    return mat;
                } else {
                    return comat;
                }
          }
            if (color.size() > 5) {

                circel.add(color.get(color.size() / 2));
                circel.add(color.get((color.size() / 2) + 1));
                // counterforshooting=0;
            }
            counterforshooting++;
                if (movecycle) {
                     print(mat,0);
                int op=0;
                for (org.opencv.core.Point p : color) {
       
                      Imgproc.drawMarker(mat, new org.opencv.core.Point(  mat.width() - 90, mat.height() - 180), new Scalar(200, 255, 362));
                     Imgproc.drawMarker(mat, new org.opencv.core.Point( mat.width() - 90, mat.height() - 280), new Scalar(200, 255, 362));
                     Imgproc.drawMarker(mat, new org.opencv.core.Point(  mat.width() - 90, mat.height() - 380), new Scalar(200, 255, 362));
                       Imgproc.drawMarker(mat, new org.opencv.core.Point(  mat.width() - 70, mat.height() - 160), new Scalar(200, 255, 362));
                     Imgproc.drawMarker(mat, new org.opencv.core.Point( mat.width() - 70, mat.height() - 260), new Scalar(200, 255, 362));
                     Imgproc.drawMarker(mat, new org.opencv.core.Point(  mat.width() - 70, mat.height() - 360), new Scalar(200, 255, 362));
                    if (p.x <= mat.width() - 70 && p.y <= mat.height() - 160 && p.x >= mat.width() - 90 && p.y >= mat.height() - 180) {
                        System.out.println("1"+p);
                        clear = true;
                        option2 = false;
                        option1 = false;
                        canAnalyze = true;
                        break;
                    } else if (p.x <= mat.width() - 70  && p.y >= mat.height() - 260&&p.x >= mat.width() - 90 && p.y >=  mat.height() - 280) {
                        System.out.println("2");
                        clear = false;
                        option2 = false;
                        option1 = true;
                        canAnalyze = true;
                        break;
                    } else if (p.x <= mat.width() - 70 && p.y <=  mat.height() - 360 &&p.x >= mat.width() - 90 && p.y >=  mat.height() - 380) {
                          System.out.println("3");
                        clear = false;
                        option2 = true;
                        option1 = false;
                        canAnalyze = true;
                        break;
                    }
              
                }

               
            }
            //get the point between high  and bottom 
            iscirect = new double[circel.size()];
            if (counterforshooting == sw && !circel.isEmpty() && canAnalyze) {
                counterforshooting = 0;
                  //  System.out.println(circel.size());

                //get the point between high  and bottom 
                iscirect = new double[circel.size()];
                iscirect = converttodouble(circel);

                int yu = 0, gar1 = 0, gar2 = 0;

                /*for (double[]ns:gar)
                 {
                 DTW gar=new DTW(iscirect, ns);
                 if(gar.getDistance()<65)
                 {
                 gar1=1;
                 gar2=1;
                 }
                 if(gar1==1)
                 break;
                 }*/
                if (gar2 == 0) {
                    int i=0;
                    for (double[] ns : templet) {
                        //print here
                        i++;
                        DTW bn = new DTW(iscirect, ns);

                        if (bn.getDistance() <= 2500) {
                            canAnalyze = false;
                            System.err.println(i+"true" + bn.getDistance());
                            movecycle = true;
                          
                            break;
                            //System.exit(0);
                        }
                        System.out.println(i+":"+bn.getDistance());
                       
                    }//end for
                }//end if
                /*
                  //to draw the cirecl  
                 for (int i = 0; i < circel.size() - 1; i++) {
                 org.opencv.core.Point we = circel.get(i);
                 org.opencv.core.Point we1 = circel.get(i + 1);
                 if (java.lang.Math.sqrt(java.lang.Math.pow(we.x - we1.x, 2) + java.lang.Math.pow(we.y - we1.y, 2)) < 150) {
                 Imgproc.line(cycles, we, we1, new Scalar(255, 255, 255), 5);
                 }
                 }

                 Imgcodecs.imwrite("cyrcle" + isw + ".jpg", cycles);
                 //to add an templet            
                 Scanner input= new Scanner(System.in);
                 if(input.nextInt()==1)
                 {
                 //  pri.writeObject((Object)circel);
                 try (ObjectOutputStream pri = new ObjectOutputStream(new FileOutputStream("files\\templet.txt"))) {
                 //  pri.writeObject((Object)circel);
                 double[] i0;
                 i0=converttodouble(circel);
                 templet.add(i0); 
                 pri.writeObject(templet);
                 pri.close();
                 }
                 } 
                 */
                isw++;
                circel = new ArrayList<>();

            } else if (counterforshooting == sw) {
                counterforshooting = 0;
            }

            //hna bshof any el array fee akter mn 30 andex wla la2 
            Mat ma;
            //hna brsm aw blwn el no2t el feha a3l do2
            if (choise == 1) //el function de a7na el 3mlenha 
            {
                ma = draw(color, mat, choise);
            } else {
                ma = draw(color, comat, choise);
            }
            //hna 3lshan a3rf hwa wsl le  left aw el right aw el up down
            
            for (org.opencv.core.Point p : color) {
                if (p.x < width * 0.01) {
                    left = false;
                    right = true;
                    up = false;
                    bootom = false;
                } else if (OP) {

                    if (p.x > width * 0.99&&!Test_2.movecycle) {
                        
                        right = false;
                        left = true;
                        up = false;
                        bootom = false;
                    } else if (p.y < height * 0.01) {
                        up = true;
                        left = false;
                        right = false;
                        bootom = false;
                    } else if (p.y > height * 0.99) {
                        bootom = true;
                        up = false;
                        left = false;
                        right = false;
                    }
                }
            }
              

            // hna 3lshan lo geh na7et el left ytl3 3 cycle we y5tar wa7da mnhm
            if (left) {
                AL = true;

            }
            if (AL) {

                if (right == false) {
                    ma = print(ma,1);
                    OP = false;
                    for (org.opencv.core.Point p : color) {
                        if (p.x <= ma.width() - 70 && p.y <= ma.height() - 160 && p.x >= ma.width() - 90 && p.y >= ma.height() - 180) {
                            EX = true;
                            opn1 = false;
                            opn2 = false;
                            // System.out.println("hiw");
                            break;

                        }

                    }

                } else {
                    AL = false;
                    OP = true;
                }

            }
            return ma;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static double[] converttodouble(ArrayList<org.opencv.core.Point> s) {
        double[] nsd = new double[s.size()];

        

        //get high 
        miniX = s.get(0).x;

        for (Point item : s) {
            if (miniX > item.x) {
                miniX = item.x;
                mins = item;
            }
            if (ma5X < item.x) {
                ma5X = item.x;
                high = item;
            }
        }
        //get the point between high  and bottom 

        result = new org.opencv.core.Point(((high.x + mins.x) / 2), ((high.y + mins.y) / 2));
        for (int i = 0; i < s.size(); i++) {
            nsd[i] = java.lang.Math.sqrt(java.lang.Math.pow(s.get(i).x - result.x, 2) + java.lang.Math.pow(s.get(i).y - result.y, 2));
        }
        return nsd;
    }

  
    public static boolean isleft() {
        return left;
    }

    public static boolean isBooton() {
        return bootom;
    }

    public static boolean isUp() {
        return up;
    }

    public static boolean isRight() {
        return right;
    }

    public static void setLeft(boolean left) {
        Test_2.left = left;
    }

    public static void setBooton(boolean booton) {
        Test_2.bootom = booton;
    }

    public static void setRight(boolean right) {
        Test_2.right = right;
    }

    public static void setUp(boolean up) {
        Test_2.up = up;
    }

    public static Mat pointsimg(Mat mat) {
        ArrayList<org.opencv.core.Point> color = new ArrayList<>();
        try {
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
            int width = mat.width();
            int height = mat.height();
            MatOfByte m = new MatOfByte();
            Imgcodecs.imencode(".jpg", mat, m);
            InputStream in = new ByteArrayInputStream(m.toArray());
            BufferedImage buffer = ImageIO.read(in);
            java.awt.Color co = new java.awt.Color(buffer.getRGB(0, 0));
            int max = co.getRed(), Xmax = 0, Ymax = 0;
            int wap = 0;
            for (int i = 0; i < height; i++) {

                for (int j = 1; j < width; j++) {
                    co = new java.awt.Color(buffer.getRGB(j, i));
                    if (max == co.getRed()) {
                        color.add(new org.opencv.core.Point(j, i));
                    } else if (co.getRed() > max) {
                        max = co.getRed();
                        color = new ArrayList<>();
                        color.add(new org.opencv.core.Point(j, i));
                    }
                }
            }
            Mat ma = draw(color, mat, 1);
            return ma;
        } catch (IOException ex) {
            System.out.println("error in function points");
        }
        return null;
    }

    private static Mat draw(ArrayList<org.opencv.core.Point> points, Mat mat, int chose) {
        org.opencv.core.Point p1;
        if (chose == 1) {
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2BGR);
        }
        for (org.opencv.core.Point point : points) {
            p1 = point;
            Imgproc.circle(mat, p1, 5, new Scalar(0, 255, 0));
        }
        return mat;
    }

    private static Mat print(Mat mat,int op) {
    
        if(op==1)
        Imgproc.putText(mat, "Exit", new org.opencv.core.Point(mat.width() - 105, 300), 1, 1, new Scalar(255, 0, 0));
        else
        Imgproc.putText(mat, "clear", new org.opencv.core.Point(mat.width() - 105, 300), 1, 1, new Scalar(255, 0, 0));
        Imgproc.circle(mat, new org.opencv.core.Point(mat.width() - 90, mat.height() - 180), 40, new Scalar(255, 0, 0));
        Imgproc.putText(mat, "option1", new org.opencv.core.Point(mat.width() - 120, mat.height() - 280), 1, 1, new Scalar(255, 0, 0));
        Imgproc.circle(mat, new org.opencv.core.Point(mat.width() - 90, mat.height() - 280), 40, new Scalar(255, 0, 0));
        Imgproc.putText(mat, "option2", new org.opencv.core.Point(mat.width() - 120, mat.height() - 380), 1, 1, new Scalar(255, 0, 0));
        Imgproc.circle(mat, new org.opencv.core.Point(mat.width() - 90, mat.height() - 380), 40, new Scalar(255, 0, 0));
        return mat;
    }

   /* private static Mat print2(Mat mat) {
        System.out.println(mins);
        Imgproc.putText(mat, "clear", new org.opencv.core.Point(mins.x, mins.y +80), 1, 1, new Scalar(255, 0, 0));
        Imgproc.circle(mat, new org.opencv.core.Point(mins.x + 5, mins.y + 80), 40, new Scalar(255, 0, 0));
        Imgproc.putText(mat, "option1", new org.opencv.core.Point(mins.x + 70, mins.y + 80), 1, 1, new Scalar(255, 0, 0));
        Imgproc.circle(mat, new org.opencv.core.Point(mins.x + 100, mins.y + 80), 40, new Scalar(255, 0, 0));
        Imgproc.putText(mat, "option2", new org.opencv.core.Point(mins.x + 170, mins.y + 80), 1, 1, new Scalar(255, 0, 0));
        Imgproc.circle(mat, new org.opencv.core.Point(mins.x + 200, mins.y + 80), 40, new Scalar(255, 0, 0));
        return mat;
    }*/



/**
 * 
 */

     /*counteru=circel.size()-1;
     if(circel.get(counteru).x>ma5X)
     ma5X= circel.get(counteru).x;
     if(circel.get(counteru).x>maxY)
     maxY= circel.get(counteru).y;
     if(circel.get(counteru).x<miniX)
     miniX= circel.get(counteru).x;
     if(circel.get(counteru).x<miniY)
     {
     miniY= circel.get(counteru).y;
     }
     hightimage=(maxY-miniY);
     widthimage=(ma5X-miniX);
     */
}
