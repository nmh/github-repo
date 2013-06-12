package com.heliocentris.rms.core.processor;

import javax.ejb.Stateless;

import com.heliocentris.rms.core.data.Header;

@Stateless
public class DataProcessorBean implements DataProcessor
{

   @Override
   public void proccess(Header header)
   {
      // preprocessing on system server level 
      
      header.getSite().process(header);
      
      // preprocessing on system server level 
   }
}
