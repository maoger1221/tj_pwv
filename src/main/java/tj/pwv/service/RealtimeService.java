package tj.pwv.service;

/**
 * Created by mao on 2017/6/6.
 */
public interface RealtimeService {
    public boolean getFtpPwv();
    public void handlePwvFile();
    public void deleteFiles();
}
