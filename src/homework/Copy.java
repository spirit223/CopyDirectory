package homework;

import java.io.*;

/**
 * @author ironcentury
 * @date 2020/8/5 - 9:18
 */
public class Copy {
    static int count = 0;       //文件个数计数器
    public static void main(String[] args) throws  IOException{

        //创建流对象,fileSrc表示数据源;fileDesc为数据复制目标文件夹
        File fileSrc = new File("D:\\Develop\\IODemo\\src\\cn\\itcast");
        File fileDesc = new File("D:\\result");

        judgeFile(fileSrc, fileDesc);
        System.out.println("txtFileCount is " + count);
    }

    /*文件类型判断函数*/
    public static void judgeFile(File src, File desc) throws IOException
    {
        //如果数据目的文件不存在,则创建一个新的文件夹,一般是同名子目录不存在
        if(!desc.exists())
        {
            desc.mkdir();
        }

        //将源文件夹的文件遍历放入File[]中进行遍历复制
        File[] files = src.listFiles(new TxtFilter());
        for(File f : files)
        {
            if (f.isFile())
            {
                //如果目标是文件,则调用文件复制函数
                count++;
                copyFile(new File(src+"\\"+f.getName()), new File(desc+"\\"+f.getName()));
            }else
            {
                //目标是文件夹则递归调用对该文件夹进行遍历
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
        char[] chars = new char[2048];

        //每次读取2024个文件进行写入,如果read返回-1,则退出文件写入并关闭流
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
