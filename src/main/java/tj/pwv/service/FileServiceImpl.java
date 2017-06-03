package tj.pwv.service;

import org.springframework.stereotype.Service;
import tj.pwv.pojo.Pwv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by mao on 2017/6/3.
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public void createFile(String path, Object content,String type) {
        File file =new File(path);
        try {
            if(!file.exists()){
                file.createNewFile();
                writeFile(path,type);
                writeFile(path,System.getProperty("line.separator"));
                if (type.equals("pwv_before")){
                    List<Pwv> c = (List<Pwv>)content;
                    for (Pwv pwv : c){
                        writeFile(path,String.format("%.4f",pwv.getPw()));
                        writeFile(path,System.getProperty("line.separator"));
                    }
                }/*else if (type.equals("y")){
                    List<List<Double>> y = (List<List<Double>>)content;
                    for (List<Double> yrow : y){
                        for (Double ycol : yrow){
                            writeFile(path,String.format("%.4f",ycol));
                            writeFile(path,"   ");
                        }
                        writeFile(path,System.getProperty("line.separator"));
                    }
                }*/else {
                    List<Double> c = (List<Double>)content;
                    for (Double d : c){
                        writeFile(path,String.format("%.4f",d));
                        writeFile(path,System.getProperty("line.separator"));
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void writeFile(String path, String content) {
        File file =new File(path);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file,true);
            fw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteFile(String path) {
        File file =new File(path);
        try {
            if(file.exists()){
                file.delete();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
