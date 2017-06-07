package tj.pwv.service;

import java.util.List;

/**
 * Created by mao on 2017/6/6.
 */
public interface RealtimeService {
    public void getFtpPwv();
    public void getFtpSwv();
    public void handlePwvFile();
    public void handleSwvFile();
    public void deleteFiles();
    public List<Integer> doy2day(int year, int doy);
}
