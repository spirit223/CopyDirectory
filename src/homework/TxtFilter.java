package homework;

import java.io.File;
import java.io.FileFilter;

/**
 * @author ironcentury
 * @date 2020/8/5 - 9:23
 */
public class TxtFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        if(pathname.isDirectory())
        {
            return true;
        }else if(pathname.getName().endsWith(".java"))
        {
            return true;
        }else
        {
            return false;
        }
    }
}
