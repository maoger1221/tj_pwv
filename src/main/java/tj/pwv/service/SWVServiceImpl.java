package tj.pwv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tj.pwv.mapper.SwvMapper;
import tj.pwv.pojo.Swv;
import tj.pwv.utils.SWV.GetSWV;
import tj.pwv.utils.SWV.SWV;

import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mao on 2017/6/7.
 */
@Service
public class SWVServiceImpl implements SWVService {
    @Value("${FILENAME}")
    private String FILENAME;
    @Value("${LOCALPATH}")
    private String LOCALPATH;
    @Value("${DPHFILENAME}")
    private String DPHFILENAME;
    @Value("${DPHFILEPOSITION}")
    private String DPHFILEPOSITION;

    @Autowired
    private RealtimeService realtimeService;
    @Autowired
    private SwvMapper swvMapper;
    @Override
    public void getSWV1() {
            String pwvPath = LOCALPATH + FILENAME;
            for (int i = 1; i <= 32; i++) {
                Swv latestSwv = swvMapper.selectLatest(i);
                String dphPath = DPHFILEPOSITION + DPHFILENAME;
                if (i < 10) {
                    dphPath = dphPath + "0" + i;
                } else {
                    dphPath += i;
                }
                if (!new File(dphPath).exists())
                    continue;
                List<SWV> my_swv = GetSWV.getMySWV(pwvPath, dphPath);
                //格式转换后入库
                for (SWV swv1 : my_swv){
                    Swv swv2 = swvTrans(swv1,i);
                    if (latestSwv == null || swv2.getDate().getTime() - latestSwv.getDate().getTime() > 10000){//相差大于10s，防止有多1s的相同数据出现
                        swvMapper.insert(swv2);
                    }
                }
//                GetSWV.writeSWVFile(pwvPath, dphPath, my_swv);
            }
    }
    private Swv swvTrans(SWV swv1,int prn){
        Swv swv2 = new Swv();
        swv2.setAzi(new BigDecimal(swv1.getAzi()));
        swv2.setElev(new BigDecimal(swv1.getElev()));
        swv2.setSwv(new BigDecimal(swv1.getSwv()));
        swv2.setPrn(prn);
        List<Integer> monthdayList = realtimeService.doy2day(swv1.getYear(),swv1.getDoy());
        Calendar calendar = Calendar.getInstance();
        calendar.set(swv1.getYear(),monthdayList.get(0)-1,monthdayList.get(1),swv1.getHour(),swv1.getMin(),swv1.getSec());//month从0开始
        Date date = calendar.getTime();
        swv2.setDate(date);
        return swv2;
    }
}
