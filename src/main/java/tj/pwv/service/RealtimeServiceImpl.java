package tj.pwv.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tj.pwv.pojo.Pwv;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static tj.pwv.utils.FtpUtil.downloadFile;

/**
 * Created by mao on 2017/6/6.
 */
@Service
@EnableScheduling
public class RealtimeServiceImpl implements RealtimeService{
    @Value("${HOST}")
    private String HOST;
    @Value("${PORT}")
    private int PORT;
    @Value("${USERNAME}")
    private String USERNAME;
    @Value("${PASSWORD}")
    private String PASSWORD;
    @Value("${REMOTEPATH}")
    private String REMOTEPATH;
    @Value("${FILENAME}")
    private String FILENAME;
    @Value("${LOCALPATH}")
    private String LOCALPATH;
    @Value("${PWVKEY}")
    private String PWVKEY;
    @Value("${DOWNLOADPOSTION}")
    private String DOWNLOADPOSTION;
    @Value("${UPLOADPOSTION}")
    private String UPLOADPOSTION;
    @Autowired
    private DataService dataService;
    @Autowired
    private JedisAdapter jedisAdapter;
    @Override
    public boolean getFtpPwv() {
        return downloadFile(HOST,PORT,USERNAME,PASSWORD,REMOTEPATH,FILENAME,LOCALPATH);
    }

    @Override
    @Scheduled(fixedRate = 60000)//定时任务1分钟执行一次
    public void handlePwvFile() {
        System.out.println("scheduled before");
//        deleteDirectory(LOCALPATH);
        getFtpPwv();
        List<Pwv> pwvList = readPWVFile(LOCALPATH + FILENAME);//从ftp数据中找出需要入库的数据
        if (pwvList.size()!=0){
            dataService.insertPWV(pwvList);//向mysql中插入数据
            //向redis中插入数据
            for (Pwv pwv : pwvList){
               jedisAdapter.lpush(PWVKEY, JSONObject.toJSONString(pwv));
            }
            System.out.println("scheduled执行");
        }
    }
    @Override
    @Scheduled(fixedRate = 3600000)//定时任务10小时执行一次
    public void deleteFiles(){
        deleteDirectory(UPLOADPOSTION);
        deleteDirectory(DOWNLOADPOSTION);
        System.out.println("delete执行");
    }

