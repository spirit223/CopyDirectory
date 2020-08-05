package homework;

import java.io.*;

/**
 * @author ironcentury
 * @date 2020/8/5 - 9:18
 */
public class Copy {
    static int count = 0;
    public static void main(String[] args) throws  IOException{
        File fileSrc = new File("D:\\Develop\\IODemo\\src\\cn\\itcast");
        File fileDesc = new File("D:\\result");

        judgeFile(fileSrc, fileDesc);
        System.out.println("txtFileCount is " + count);
    }

    /*文件类型判断函数*/
    public static void judgeFile(File src, File desc) throws IOException
    {
        if(!desc.exists())
        {
            desc.mkdir();
        }
        File[] files = src.listFiles(new TxtFilter());
        for(File f : files)
        {
            if (f.isFile())
            {
                count++;
                copyFile(new File(src+"\\"+f.getName()), new File(desc+"\\"+f.getName()));
                //desc-->D:\\result\\f.getName()   
            }else
            {
                judgeFile(new File(src+"\\"+f.getName()),new File(desc+"\\"+f.getName()));
            }
        }
    }

    /*文件复制函数*/
    public static void copyFile(File srcFile,File targetFile) throws IOException
    {
        //创建流对象
        FileReader fr = new FileReader(srcFile);
        FileWriter fw = new FileWriter(targetFile);
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(fw);

        //复制文件
        int len = 0;
        char[] chars = new char[1024];
        while((len = br.read(chars)) != -1)
        {
            bw.write(chars, 0, len);
            bw.flush();
        }

        //关闭流
        fw.close();
        bw.close();
        fr.close();
        br.close();
    }
}
