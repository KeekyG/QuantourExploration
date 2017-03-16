package data.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import po.StockUpDownPO;

public class WriteCsv {
	
	 /**  
     * ASCII表中可见字符从!开始，偏移位值为33(Decimal)  
     */    
    static final char DBC_CHAR_START = 33; // 半角!    
    
    /**  
     * ASCII表中可见字符到~结束，偏移位值为126(Decimal)  
     */    
    static final char DBC_CHAR_END = 126; // 半角~    
    
    /**  
     * 全角对应于ASCII表的可见字符从！开始，偏移值为65281  
     */    
    static final char SBC_CHAR_START = 65281; // 全角！    
    
    /**  
     * 全角对应于ASCII表的可见字符到～结束，偏移值为65374  
     */    
    static final char SBC_CHAR_END = 65374; // 全角～    
    
    /**  
     * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移  
     */    
    static final int CONVERT_STEP = 65248; // 全角半角转换间隔    
    
    /**  
     * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理  
     */    
    static final char SBC_SPACE = 12288; // 全角空格 12288    
    
    /**  
     * 半角空格的值，在ASCII中为32(Decimal)  
     */    
    static final char DBC_SPACE = ' '; // 半角空格
	
	public static String qj2bj(String src) {    
        if (src == null) {    
            return src;    
        }    
        StringBuilder buf = new StringBuilder(src.length());    
        char[] ca = src.toCharArray();    
        for (int i = 0; i < src.length(); i++) {    
            if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) { // 如果位于全角！到全角～区间内    
                buf.append((char) (ca[i] - CONVERT_STEP));    
            } else if (ca[i] == SBC_SPACE) { // 如果是全角空格    
                buf.append(DBC_SPACE);    
            } else { // 不处理全角空格，全角！到全角～区间外的字符    
                buf.append(ca[i]);    
            }    
        }    
        return buf.toString();    
    }
	
	public static void main(String[] args) {
		CsvReader record = null;
		CsvWriter writer = null;
		try{
			record = new CsvReader("C://Users/ssw/Desktop/量化交易/股票历史数据ALL.csv", '	',Charset.forName("UTF8"));
			writer = new CsvWriter("C://Users/ssw/Desktop/量化交易/股票历史数据ALL(clean).csv", '	',Charset.forName("UTF8"));
			record.readHeaders();
			int flag = 0;
			while (record.readRecord()) {
				record.getRawRecord();
				if(!record.get("Volume").equals("0")){
					String[] contents = {qj2bj(String.valueOf(flag)),qj2bj(record.get("Open")),qj2bj(record.get("High")),
							qj2bj(record.get("Low")),qj2bj(record.get("Close")),qj2bj(record.get("Volume")),qj2bj(record.get("Adj Close")),qj2bj(record.get("code")),qj2bj(record.get("name")),qj2bj(record.get("market"))};
					writer.writeRecord(contents);
					flag++;
				}
			}
			record.close();
			writer.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
