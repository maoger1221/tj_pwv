package tj.pwv.service;


/**
 * Created by mao on 2017/6/3.
 */

public interface FileService {
    public void createFile(String path,Object content,String type);
    public void writeFile(String path,String content);
    public void deleteFile(String path);


}
