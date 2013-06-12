package com.heliocentris.rms.util;



import com.heliocentris.rms.util.logger.Logger;
import com.heliocentris.rms.util.logger.LoggerFactory;


/**
 * Utility to provide all functionality for CRC16.
 * 
 * @author thien
 *
 */
public class CRC16
{
   private static final Logger logger = LoggerFactory.getLogger(CRC16.class);
   
   public static final int BITMASK_8 = 0xFF;
   public static final int BITMASK_16 = 0xFFFF;
   public static final int DEFAULT_POLYNOM = 0x8041;

   private int polynom = DEFAULT_POLYNOM;

   private static final CRC16 instance = new CRC16();
   
   public CRC16()
   {
   }

   public static CRC16 getInstance()
   {
      return instance;
   }
   
   public CRC16(int polynom)
   {
      setPolynom(polynom);
   }

   public int getPolynom()
   {
      return polynom;
   }

   public void setPolynom(int polynom)
   {
      this.polynom = polynom;
   }

   public int calculate(byte[] bytes)
   {
      return calculate(polynom, bytes);
   }

   public int calculate(int polynom, byte[] bytes)
   {
      logger.entering(polynom, bytes);
      int crc16 = BITMASK_16;
      for (byte data : bytes)
      {
         crc16 = crc16 * 2;
         if (crc16 > BITMASK_16)
         {
            crc16 = (crc16 & BITMASK_16) ^ polynom;
         }
         crc16 = crc16 ^ (data & BITMASK_8);
      }
      return logger.exiting(crc16);
   }

   public static boolean check(int checkSum, byte[] bytes)
   {
      logger.entering(checkSum, bytes);
      return logger.exiting(checkSum == getInstance().calculate(bytes));
   }
   
   public static void main(String[] args)
   {
      byte[] bytes = new byte[2048000];
      for (int i = 0; i < bytes.length; i++)
      {
         bytes[i] = (byte) i;
      }
      long milliseconds = System.currentTimeMillis();
      System.out.println(new CRC16().calculate(bytes));
      System.out.println("duration in ms: " + (System.currentTimeMillis() - milliseconds));

      milliseconds = System.currentTimeMillis();
      System.out.println(new CRC16().calculate(bytes));
      System.out.println("duration in ms: " + (System.currentTimeMillis() - milliseconds));

   }
}