    private List<Pwv> readPWVFile(String pwvPath) {
        // 字节流读取数据
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        Pwv latestPwv = dataService.getLatestPWV();//从数据库中取出最近的一条数据
        Date latesDate = latestPwv.getDate();
        List<Pwv> pwv_list = new ArrayList<Pwv>();// 存储pwv数据的list
        int year_read, doy_read, hour_read, min_read, sec_read;
        double tzen_read,wzen_read,szen_read,pwv_read,spwv_read,press_read,temp_read,zhd_read,gradNS_read,sNS_read,gradEW_read,sEW_read;

        try {
            String str = "";
            int num_pwv = 0;
            fis = new FileInputStream(pwvPath);
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                if (num_pwv > 3) {
                    year_read = Integer.parseInt(str.substring(1, 5).trim());// 年
                    doy_read = Integer.parseInt(str.substring(6, 9).trim());// 年积日
                    hour_read = Integer.parseInt(str.substring(10, 12).trim());// 时
                    min_read = Integer.parseInt(str.substring(13, 15).trim());// 分
                    sec_read = Integer.parseInt(str.substring(16, 18).trim());// 秒
                    tzen_read = Double.parseDouble(str.substring(22, 30).trim());
                    wzen_read = Double.parseDouble(str.substring(32, 39).trim());// zwd天顶湿延迟
                    szen_read = Double.parseDouble(str.substring(40, 47).trim());
                    pwv_read = Double.parseDouble(str.substring(49, 55).trim());// pwv
                    spwv_read = Double.parseDouble(str.substring(56, 63).trim());
                    press_read = Double.parseDouble(str.substring(69, 78).trim());
                    temp_read = Double.parseDouble(str.substring(82, 90).trim());
                    zhd_read = Double.parseDouble(str.substring(91, 100).trim());
                    gradNS_read = Double.parseDouble(str.substring(103, 110).trim());// gN
                    sNS_read = Double.parseDouble(str.substring(112, 119).trim());// sgN
                    gradEW_read = Double.parseDouble(str.substring(121, 128).trim());// gE
                    sEW_read = Double.parseDouble(str.substring(129).trim());// sgE

                    List<Integer> monthdayList = doy2day(year_read,doy_read);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year_read,monthdayList.get(0),monthdayList.get(1),hour_read,min_read,sec_read);
                    Date date_read = calendar.getTime();
                    //如果从ftp中读取的数据的时间在数据库中最新数据之后
                    if (latesDate.getTime() < date_read.getTime()){
                        Pwv pwv = new Pwv();
                        pwv.setDate(date_read);
                        pwv.setTotalzen(new BigDecimal(tzen_read));
                        pwv.setWetzen(new BigDecimal(wzen_read));
                        pwv.setSigzen(new BigDecimal(szen_read));
                        pwv.setPw(new BigDecimal(pwv_read));
                        pwv.setSigpw(new BigDecimal(spwv_read));
                        pwv.setPress(new BigDecimal(press_read));
                        pwv.setTemp(new BigDecimal(temp_read));
                        pwv.setZhd(new BigDecimal(zhd_read));
                        pwv.setGradns(new BigDecimal(gradNS_read));
                        pwv.setSigns(new BigDecimal(sNS_read));
                        pwv.setGradew(new BigDecimal(gradEW_read));
                        pwv.setSigew(new BigDecimal(sEW_read));
                        pwv_list.add(pwv);
                    }
                }
                num_pwv++;
            }
        } catch (FileNotFoundException e) {
            // System.out.println("找不到指定文件");
            e.printStackTrace();
        } catch (IOException e) {
            // System.out.println("读取文件失败");
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pwv_list;
    }



    private List<Integer> doy2day(int year, int doy) {
        int flag = 0;
        String temp="";
        List<Integer> list = new ArrayList<>();
        if (year % 4 == 0)
            flag = 1;
        if (doy <= 31) {
            temp = "00" + doy;
            list.add(1);
        } else if (doy <= 59 + flag) {
            temp = "00" + (doy - 31);
            list.add(2);
        } else if (doy <= 90 + flag) {
            temp = "00" + (doy - 59 - flag);
            list.add(3);
        } else if (doy <= 120 + flag) {
            temp = "00" + (doy - 90 - flag);
            list.add(4);
        } else if (doy <= 151 + flag) {
            temp = "00" + (doy - 120 - flag);
            list.add(5);
        } else if (doy <= 181 + flag) {
            temp = "00" + (doy - 151 - flag);
            list.add(6);
        } else if (doy <= 212 + flag) {
            temp = "00" + (doy - 181 - flag);
            list.add(7);
        } else if (doy <= 243 + flag) {
            temp = "00" + (doy - 212 - flag);
            list.add(8);
        } else if (doy <= 273 + flag) {
            temp = "00" + (doy - 243 - flag);
            list.add(9);
        } else if (doy <= 304 + flag) {
            temp = "00" + (doy - 273 - flag);
            list.add(10);
        } else if (doy <= 334 + flag) {
            temp = "00" + (doy - 304 - flag);
            list.add(11);
        } else if (doy <= 365 + flag) {
            temp = "00" + (doy - 334 - flag);
            list.add(12);
        }
        list.add(Integer.parseInt(temp.substring(temp.length() - 2)));
        return list;
    }

    private boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        //删除文件夹下的所有文件
        File[] files = dirFile.listFiles();
        for (File file : files){
            file.delete();
        }
        return true;
    }

}
